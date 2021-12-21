package com.kasiudyk.kpi.service.impl;

import com.kasiudyk.kpi.persistence.TenderRepository;
import com.kasiudyk.kpi.service.BetService;
import com.kasiudyk.kpi.service.model.Bet;
import com.kasiudyk.kpi.service.model.Tender;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {

    private final TenderRepository tenderRepository;

    public BetServiceImpl(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    @Override
    public boolean bet(Long userId, Long lotId, Bet bet) {
        Tender tender = tenderRepository.getTenderById(lotId);
        if(tender.getActiveBet() <= bet.getAmount()) {
            return false;
        } else {
            tender.setActiveBet(bet.getAmount());
            return true;
        }
    }
}
