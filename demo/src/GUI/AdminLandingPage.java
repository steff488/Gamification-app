package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.Main;

public class AdminLandingPage {

    private Main m = new Main();
    @FXML
    private Button assignTasksAdminLandingButton;
    @FXML
    private Button verifyTasksAdminLandingButton;
    @FXML
    private Button logoutButton;

    public void assignTasksAdminLanding(ActionEvent actionEvent) throws Exception {
        m.changeScene("/GUI/AdminAssignTasksPage.fxml", "Admin assign page", 600, 800);
    }

    public void verifyTasksAdminLanding(ActionEvent actionEvent) throws Exception {
        m.changeScene("/GUI/AdminVerifyTasksPage.fxml", "Admin verify page", 600, 800);
    }

    public void logout(ActionEvent actionEvent) throws Exception {
        m.changeScene("/GUI/Login.fxml", "Login page", 320, 270);
    }
}
