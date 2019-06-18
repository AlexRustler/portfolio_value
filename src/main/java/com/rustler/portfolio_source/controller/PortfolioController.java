package com.rustler.portfolio_source.controller;

import com.rustler.portfolio_source.model.Stock;
import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.Value;
import com.rustler.portfolio_source.service.PortfolioService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Api
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PutMapping("/")
    public ResponseEntity<Value> getPortfolioValue(@RequestBody Stocks stocks) {
        Value portfolio_value;
        portfolio_value = this.portfolioService.calculateValue(stocks);

        return ResponseEntity.status(HttpStatus.OK).body(portfolio_value);
    }

}
