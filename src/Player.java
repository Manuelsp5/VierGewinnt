public class Player {

    private String name;
    private int wins;
    private int losses;
    private int draws;

    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public void addWins() { wins++; }

    public void addLosses() { losses++; }

    public void addDraws() { draws++; }

    @Override
    public String toString() {
        return name + " | Wins: " + wins + " | Losses: " + losses + " | Draws: " + draws;
    }

}

