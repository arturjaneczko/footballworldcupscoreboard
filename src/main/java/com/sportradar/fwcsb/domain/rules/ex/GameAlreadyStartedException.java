package com.sportradar.fwcsb.domain.rules.ex;

public class GameAlreadyStartedException extends BusinessRuleException {
    public GameAlreadyStartedException(final String game) {
        super("Game already started! " + game);
    }
}
