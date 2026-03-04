package com.sportradar.fwcsb.domain.rules.ex;

class NegativeScoreException extends BusinessRuleException {
    NegativeScoreException() {
        super("Negative score!");
    }
}
