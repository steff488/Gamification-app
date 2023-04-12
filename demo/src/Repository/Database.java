package Repository;

import Domain.Account;
import Domain.Entity;
import Domain.Rank;
import Domain.Task;

import java.sql.*;
import java.util.ArrayList;

public class Database extends AbstractDatabase {
    public Database(String url) {
        super(url);
    }

    @Override
    public boolean createTable(String tableType) {
        try {
            openConnection();
            final Statement s = conn.createStatement();
            switch (tableType) {
                case "users" -> {
                    s.executeUpdate("CREATE TABLE IF NOT EXISTS Ranks(RankId INTEGER PRIMARY KEY, RankName VARCHAR(20));");
                    s.executeUpdate("CREATE TABLE IF NOT EXISTS Users(UserName VARCHAR(50) PRIMARY KEY, Password VARCHAR(65), RankId INTEGER, FOREIGN KEY(RankId) REFERENCES Ranks(RankId));");
                }
                case "tasks" ->
                        s.executeUpdate("CREATE TABLE IF NOT EXISTS Tasks(TaskId INTEGER PRIMARY KEY, TaskName VARCHAR(20), Difficulty Varchar(10), Description Varchar(500), UserName Varchar(50) DEFAULT \"Unassigned\", Done INTEGER DEFAULT 0);");
            }
            s.close();

            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Couldn't create table " + tableType + "! - " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addUser(Account obj) {
        try {
            openConnection();

            PreparedStatement statement;
            statement = conn.prepareStatement("INSERT INTO Users VALUES (?, ?, ?);");
            statement.setString(1, obj.getUserName());
            statement.setString(2, obj.getPass());
            statement.setInt(3, obj.getRank());
            statement.executeUpdate();
            statement.close();

            closeConnection();
            return true;
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public boolean addRank(Rank obj) {
        try {
            openConnection();

            PreparedStatement statement;
            statement = conn.prepareStatement("INSERT INTO Ranks VALUES (?, ?);");
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getRankName());
            statement.executeUpdate();
            statement.close();

            closeConnection();
            return true;
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public boolean addTask(Task obj) {
        try {
            openConnection();

            PreparedStatement statement;
            statement = conn.prepareStatement("INSERT INTO Tasks VALUES (?, ?, ?, ?, ?, ?);");
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getTaskName());
            statement.setString(3, obj.getDifficulty());
            statement.setString(4, obj.getDescription());
            statement.setString(5, obj.getUserName());
            statement.setInt(6, obj.getDone());
            statement.executeUpdate();
            statement.close();

            closeConnection();
            return true;
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeTask(Integer id) {
        openConnection();
        try {
            Statement statement;
            statement = conn.createStatement();
            String text = "DELETE FROM Tasks WHERE TaskId = " + Integer.toString(id) + ";";
            System.out.println(text);
            statement.executeUpdate(text);

            closeConnection();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        try {
            openConnection();

            ArrayList<Task> tasks = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Tasks");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Task t = new Task(
                        rs.getInt("TaskId"),
                        rs.getString("TaskName"),
                        rs.getString("Difficulty"),
                        rs.getString("Description"),
                        rs.getString("UserName"),
                        rs.getInt("Done"));
                tasks.add(t);
            }
            rs.close();
            statement.close();

            closeConnection();
            return tasks;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ArrayList<Task>();
    }

    @Override
    public ArrayList<Account> getAllUsers() {
        try {
            openConnection();

            ArrayList<Account> users = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                Account u = new Account(
                        i,
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getInt("RankId"));
                users.add(u);
                i++;
            }
            rs.close();
            statement.close();

            closeConnection();
            return users;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return new ArrayList<Account>();
    }


    @Override
    public String userRank(String userName) {
        try {
            openConnection();

            Statement statement;
            statement = conn.createStatement();
            String query = "SELECT RankName FROM Ranks WHERE RankId IN (SELECT RankId FROM Users WHERE UserName = '" + userName + "');";
            ResultSet rs = statement.executeQuery(query);
            String pass = rs.getString(1);

            closeConnection();
            return pass;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean updateTasks(Task obj) {
        openConnection();
        try {
            Statement statement;
            statement = conn.createStatement();
            String text = "UPDATE Tasks SET UserName = '" + obj.getUserName() + "', Done = " + Integer.toString(obj.getDone()) + " WHERE TaskId = " + Integer.toString(obj.getId()) + ";";
            statement.executeUpdate(text);

            closeConnection();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Account getElem(Integer id) {
        return null;
    }

    @Override
    public String getPassword(String userName, String password) {
        openConnection();
        try {
            Statement statement;
            statement = conn.createStatement();
            String query = "SELECT Password FROM Users WHERE UserName = '" + userName + "';";
            ResultSet rs = statement.executeQuery(query);
            String pass = rs.getString(1);
            return pass;
        } catch (SQLException e) {
            System.out.println("Can't log in! " + e.getMessage());
            return null;
        }
    }
}
