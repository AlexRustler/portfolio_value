package com.rustler.portfolio_source.service;

import com.rustler.portfolio_source.model.Stock;
import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.Value;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.stocks.Company;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.CompanyRequestBuilder;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class PortfolioService {

    private static final String TOKEN = "";

    public Value calculateValue(Stocks stocks) {
        Value portfolio_value = new Value();
        Map<String, Double> sectors = new ConcurrentHashMap<>();

        for (Stock stock : stocks.getStocks()
        ) {
            Double latestPrice = getLatestPrice(stock.getSymbol());
            String sector = getSector(stock.getSymbol());
            Double assetValue = latestPrice * stock.getVolume();
            portfolio_value.addValue(assetValue);
            sectors = updateSectors(sectors, sector, assetValue);
        }

        portfolio_value.calculateProportions(sectors);
        return portfolio_value;
    }

    private Double getLatestPrice(String symbol) {
        final IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(TOKEN)
                        .build());
        final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());
        return quote.getLatestPrice().doubleValue();

    }

    private String getSector(String symbol) {

    final IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
            new IEXCloudTokenBuilder()
                    .withPublishableToken(TOKEN)
                    .build());
    final Company company = iexTradingClient.executeRequest(new CompanyRequestBuilder()
            .withSymbol(symbol)
            .build());
    return company.getSector();
}




    Map<String, Double> updateSectors(Map<String, Double> sectors, String sector, Double newValue) {

        Double oldValue = sectors.getOrDefault(sector, 0.0);

        sectors.put(sector, oldValue + newValue);
        return sectors;
    }
}
