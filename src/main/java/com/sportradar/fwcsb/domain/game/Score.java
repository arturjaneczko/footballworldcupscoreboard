package com.sportradar.fwcsb.domain.game;

public record Score(int value) {

    public static final Score INITIAL_SCORE = new Score(0);

}
