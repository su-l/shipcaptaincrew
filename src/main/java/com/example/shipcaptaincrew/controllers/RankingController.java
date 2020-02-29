package com.example.shipcaptaincrew.controllers;

import com.example.shipcaptaincrew.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {
    @Value("${title} - Ranking")
    String title;

    @Autowired
    PlayerService playerService;

    @GetMapping("/ranking")
    public String ranking(Model model) {
        model.addAttribute("title", title);

        model.addAttribute("players", playerService.getAllPlayersOrderedByScore());

        return "ranking";
    }
}
