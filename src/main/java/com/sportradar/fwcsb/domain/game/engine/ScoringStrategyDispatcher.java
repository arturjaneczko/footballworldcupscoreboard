package com.sportradar.fwcsb.domain.game.engine;

import com.sportradar.fwcsb.domain.game.engine.strategy.SummationSystemScoringStrategy;

public class ScoringStrategyDispatcher {

    public static ScoringStrategy scoringStrategy(final Scoring scoring) {
        return switch (scoring) {
            case SUMMATION_SYSTEM -> new SummationSystemScoringStrategy();
            default -> throw new IllegalStateException("Unsupported scoring strategy: " + scoring);
        };
    }
}
