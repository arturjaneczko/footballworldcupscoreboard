package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.service.Service;

import java.util.Optional;

public class Board {

    private final Service service;

    public Board(final Service service) {
        this.service = service;
    }

    public boolean startGame(final Team home, final Team away) {
        return service.startGame(new Game(home, away));
    }

    public boolean finishGame(final Team home, final Team away) {
        Optional<Game> game = service.findGame(home, away);
        return service.finishGame(game.orElseThrow());
    }

    public boolean updateScore(final TeamScore home, final TeamScore away) {
        return service.updateScore(null, null);
    }

    public boolean totalSummary() {
        return service.totalSummary();
    }
}
