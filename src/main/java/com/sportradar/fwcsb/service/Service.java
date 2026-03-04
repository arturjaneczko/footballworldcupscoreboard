package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.team.Team;

import java.util.Optional;

public class Service {

    public boolean startGame(final Game game) {
        return false;
    }

    public boolean finishGame(final Game game) {
        return false;
    }

    public boolean updateScore() {
        return false;
    }

    public boolean totalSummary() {
        return false;
    }

    public Optional<Game> findGame(final Team home, final Team away) {
        return null;
    }
}
