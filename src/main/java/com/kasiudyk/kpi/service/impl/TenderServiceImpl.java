package com.kasiudyk.kpi.service.impl;

import com.kasiudyk.kpi.persistence.TenderRepository;
import com.kasiudyk.kpi.persistence.UserRepository;
import com.kasiudyk.kpi.service.TenderService;
import com.kasiudyk.kpi.service.model.Tender;
import com.kasiudyk.kpi.service.model.TenderStatus;
import com.kasiudyk.kpi.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenderServiceImpl implements TenderService {

    TenderRepository tenderRepository;
    UserRepository userRepository;

    public TenderServiceImpl(TenderRepository tenderRepository, UserRepository userRepository) {
        this.tenderRepository = tenderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tender> getActiveTenders() {
        return tenderRepository.findAllActiveTenders();
    }

    @Override
    public List<Tender> getUserCreateTenders(Long userId) {
        return tenderRepository.getUserCreateTender(userId);
    }

    @Override
    public Tender getTenderById(Long tenderId) {
        return tenderRepository.getTenderById(tenderId);
    }

    @Override
    public void startTender(Long tenderId) {
        Tender tender = tenderRepository.getTenderById(tenderId);
        tender.setTenderStatus(TenderStatus.ACTIVE);

    }

    @Override
    public void stopTender(Long tenderId) {
        Tender tender = tenderRepository.getTenderById(tenderId);
        tender.setTenderStatus(TenderStatus.STOPPED);
    }

    @Override
    public void removeTender(Long tenderId) {
        tenderRepository.removeTender(tenderId);
    }

    @Override
    public void createTender(Long userId, Tender Tender) {
        User user = userRepository.getById(userId);
        Tender.setUser(user);
        tenderRepository.createTender(Tender);
    }

    @Override
    public String generateUrl(Long TenderId) {
        return null;
    }

    @Override
    public List<Tender> getActiveTenders(String searchString) {
        return getActiveTenders().stream()
                .filter(t -> t.getName().contains(searchString))
                .collect(Collectors.toList());
    }
}
