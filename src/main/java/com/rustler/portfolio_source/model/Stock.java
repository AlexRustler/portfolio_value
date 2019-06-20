package com.rustler.portfolio_source.model;

import javax.validation.constraints.*;

public class Stock {
    private String symbol;
    @Min(0)
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
