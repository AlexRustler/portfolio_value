package com.rustler.portfolio_source.model;

public class Stock {
    private String symbol;
    private Integer volume;

    public Stock(String symbol, Integer volume) {
        this.symbol = symbol;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getVolume() {
        return volume;
    }

}
