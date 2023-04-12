package Repository;

import Domain.Account;
import Domain.Entity;
import Domain.Rank;
import Domain.Task;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDatabase{

    protected String url;
    protected Connection conn = null;

    public AbstractDatabase(String url) {
        this.url = "jdbc:sqlite:" + url;
    }

    public boolean openConnection() {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(url);
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url,config.toProperties());
                conn.createStatement().execute("PRAGMA foreign_keys = ON;");
                System.out.println("Successfully connected!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean closeConnection() {
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    abstract public boolean createTable(String tableType);

    abstract public boolean addUser(Account obj);

    abstract public boolean addRank(Rank obj);

    abstract public boolean addTask(Task obj);

    abstract public boolean removeTask(Integer id);

    public abstract boolean updateTasks(Task obj);

    public abstract String userRank(String userName);

    public abstract Account getElem(Integer id);

    public abstract String getPassword(String userName, String password);

    public int size() {
        return 0;
    }

    public abstract ArrayList<Task> getAllTasks();

    public abstract ArrayList<Account> getAllUsers();
}
