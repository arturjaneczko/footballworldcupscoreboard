package com.sportradar.fwcsb.domain.game.match;

import com.sportradar.fwcsb.domain.game.TeamScore;

public class Match {

    private TeamScore home;
    private TeamScore away;

    public Match(final TeamScore home, final TeamScore away) {
        this.home = home;
        this.away = away;
    }

    public void updateScore(final TeamScore home, final TeamScore away) {
        this.home = home;
        this.away = away;
    }

    public TeamScore getHome() {
        return home;
    }

    public TeamScore getAway() {
        return away;
    }
}
