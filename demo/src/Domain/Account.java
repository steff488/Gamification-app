package Domain;

public class Account extends Entity {

    private String userName;
    private String pass;

    private int rank;

    public Account(int id, String userName, String pass, int rank) {
        super(id);
        this.userName = userName;
        this.pass = pass;
        this.rank = rank;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "User Id: " + Integer.toString(getId()) + " | Username: " + userName + " | Rank: " + rank;
    }
}
