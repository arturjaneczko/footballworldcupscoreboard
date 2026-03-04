package com.sportradar.fwcsb.domain.game.team;

import java.util.Objects;

abstract class AbstractTeam implements Team {

    private final String name;

    protected AbstractTeam(final String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public abstract Origin getOrigin();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTeam that = (AbstractTeam) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
