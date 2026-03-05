package com.sportradar.fwcsb.domain.game.engine.strategy;

import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.engine.ScoringStrategy;

public class SummationSystemScoringStrategy implements ScoringStrategy {

    @Override
    public int getTotalScore(final TeamScore home, final TeamScore away) {
        return home.score().value() + away.score().value();
    }

}
