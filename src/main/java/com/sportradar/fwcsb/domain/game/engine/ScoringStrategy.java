package com.sportradar.fwcsb.domain.game.engine;

import com.sportradar.fwcsb.domain.game.TeamScore;

@FunctionalInterface
public interface ScoringStrategy {

    int getTotalScore(TeamScore home, TeamScore away);

}
