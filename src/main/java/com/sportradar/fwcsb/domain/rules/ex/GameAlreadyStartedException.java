package com.sportradar.fwcsb.domain.rules.ex;

class GameAlreadyStartedException extends BusinessRuleException {
    GameAlreadyStartedException(final String game) {
        super("Game already started! " + game);
    }
}
