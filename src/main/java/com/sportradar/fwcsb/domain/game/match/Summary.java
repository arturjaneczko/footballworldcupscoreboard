package com.sportradar.fwcsb.domain.game.match;

import java.util.Objects;

public class Summary {

    private static final String PATTERN = "%s - %s: %d - %d";
    private final Match match;

    public Summary(final Match match) {
        this.match = match;
    }

    public int getTotalScore() {
        return match.getHome().score() + match.getAway().score();
    }

    @Override
    public String toString() {
        final String homeName = match.getHome().team().getName();
        final String awayName = match.getAway().team().getName();
        final int homeScore = match.getHome().score();
        final int awayScore = match.getAway().score();
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
