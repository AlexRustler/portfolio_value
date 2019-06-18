package com.rustler.portfolio_source.model;

import java.util.ArrayList;

public class Stocks {
    private Stock[] stocks;


    public Stock[] getStocks() {
        return stocks;
    }

    public void setStocks(Stock[] stocks_set) {
        this.stocks = stocks_set;
    }

    public Stock get(int index) {
        return this.stocks[index];
    }
}
