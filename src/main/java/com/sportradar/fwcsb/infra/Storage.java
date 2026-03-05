package com.sportradar.fwcsb.infra;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.match.Match;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Storage {

    private static final Map<Game, Match> SCOREBOARD = new LinkedHashMap<>();

    public Match updateScoreboard(final Game game, final Match match) {
        return SCOREBOARD.put(game, match);
    }

    public Match removeFromScoreboard(final Game game) {
        return SCOREBOARD.remove(game);
    }

    public Collection<Match> getScoreboard() {
        return SCOREBOARD.values();
    }

    public Match getMatch(final Game game) {
        return SCOREBOARD.get(game);
    }

}
