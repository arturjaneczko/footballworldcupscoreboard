package com.sportradar.fwcsb.domain.game.match;

import com.sportradar.fwcsb.domain.game.engine.ScoringStrategy;
import com.sportradar.fwcsb.domain.game.engine.ScoringStrategyDispatcher;

import java.util.Objects;

public class Summary {

    private static final String PATTERN = "%s - %s: %d - %d";
    private final Match match;

    public Summary(final Match match) {
        this.match = match;
    }

    public int getTotalScore() {
        ScoringStrategy scoringStrategy = ScoringStrategyDispatcher.scoringStrategy(match.getScoring());
        return scoringStrategy.getTotalScore(match.getHome(), match.getAway());
    }

    @Override
    public String toString() {
        final String homeName = match.getHome().team().getName();
        final String awayName = match.getAway().team().getName();
        final int homeScore = match.getHome().score().value();
        final int awayScore = match.getAway().score().value();
        return String.format(PATTERN, homeName, awayName, homeScore, awayScore);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return match.equals(summary.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(match);
    }

}
