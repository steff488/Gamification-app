package GUI;

import Domain.Task;
import Repository.Database;
import Service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import main.Main;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminVerifyTasksPage {
    private Main m = new Main();
    private Database db = new Database("database.db");
    private DatabaseService service = new DatabaseService(db);

    private boolean tasksSelected = false;

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> doneTasksListAdmin;

    @FXML
    private Button verifyTasksAdminBackButton;

    @FXML
    private Button approveTaskAdminButton;

    @FXML
    private Button denyTaskAdminButton;

    @FXML
    private Label warningVerifyAdmin;

    public void verifyTasksAdminBack(ActionEvent actionEvent) throws Exception {
        m.changeScene("/GUI/AdminLandingPage.fxml", "Admin page", 600, 800);
    }

    public void displayTasks() throws Exception {
        doneTasksListAdmin.getItems().clear();
        tasks.addAll(service.getAllTasks()
                .stream()
                .filter(t -> t.getDone() == 1)
                .sorted((t1, t2) -> {
                    if (t1.getTaskName().compareTo(t2.getTaskName()) > 0)
                        return 1;
                    if (t1.getTaskName().equals(t2.getTaskName()))
                        return 0;
                    else return -1;
                })
                .collect(Collectors.toCollection(ArrayList::new)));
        doneTasksListAdmin.setItems(tasks);
        doneTasksListAdmin.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initialize() throws Exception {
        displayTasks();
    }

    public void approveTaskAdmin(ActionEvent actionEvent) throws Exception {
        if (!tasksSelected)
            warningVerifyAdmin.setText("Select task!");
        else {
            warningVerifyAdmin.setText("");
            ObservableList<Task> selectedTasks = doneTasksListAdmin.getSelectionModel().getSelectedItems();
            for (Task t : selectedTasks) {

                service.updateTasks(t);
            }

            doneTasksListAdmin.getItems().removeAll(selectedTasks);

            doneTasksListAdmin.getSelectionModel().clearSelection();
            doneTasksListAdmin.getSelectionModel().clearSelection();
        }
    }

    public void denyTaskAdmin(ActionEvent actionEvent) throws Exception {
        if (!tasksSelected)
            warningVerifyAdmin.setText("Select task!");
        else {
            warningVerifyAdmin.setText("");
            ObservableList<Task> selectedTasks = doneTasksListAdmin.getSelectionModel().getSelectedItems();
            for (Task t : selectedTasks) {
                t.setDone(0);
                service.updateTasks(t);
            }

            doneTasksListAdmin.getItems().removeAll(selectedTasks);

            doneTasksListAdmin.getSelectionModel().clearSelection();
            doneTasksListAdmin.getSelectionModel().clearSelection();
        }
    }
}
