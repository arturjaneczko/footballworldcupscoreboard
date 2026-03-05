package com.sportradar.fwcsb.api;

import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.Team;

import java.util.List;

public interface Facade {

    boolean startGame(final Team home, final Team away);

    boolean finishGame(final Team home, final Team away);

    boolean updateScore(final TeamScore home, final TeamScore away);

    List<Summary> totalSummary();

}
