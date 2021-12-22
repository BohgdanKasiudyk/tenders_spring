package com.kasiudyk.kpi.service;

import com.kasiudyk.kpi.service.model.Bet;

public interface BetService {
    boolean bet(Long userId, Long tenderId, Bet bet);
}
