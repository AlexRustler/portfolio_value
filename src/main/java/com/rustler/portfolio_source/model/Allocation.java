package com.rustler.portfolio_source.model;

public class Allocation {
    private String sector;
    private Double assetValue;
    private Double proportion;

    public Allocation(String sector, Double assetValue, Double proportion) {
        this.sector = sector;
        this.assetValue = assetValue;
        this.proportion = proportion;
    }

    public String getSector() {
        return sector;
    }

    public Double getAssetValue() {
        return assetValue;
    }

    public Double getProportion() {
        return proportion;
    }

}

