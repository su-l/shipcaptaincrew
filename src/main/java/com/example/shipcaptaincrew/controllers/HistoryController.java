package com.example.shipcaptaincrew.controllers;

import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class HistoryController {
    @Value("${title} - History")
    String title;

    @Autowired
    SessionService sessionService;

    @GetMapping("/history")
    public String history(Model model) {
        LinkedList<Session> linkedList = new LinkedList<>(sessionService.getAllSessions());
        Collections.reverse(linkedList);
        model.addAttribute("sessions", linkedList);

        model.addAttribute("title", title);

        return "history";
    }
}
