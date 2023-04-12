package GUI;

import Domain.Task;
import Repository.Database;
import Service.DatabaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Main;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JuniorLandingPage {

    private Main m = new Main();
    private Database db = new Database("database.db");
    private DatabaseService service = new DatabaseService(db);

    private boolean tasksSelected = false;

    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> tasksListJunior;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label warningJunior;

    @FXML
    private Button doneJuniorButton;

    public void displayTasks() throws Exception {
        tasksListJunior.getItems().clear();
        tasks.addAll(service.getAllTasks()
                .stream()
                .filter(t -> t.getUserName().equals(Main.currentUser) && t.getDone()==0)
                .sorted((t1, t2) -> {
                    if (t1.getTaskName().compareTo(t2.getTaskName()) > 0)
                        return 1;
                    if (t1.getTaskName().equals(t2.getTaskName()))
                        return 0;
                    else return -1;
                })
                .collect(Collectors.toCollection(ArrayList::new)));
        tasksListJunior.setItems(tasks);
        tasksListJunior.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initialize() throws Exception {
        displayTasks();
        tasksListJunior.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                descriptionArea.setText(newValue.getDescription());
                tasksSelected = true;
            } else {
                descriptionArea.setText("");
                tasksSelected = false;
            }
        });
    }

    public void doneJunior(ActionEvent actionEvent) throws Exception {
        if (!tasksSelected)
            warningJunior.setText("Select a task!");
        else {
            warningJunior.setText("");
            Task t = tasksListJunior.getSelectionModel().getSelectedItem();
            t.setDone(1);
            service.updateTasks(t);

            tasksListJunior.getItems().remove(t);
            tasksListJunior.getSelectionModel().clearSelection();
        }
    }
}
