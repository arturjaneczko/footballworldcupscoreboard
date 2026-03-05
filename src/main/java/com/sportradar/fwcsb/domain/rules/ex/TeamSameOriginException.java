package com.sportradar.fwcsb.domain.rules.ex;

public class TeamSameOriginException extends BusinessRuleException {
    public TeamSameOriginException() {
        super("Teams have the same origin!");
    }
}
