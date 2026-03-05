package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.Score;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.infra.Storage;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Service {

    private final Storage storage;

    public Service(final Storage storage) {
        this.storage = storage;
    }

    public boolean startGame(final Game game) {
        TeamScore home = new TeamScore(game.getHomeTeam(), Score.INITIAL_SCORE);
        TeamScore away = new TeamScore(game.getAwayTeam(), Score.INITIAL_SCORE);
        Match match = new Match(home, away);
        return Objects.isNull(storage.updateScoreboard(game, match));
    }

    public boolean finishGame(final Game game) {
        return Objects.nonNull(storage.removeFromScoreboard(game));
    }

    public boolean updateScore(final Game game, final Match match) {
        return Objects.nonNull(storage.updateScoreboard(game, match));
    }

    public List<Summary> totalSummary() {
        List<Summary> summaries = storage.getScoreboard().stream()
                .map(Match::getSummary)
                .collect(Collectors.toList());
        return summaries.stream()
                .sorted(Comparator.comparing(Summary::getTotalScore).thenComparingInt(summaries::indexOf).reversed())
                .collect(Collectors.toList());
    }

    public Optional<Game> findGame(final Team home, final Team away) {
        return storage.findGame(home, away);
    }

    public Match getMatch(final Game game) {
        return storage.getMatch(game);
    }

}
