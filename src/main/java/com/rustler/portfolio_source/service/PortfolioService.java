package com.rustler.portfolio_source.service;

import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.Value;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    public Value calculateValue(Stocks stocks) {
        Value result = new Value();
        return result;
    }
}
