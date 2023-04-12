package main;

import Domain.Account;
import Domain.Rank;
import Domain.Task;
import Repository.Database;
import Service.DatabaseService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;

    public static String currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception {

        populate();

        stage = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        primaryStage.setTitle("Login page");
        primaryStage.setScene(new Scene(root, 270, 320));
        primaryStage.show();
    }

    public void changeScene(String fxmlPage, String title, int height, int width) throws Exception {
        Parent pane = FXMLLoader.load(getClass().getResource(fxmlPage));
        stage.getScene().setRoot(pane);
        stage.setTitle(title);
        stage.setHeight(height);
        stage.setWidth(width);
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void populate() {
        Database db = new Database("database.db");
        DatabaseService dbService = new DatabaseService(db);

        try {
            db.createTable("users");
            db.createTable("tasks");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Rank adminRank = new Rank(1, "Admin");
            Rank seniorRank = new Rank(2, "Senior");
            Rank juniorRank = new Rank(3, "Junior");

            dbService.addRank(adminRank);
            dbService.addRank(seniorRank);
            dbService.addRank(juniorRank);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Account user1 = new Account(1, "Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", 1);
            Account user2 = new Account(2, "Elena", "0ce93c9606f0685bf60e051265891d256381f639d05c0aec67c84eec49d33cc1", 2);
            Account user3 = new Account(3, "Alex", "4135aa9dc1b842a653dea846903ddb95bfb8c5a10c504a7fa16e10bc31d1fdf0", 3);

            dbService.addUser(user1);
            dbService.addUser(user2);
            dbService.addUser(user3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Task task1 = new Task(1, "Task1", "Easy", "Description1", "Unassigned", 0);
            Task task2 = new Task(2, "Task2", "Hard", "Description2", "Unassigned", 0);
            Task task3 = new Task(3, "Task3", "Medium", "Description3", "Unassigned", 0);
            Task task4 = new Task(4, "Task4", "Easy", "Description4", "Unassigned", 0);
            Task task5 = new Task(5, "Task5", "Hard", "Description5", "Unassigned", 0);
            Task task6 = new Task(6, "Task6", "Medium", "Description6", "Unassigned", 0);
            Task task7 = new Task(7, "Task7", "Easy", "Description7", "Unassigned", 0);
            Task task8 = new Task(8, "Task8", "Easy", "Description8", "Unassigned", 0);
            Task task9 = new Task(9, "Task9", "Hard", "Description9", "Unassigned", 0);

            dbService.addTask(task1);
            dbService.addTask(task2);
            dbService.addTask(task3);
            dbService.addTask(task4);
            dbService.addTask(task5);
            dbService.addTask(task6);
            dbService.addTask(task7);
            dbService.addTask(task8);
            dbService.addTask(task9);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
