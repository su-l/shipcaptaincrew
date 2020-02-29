package com.example.shipcaptaincrew.controllers;

import com.example.shipcaptaincrew.domain.Game;
import com.example.shipcaptaincrew.domain.Player;
import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.service.PlayerService;
import com.example.shipcaptaincrew.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Controller
public class GameController {
    @Value("${title} - Game")
    String title;

    @Autowired
    SessionService sessionService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/game")
    public String game(@RequestParam Integer sessionId, @RequestParam String playerName, @RequestParam(required = false) String action, Model model) {
       Session session = sessionService.getSession(sessionId);
       Player player = session.getPlayer(playerName);
       Game game = session.getGame(playerName);

       if(action != null) {
           switch (action) {
               case "roll":
                   game.roll();
                   break;
               case "stop":
                   game.stopRolling();
                   break;
               case "scoreboard":
                   return scoreboard(sessionId, model);
           }
       }

       if(game.areAllRollsDone())
            session.nextTurn();

       if(session.isEnded())
           playerService.updatePlayersScore(session);

       model.addAttribute("title", title);
       model.addAttribute("sessionId", sessionId);
       model.addAttribute("currentPlayer", session.getCurrentPlayer());
       model.addAttribute("players", session.getPlayers());
       model.addAttribute("scoreboard", session.getScoreboard());
       model.addAttribute("player", player);
       model.addAttribute("game", game);
       model.addAttribute("hasEnded", session.isEnded());

       return "game";
    }

    @GetMapping("/scoreboard")
    public String scoreboard(@RequestParam Integer sessionId, Model model) {
        Session session = sessionService.getSession(sessionId);

        model.addAttribute("title", title.concat(" - Scoreboard"));
        model.addAttribute("date", session.getDate());
        model.addAttribute("scoreboard", session.getScoreboard());

        return "scoreboard";
    }
}
