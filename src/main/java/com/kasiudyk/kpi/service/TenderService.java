package com.kasiudyk.kpi.service;

import com.kasiudyk.kpi.service.model.Tender;

import java.util.List;

public interface TenderService {
    List<Tender> getActiveTenders();
    List<Tender> getUserCreateTenders(Long userId);
    Tender getTenderById(Long tenderId);
    void createTender(Long userId, Tender Tender);
    void startTender(Long tenderId);
    void stopTender(Long tenderId);
    void removeTender(Long tenderId);
    String generateUrl(Long TenderId);
    List<Tender> getActiveTenders(String searchString);
}
