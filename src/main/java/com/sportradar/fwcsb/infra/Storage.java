package com.sportradar.fwcsb.infra;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.match.Match;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Storage {

    private static final Map<Game, Match> SCOREBOARD = new HashMap<>();

    public boolean updateScoreboard(final Game game, final Match match) {
        return Objects.isNull(SCOREBOARD.put(game, match));
    }

    public boolean removeFromScoreboard(final Game game) {
        return Objects.nonNull(SCOREBOARD.remove(game));
    }

    public Collection<Match> getScoreboard() {
        return SCOREBOARD.values();
    }
}
