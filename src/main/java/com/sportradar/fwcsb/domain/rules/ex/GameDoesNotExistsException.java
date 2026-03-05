package com.sportradar.fwcsb.domain.rules.ex;

public class GameDoesNotExistsException extends BusinessRuleException {

    public GameDoesNotExistsException() {
        super("Game does not exists!");
    }
}
