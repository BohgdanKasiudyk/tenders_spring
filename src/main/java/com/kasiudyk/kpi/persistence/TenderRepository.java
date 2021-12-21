package com.kasiudyk.kpi.persistence;

import com.kasiudyk.kpi.service.model.Tender;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TenderRepository {

    List<Tender> findAllActiveTenders();
    List<Tender> getUserCreateTender(Long userId);
    Tender getTenderById(Long tenderId);
    void createTender(Tender tender);
    void removeTender(Long tenderId);
}
