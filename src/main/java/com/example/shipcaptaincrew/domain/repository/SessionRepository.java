package com.example.shipcaptaincrew.domain.repository;

import com.example.shipcaptaincrew.domain.Player;
import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.utils.SessionIdGenerator;
import org.springframework.stereotype.Repository;
import org.springframework.util.IdGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Repository
public class SessionRepository {
    private Map<Integer, Session> sessionHashMap = new HashMap<>();

    public Session createNewSession(LinkedList<Player> players) {
        Session session = new Session(players);

        session.setId(
                SessionIdGenerator.getUniqueId()
        );
        sessionHashMap.put(session.getId(), session);

        return session;
    }

    public Session getSesion(Integer id) {
        return sessionHashMap.get(id);
    }

    public Collection<Session> getAllSessions() {
        return sessionHashMap.values();
    }
}
