package com.kasiudyk.kpi.web.dto;

public class TenderDto {
    private Long id;
    private String name;
    private String description;
    private double startBet;
    private double activeBet;
    private String status;


    public TenderDto(Long id, String name, String description, double startBet, double activeBet, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startBet = startBet;
        this.activeBet = activeBet;
        this.status = status;
    }

    public TenderDto() {
    }

    public Long getId() {
        return id;
    }

    public TenderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TenderDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TenderDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getStartBet() {
        return startBet;
    }

    public TenderDto setStartBet(double startBet) {
        this.startBet = startBet;
        return this;
    }

    public double getActiveBet() {
        return activeBet;
    }

    public TenderDto setActiveBet(double activeBet) {
        this.activeBet = activeBet;
        return  this;
    }

    public String getStatus() {
        return status;
    }

    public TenderDto setStatus(String status) {
        this.status = status;
        return this;
    }
}
