package com.kasiudyk.kpi.service.model;

public class Bet {
    private Long id;
    private double amount;

    public Bet() {

    }

    public Bet(Long id, Long amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Bet setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Bet setAmount(double amount) {
        this.amount = amount;
        return this;
    }
}
