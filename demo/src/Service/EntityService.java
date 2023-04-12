package Service;

import Domain.Account;
import Domain.Entity;
import Domain.Rank;
import Domain.Task;
import Repository.AbstractDatabase;

import java.util.ArrayList;

public abstract class EntityService {

    private final AbstractDatabase repo;

    public EntityService(AbstractDatabase repo) {
        this.repo = repo;
    }

    public void addUser(Account obj) throws Exception {
        if (!repo.addUser(obj))
            throw new Exception("The id already exists!");
    }

    public void addRank(Rank obj) throws Exception {
        if (!repo.addRank(obj))
            throw new Exception("The id already exists!");
    }

    public void addTask(Task obj) throws Exception {
        if (!repo.addTask(obj))
            throw new Exception("The id already exists!");
    }

    public String userRank(String userName){
        return repo.userRank(userName);
    }

    public void removeTask(Integer id) throws Exception {
        if (!repo.removeTask(id))
            throw new Exception("This id doesn't exist!");
    }

    public void updateTasks(Task obj) throws Exception {
        if (!repo.updateTasks(obj))
            throw new Exception("Couldn't update Tasks table!");
    }

    public Account getById(Integer id) throws Exception {
        var piu = repo.getElem(id);
        if (piu == null)
            throw new Exception("This id doesn't exist!");
        else
            return piu;
    }

    public int size() {
        return repo.size();
    }

    public ArrayList<Task> getAllTasks() {
        return repo.getAllTasks();
    }

    public ArrayList<Account> getAllUsers() {
        return repo.getAllUsers();
    }

    public Entity getElem(Integer id){
        return repo.getElem(id);
    }

    public String getPassword(String userName, String password) {
        return repo.getPassword(userName, password);
    }
}
