package com.sportradar.fwcsb.domain.rules.ex;

abstract class BusinessRuleException extends Exception {
    BusinessRuleException(final String message) {
        super("[ BusinessRuleException ]: " + message);
    }
}
