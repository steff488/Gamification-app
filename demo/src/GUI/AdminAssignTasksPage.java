package GUI;

import Domain.Account;
import Domain.Task;
import Repository.Database;
import Service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminAssignTasksPage {
    private Main m = new Main();
    private Database db = new Database("database.db");
    private DatabaseService service = new DatabaseService(db);

    private boolean tasksSelected = false;
    private boolean usersSelected = false;

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    private final ObservableList<Account> users = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> tasksListAdmin;

    @FXML
    private ListView<Account> usersListAdmin;

    @FXML
    private Button selectTasksButtonAdmin;

    @FXML
    private Button assignTasksButtonAdmin;

    @FXML
    private Button assignTasksAdminBackButton;

    @FXML
    private Button confirmAdminButton;

    @FXML
    private Label assignTasksAdminWarning;

    public void displayTasks() throws Exception {
        tasksListAdmin.getItems().clear();
        tasks.addAll(service.getAllTasks()
                .stream()
                .sorted((t1, t2) -> {
                    if (t1.getTaskName().compareTo(t2.getTaskName()) > 0)
                        return 1;
                    if (t1.getTaskName().equals(t2.getTaskName()))
                        return 0;
                    else return -1;
                })
                .collect(Collectors.toCollection(ArrayList::new)));
        tasksListAdmin.setItems(tasks);
        tasksListAdmin.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void displayUsers(int rank) throws Exception {
        usersListAdmin.getItems().clear();
        users.addAll(service.getAllUsers()
                .stream()
                .filter(u -> u.getRank() > rank)
                .sorted((u1, u2) -> {
                    if (u1.getUserName().compareTo(u2.getUserName()) > 0)
                        return 1;
                    if (u1.getUserName().equals(u2.getUserName()))
                        return 0;
                    else return -1;
                })
                .collect(Collectors.toCollection(ArrayList::new)));
        usersListAdmin.setItems(users);
    }

    public void assignTasksAdminBack(ActionEvent actionEvent) throws Exception {
        m.changeScene("/GUI/AdminLandingPage.fxml", "Admin page", 600, 800);
    }

    public void selectTasksAdmin(ActionEvent actionEvent) {
        tasksSelected = true;
        tasksListAdmin.setStyle("-fx-selection-bar-non-focused: green;");
        ObservableList<Task> selectedTasks = tasksListAdmin.getSelectionModel().getSelectedItems();
        for (Task t : selectedTasks) {
            System.out.println(t);
        }
    }

    public void assignTasksAdmin() {
        usersSelected = true;
        usersListAdmin.setStyle("-fx-selection-bar-non-focused: red;");
        Account a = usersListAdmin.getSelectionModel().getSelectedItem();
        System.out.println(a);
    }

    public void confirmAdmin(ActionEvent actionEvent) throws Exception {
        if (!tasksSelected)
            assignTasksAdminWarning.setText("Please select tasks!");
        else if (!usersSelected) {
            assignTasksAdminWarning.setText("Please select users!");
        } else {
            assignTasksAdminWarning.setText("");
            Account userSelected = usersListAdmin.getSelectionModel().getSelectedItem();

            ObservableList<Task> selectedTasks = tasksListAdmin.getSelectionModel().getSelectedItems();
            for (Task t : selectedTasks) {
                t.setUserName(userSelected.getUserName());
                service.updateTasks(t);
            }

            tasksListAdmin.getItems().removeAll(selectedTasks);

            tasksListAdmin.getSelectionModel().clearSelection();
            usersListAdmin.getSelectionModel().clearSelection();
        }
    }

    public void initialize() throws Exception {
        displayTasks();

        String rank = service.userRank(Main.currentUser);
        int comparableRank = switch (rank) {
            case "Admin" -> 1;
            case "Senior" -> 2;
            case "Junior" -> 3;
            default -> 0;
        };
        displayUsers(comparableRank);
    }
}