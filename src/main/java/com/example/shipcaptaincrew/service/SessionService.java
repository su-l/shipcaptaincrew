package com.example.shipcaptaincrew.service;

import com.example.shipcaptaincrew.domain.Player;
import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.domain.repository.SessionRepository;
import com.example.shipcaptaincrew.domain.requests.CreateNewSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

@Service
public class SessionService {
    @Autowired
    SessionRepository repository;

    @Autowired
    PlayerService playerService;

    public Session createNewSession(CreateNewSessionRequest request) {
        Session session = repository.createNewSession(
                playerService.playerNamesToPlayerList(request.getSelectedNames())
        );

        session.setCurrentPlayer(session.getPlayers().getFirst());

        return session;
    }

    public Session getSession(Integer id) {
        return repository.getSesion(id);
    }

    public Collection<Session> getAllSessions() {
        return repository.getAllSessions();
    }
}
