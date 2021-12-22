package com.kasiudyk.kpi.web.controller;

import com.kasiudyk.kpi.service.TenderService;
import com.kasiudyk.kpi.service.model.Tender;
import com.kasiudyk.kpi.service.model.TenderStatus;
import com.kasiudyk.kpi.web.dto.BetDto;
import com.kasiudyk.kpi.web.dto.LoginUserDto;
import com.kasiudyk.kpi.web.dto.TenderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tender")
public class TenderController {

    private final TenderService tenderService;

    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping("/allActiveTenders")
    public String allActiveTenders(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            model.addAttribute("Tenderss", tenderService.getActiveTenders());
            return "tenders-page";
        }
    }

    @GetMapping("/allUserCreatedTenders")
    public String allUserCreatedTenders(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            model.addAttribute("Tenders", tenderService.getUserCreateTenders(userId));
            return "tenders-";
        }
    }


    @GetMapping("/{tenderId}")
    public String getTenderPage(Model model, HttpSession session, @PathVariable Long tenderId,
                             @ModelAttribute("url") String url) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            Tender tender = tenderService.getTenderById(tenderId);
            model.addAttribute("tender", new TenderDto()
                    .setId(tender.getId())
                    .setName(tender.getName())
                    .setDescription(tender.getDescription())
                    .setStartBet(tender.getStartBet())
                    .setActiveBet(tender.getActiveBet())
                    .setStatus(tender.getTenderStatus().toString())
            );

            if(tender.getUser().getId().equals(userId)) {
                model.addAttribute("url", url);
                return "owner-tender";
            } else {
                model.addAttribute("bet", new BetDto());
                return "tender";
            }
        }
    }


    @GetMapping("/createTender")
    public String getPageCreateTender(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            model.addAttribute("tender", new TenderDto());
            return "create-tender";
        }
    }

    @PostMapping("/createTender")
    public String createTender(Model model, HttpSession session,
                            @ModelAttribute("tender") TenderDto tenderDto) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            tenderService.createTender(userId, new Tender()
                    .setTenderStatus(TenderStatus.CREATED)
                    .setName(tenderDto.getName())
                    .setDescription(tenderDto.getDescription())
                    .setStartBet(tenderDto.getStartBet())
            );

            return "redirect:/tender/allUserCreatedTenders";
        }
    }

    @PostMapping("start/{tenderId}")
    public String startTender(Model model, HttpSession session, @PathVariable Long tenderId) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            tenderService.startTender(tenderId);
            return "redirect:/tender/" + tenderId;
        }
    }

    @PostMapping("stop/{tenderId}")
    public String stopTender(Model model, HttpSession session, @PathVariable Long tenderId) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            tenderService.stopTender(tenderId);
            return "redirect:/tender/" + tenderId;
        }
    }

    @PostMapping("remove/{tenderId}")
    public String removeTender(Model model, HttpSession session, @PathVariable Long tenderId) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            tenderService.removeTender(tenderId);
            return "redirect:/tender/allUserCreatedTenders";
        }


    }

    @GetMapping("search")
    public String search(Model model, HttpSession session, @RequestParam String search) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            List<Tender> tenders = tenderService.getActiveTenders(search);
            model.addAttribute("tenders", tenders);
            return "tenders-page";
        }
    }

    @GetMapping("generateUrl/{tenderId}")
    public String generateUrl(Model model, HttpSession session, HttpServletRequest request,
                              RedirectAttributes ra, @PathVariable Long tenderId) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            model.addAttribute("error", "You must login");
            model.addAttribute("loginUserDto", new LoginUserDto());
            return "redirect:/user/login";
        } else {
            String url = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath("tender/" + tenderId)
                    .build()
                    .toUriString();

            ra.addAttribute("url", url);
            return "redirect:/tender/" + tenderId;
        }
    }


}
