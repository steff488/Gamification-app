package Domain;

public class Rank extends Entity {
    private String rankName;

    public Rank(int id, String rankName) {
        super(id);
        this.rankName = rankName;
    }

    public String getRankName() {
        return rankName;
    }
}
