package com.schmalfuss.market.exception;

public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException(String s) {
        super(s);
    }
}
