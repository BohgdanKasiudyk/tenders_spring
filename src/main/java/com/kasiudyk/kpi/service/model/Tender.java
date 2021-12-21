package com.kasiudyk.kpi.service.model;

public class Tender {
    private Long id;
    private String name;
    private String description;
    private double startBet;
    private double activeBet;
    private TenderStatus TenderStatus;
    private User user;

    public Tender() {
    }

    public Tender(Long id, String name, String description, double startBet, double activeBet, TenderStatus TenderStatus,
               User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startBet = startBet;
        this.activeBet = activeBet;
        this.TenderStatus = TenderStatus;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Tender setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tender setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Tender setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getStartBet() {
        return startBet;
    }

    public Tender setStartBet(double startBet) {
        this.startBet = startBet;
        return this;
    }

    public double getActiveBet() {
        return activeBet;
    }

    public Tender setActiveBet(double activeBet) {
        this.activeBet = activeBet;
        return this;
    }

    public TenderStatus getTenderStatus() {
        return TenderStatus;
    }

    public Tender setTenderStatus(TenderStatus lotStatus) {
        this.TenderStatus = lotStatus;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Tender setUser(User user) {
        this.user = user;
        return this;
    }
}
