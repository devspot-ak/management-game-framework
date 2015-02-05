package biz.devspot.management.game.framework.model;

public class FixtureResult {

    public static final int DRAW = 0;
    public static final int HOME_WIN = 1;
    public static final int AWAY_WIN = 2;
    
    private int result;
    private int homeScore;
    private int awayScore;

    public FixtureResult(int result, int homeScore, int awayScore) {
        this.result = result;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getResult() {
        return result;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
    
}
