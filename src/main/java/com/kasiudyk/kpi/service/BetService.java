package com.kasiudyk.kpi.service;

import com.kasiudyk.kpi.service.model.Bet;

public interface BetService {
    boolean bet(Long userId, Long lotId, Bet bet);
}
