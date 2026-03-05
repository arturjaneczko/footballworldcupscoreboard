package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.api.Facade;
import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.domain.rules.BusinessRuleEngine;
import com.sportradar.fwcsb.service.Service;

import java.util.List;
import java.util.Optional;

public class Board implements Facade {

    private final Service service;

    public Board(final Service service) {
        this.service = service;
    }

    @Override
    public boolean startGame(final Team home, final Team away) {
        BusinessRuleEngine.checkTeamsOrigin(home, away);
        BusinessRuleEngine.checkIfGameCanBeStarted(service.findGame(home, away), home, away);

        return service.startGame(new Game(home, away));
    }

    @Override
    public boolean finishGame(final Team home, final Team away) {
        Optional<Game> game = service.findGame(home, away);

        BusinessRuleEngine.checkIfGameCanBeFinished(game);

        return service.finishGame(game.orElseThrow());
    }

    @Override
    public boolean updateScore(final TeamScore home, final TeamScore away) {
        Optional<Game> game = service.findGame(home.team(), away.team());
        Optional<Match> candidate = game.map(service::getMatch);

        BusinessRuleEngine.checkIfScoreCanBeUpdated(game, candidate, home, away);

        Match match = candidate.orElseThrow();
        match.updateScore(home, away);
        return service.updateScore(game.orElseThrow(), match);
    }

    @Override
    public List<Summary> totalSummary() {
        return service.totalSummary();
    }

}
