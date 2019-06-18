package com.rustler.portfolio_source.model;

import java.util.ArrayList;

public class Value {
    private Float value;
    private ArrayList<Allocation> allocations;

    public Value() {
        this.value = 0f;
        this.allocations = new ArrayList<Allocation>();
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public ArrayList<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(ArrayList<Allocation> allocations) {
        this.allocations = allocations;
    }
}
