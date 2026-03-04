package com.sportradar.fwcsb.domain.game.match;

import java.util.Objects;

public class Summary {

    private final Match match;

    public Summary(final Match match) {
        this.match = match;
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
