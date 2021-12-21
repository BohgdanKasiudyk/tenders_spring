package com.kasiudyk.kpi.web.controller;


import com.kasiudyk.kpi.service.BetService;
import com.kasiudyk.kpi.service.model.Bet;
import com.kasiudyk.kpi.web.dto.BetDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bet")
public class BetController {
    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/{userId}/{tenderId}")
    public String bet(Model model, @ModelAttribute BetDto betDto, @PathVariable Long userId, @PathVariable Long tenderId) {
        if (!betService.bet(userId, tenderId, new Bet().setAmount(betDto.getBetAmount()))) {
            model.addAttribute("error", "Invalid bet amount");
        }
        return "redirect:/tender/" + tenderId;
    }
}
