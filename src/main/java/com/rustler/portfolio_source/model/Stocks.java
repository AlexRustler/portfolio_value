package com.rustler.portfolio_source.model;

import javax.validation.Valid;
import java.util.ArrayList;

public class Stocks {
    @Valid
    private Stock[] stocks;


    public Stock[] getStocks() {
        return stocks;
    }

}
