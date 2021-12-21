package com.kasiudyk.kpi.web.dto;

public class BetDto {
    private double betAmount;

    public BetDto() {
    }

    public BetDto(double betAmount) {
        this.betAmount = betAmount;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public BetDto setBetAmount(double betAmount) {
        this.betAmount = betAmount;
        return this;
    }

}
