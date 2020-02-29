package com.example.shipcaptaincrew.controllers;

import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.domain.requests.CreateNewPlayerRequest;
import com.example.shipcaptaincrew.domain.requests.CreateNewSessionRequest;
import com.example.shipcaptaincrew.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Value("${title}")
    String title;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("title",title);

        model.addAttribute("players", playerService.getAllPlayers());

        model.addAttribute("createNewPlayerRequest", new CreateNewPlayerRequest());

        model.addAttribute("createNewSessionRequest", new CreateNewSessionRequest());

        return "home";
    }

    @PostMapping
    public String addPlayer(@ModelAttribute CreateNewPlayerRequest request) {
        if(!request.getNameInputField().trim().isEmpty()) {
            playerService.createNewPlayer(request);
        }

        return "redirect:/";
    }

    @GetMapping("/action")
    public String action(@RequestParam String name) {
        switch(name) {
            case "Reset":
                playerService.reset();
                break;
        }

        return "redirect:/";
    }
}
