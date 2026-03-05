package com.sportradar.fwcsb.domain.game.match;

import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.engine.Scoring;

import java.util.Objects;

public class Match {

    private TeamScore home;
    private TeamScore away;
    private Summary summary;
    private Scoring scoring;

    public Match(final TeamScore home, final TeamScore away) {
        this.home = home;
        this.away = away;
        this.summary = new Summary(this);
        this.scoring = Scoring.SUMMATION_SYSTEM;
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

    public Summary getSummary() {
        return summary;
    }

    public Scoring getScoring() {
        return scoring;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return home.equals(match.home) && away.equals(match.away);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, away);
    }
}
