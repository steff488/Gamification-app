package GUI;

import Repository.Database;
import Service.DatabaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Login {

    private Database db = new Database("database.db");
    private DatabaseService service = new DatabaseService(db);

    @FXML
    public TextField userField;

    @FXML
    public PasswordField passField;

    @FXML
    public Button loginButton;

    @FXML
    public Label warningLabel;

    public void login() throws Exception {

        Main m = new Main();
        String username = userField.getText();
        String pass = passField.getText();
        Main.currentUser = username;

        if (username.isEmpty()) {
            warningLabel.setText("Please input username!");
        } else if (pass.isEmpty()) {
            warningLabel.setText("Please input password!");
        } else {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            String encodedPass = bytesToHex(hash);
            String dbPass = service.getPassword(username, pass);
            if (dbPass != null && dbPass.equals(encodedPass)) {
                warningLabel.setText("Successful log in!");

                String ur = service.userRank(username);
                switch(ur) {
                    case "Admin":
                        m.changeScene("/GUI/AdminLandingPage.fxml", "Admin page",600, 800);
                        break;
                    case "Senior":
                        //TODO
                        break;
                    case "Junior":
                        m.changeScene("/GUI/JuniorLandingPage.fxml", "Junior page",600, 800);
                        break;
                }
            } else {
                warningLabel.setText("This account does not exist!");
            }
        }
    }

    public void initialize() {
        warningLabel.setText("");
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
