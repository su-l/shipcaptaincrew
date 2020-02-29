package com.example.shipcaptaincrew.controllers;

import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.domain.requests.CreateNewSessionRequest;
import com.example.shipcaptaincrew.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class SessionController {
    @Value("${title} - Lobby")
    String title;

    @Autowired
    SessionService sessionService;

    @PostMapping("/session")
    public String session(@ModelAttribute CreateNewSessionRequest request) {
        if(request.getSelectedNames().isEmpty()) {
            return "redirect:/";
        } else {
            Session session = sessionService.createNewSession(request);
            return "redirect:/session?id=".concat(session.getId().toString());
        }
    }

    @GetMapping("/session")
    public String session(@RequestParam Integer id, Model model) {
        model.addAttribute("title",title);

        Session session = sessionService.getSession(id);

        model.addAttribute("players", session.getPlayers());
        model.addAttribute("sessionId", id);

        model.addAttribute("scoreboard", session.getScoreboard());

        return "session";
    }
}
