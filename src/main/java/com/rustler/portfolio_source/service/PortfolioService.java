package com.rustler.portfolio_source.service;

import com.rustler.portfolio_source.model.Stock;
import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.Value;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PortfolioService {

    public Value calculateValue(Stocks stocks) {
        Value portfolio_value = new Value();
        Map<String, Double> sectors = new ConcurrentHashMap<>();

        for (Stock stock:stocks.getStocks()
        ) {
            Double latestPrice = getLatestPrice(stock.getSymbol());
            String sector = getSector(stock.getSymbol());
            Double assetValue = latestPrice * stock.getVolume();
            portfolio_value.addValue(assetValue);
            sectors = updateSectors(sectors, sector, assetValue);

        }
        System.out.println(sectors);
        portfolio_value.calculateProportions(sectors);

        return portfolio_value;
    }

    private Double getLatestPrice(String symbol) {
        if (symbol.length()==3) {
            return 3.0;
        }
        else {
            return 2.0;
        }
    }

    private String getSector(String symbol) {
        if (symbol.length()==3) {
            return "Technology";
        }
        else {
            return "Healtcare";
        }
    }

    Map<String, Double> updateSectors(Map<String, Double> sectors, String sector, Double newValue) {

        Double oldValue = sectors.getOrDefault(sector, 0.0);

        sectors.put(sector, oldValue+newValue);
        return sectors;
    }
}
