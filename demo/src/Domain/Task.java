package Domain;

public class Task extends Entity {

    private String taskName;
    private String difficulty;

    private String description;

    private String userName;

    private int done;

    public Task(int id, String taskName, String difficulty, String description, String userName, int done) {
        super(id);
        this.taskName = taskName;
        this.difficulty = difficulty;
        this.description = description;
        this.userName = userName;
        this.done = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task: " + taskName + " | Difficulty: " + difficulty + " | Description: " + description;
    }
}
