package com.kasiudyk.kpi.persistence.impl;


import com.kasiudyk.kpi.persistence.TenderRepository;
import com.kasiudyk.kpi.service.model.Tender;
import com.kasiudyk.kpi.service.model.TenderStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TenderRepositoryImpl implements TenderRepository {
    private final List<Tender> tendersDB;
    private Long idCounter;

    public TenderRepositoryImpl() {
        this.tendersDB = new ArrayList<>();
        this.idCounter = 1L;
    }

    @Override
    public List<Tender> findAllActiveTenders() {
        return tendersDB.stream()
                .filter(t -> t.getTenderStatus().equals(TenderStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tender> getUserCreateTender(Long userId) {
        return  tendersDB.stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Tender getTenderById(Long tenderId) {
        return tendersDB.stream()
                .filter(t -> t.getId().equals(tenderId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createTender(Tender tender) {
        tender.setId(idCounter++);
        tendersDB.add(tender);
    }

    @Override
    public void removeTender(Long tenderId) {
        tendersDB.removeIf(t -> t.getId().equals(tenderId));
    }
}
