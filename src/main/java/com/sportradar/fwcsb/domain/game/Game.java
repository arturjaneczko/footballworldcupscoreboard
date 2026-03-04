package com.sportradar.fwcsb.domain.game;

import com.sportradar.fwcsb.domain.game.team.Team;

import java.util.Objects;

public class Game {

    private final Team homeTeam;
    private final Team awayTeam;

    public Game(final Team homeTeam, final Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return homeTeam.equals(game.homeTeam) && awayTeam.equals(game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }

}
