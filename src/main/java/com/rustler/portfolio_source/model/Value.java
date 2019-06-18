package com.rustler.portfolio_source.model;

import java.util.ArrayList;
import java.util.Map;

public class Value {
    private Double value;
    private ArrayList<Allocation> allocations;

    public Value() {
        this.value = 0.0;
        this.allocations = new ArrayList<Allocation>();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public ArrayList<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(ArrayList<Allocation> allocations) {
        this.allocations = allocations;
    }

    public void addValue(Double value) {
        this.value+=value;
    }

    public void calculateProportions(Map<String, Double> sectors) {
        for (Map.Entry<String, Double> entry : sectors.entrySet()) {
            this.allocations.add(new Allocation(entry.getKey(), entry.getValue(), entry.getValue()/this.value));
        }
    }
}
