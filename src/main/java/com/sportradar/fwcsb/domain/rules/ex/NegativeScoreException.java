package com.sportradar.fwcsb.domain.rules.ex;

public class NegativeScoreException extends BusinessRuleException {
    public NegativeScoreException() {
        super("Negative score!");
    }
}
