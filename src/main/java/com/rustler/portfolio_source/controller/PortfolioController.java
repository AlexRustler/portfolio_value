package com.rustler.portfolio_source.controller;

import com.rustler.portfolio_source.controller.exceptions.NotFoundException;
import com.rustler.portfolio_source.model.Stocks;
import com.rustler.portfolio_source.model.PortfolioValue;
import com.rustler.portfolio_source.service.PortfolioService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PutMapping("/value")
    public PortfolioValue putPortfolioValue(@Valid @RequestBody Stocks stocks) throws NotFoundException {
        return this.portfolioService.calculateValue(stocks);
    }

}
