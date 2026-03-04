package com.sportradar.fwcsb.domain.rules.ex;

class GameDoesNotExistsException extends BusinessRuleException {

    GameDoesNotExistsException() {
        super("Game does not exists!");
    }
}
