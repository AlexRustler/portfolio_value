package com.rustler.portfolio_source.service;

import com.rustler.portfolio_source.controller.exceptions.NotFoundException;
import com.rustler.portfolio_source.model.Stock;
import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.PortfolioValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.exception.IEXTradingException;
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

    @Value("${iexcloud.publish.token}")
    private String TOKEN;

    public PortfolioValue calculateValue(Stocks stocks) throws NotFoundException{
        PortfolioValue portfolio_value = new PortfolioValue();
        Map<String, Double> sectors = new ConcurrentHashMap<>();
        String symbol = "";
        try {
            for (Stock stock : stocks.getStocks()
                    ) {
                symbol = stock.getSymbol();
                Double latestPrice = getLatestPrice(symbol);
                String sector = getSector(symbol);
                Double assetValue = latestPrice * stock.getVolume();
                portfolio_value.addValue(assetValue);
                sectors = updateSectors(sectors, sector, assetValue);
            }

            portfolio_value.calculateProportions(sectors);
        }
        catch (IEXTradingException e) {
            throw new NotFoundException(e.getMessage(), symbol);
        }
        return portfolio_value;
    }

    private Double getLatestPrice(String symbol) {
        final IEXCloudClient iexTradingClient = getIEXCloudClient();
        final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());
        return quote.getLatestPrice().doubleValue();
    }

    private String getSector(String symbol) {
        final IEXCloudClient iexTradingClient = getIEXCloudClient();
        final Company company = iexTradingClient.executeRequest(new CompanyRequestBuilder()
                .withSymbol(symbol)
                .build());
        return company.getSector();
    }

    private IEXCloudClient getIEXCloudClient() {
        IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(TOKEN)
                        .build());
        return iexTradingClient;
    }

    Map<String, Double> updateSectors(Map<String, Double> sectors, String sector, Double newValue) {

        Double oldValue = sectors.getOrDefault(sector, 0.0);
        sectors.put(sector, oldValue + newValue);
        return sectors;
    }
}
