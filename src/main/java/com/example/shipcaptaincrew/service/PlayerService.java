package com.example.shipcaptaincrew.service;

import com.example.shipcaptaincrew.domain.Player;
import com.example.shipcaptaincrew.domain.Session;
import com.example.shipcaptaincrew.domain.repository.PlayerRepository;
import com.example.shipcaptaincrew.domain.requests.CreateNewPlayerRequest;
import com.example.shipcaptaincrew.utils.SortedScoreboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository repository;

    public void createNewPlayer(Player player) {
        repository.addPlayer(player);
    }

    public Collection<Player> getAllPlayers() {
        return repository.getAllPlayers();
    }

    public Player getPlayer(String name) { return repository.getPlayer(name); }

    public void reset() {
        repository.reset();
    }

    public void createNewPlayer(CreateNewPlayerRequest request) {
        createNewPlayer(new Player(request.getNameInputField()));
    }

    public LinkedList<Player> playerNamesToPlayerList(Collection<String> playerNames) {
        LinkedList<Player> players = new LinkedList<>();
        for(String name : playerNames)
            players.add(this.getPlayer(name));
        return players;
    }

    public void updatePlayersScore(Session session) {
        if(session.isEnded()) {
            int i=session.getScoreboard().getKeys().size()-1;
            String last="";
            for(String name : session.getScoreboard().getKeys()) {
                if (session.getScoreboard().get(name) > 0)
                    repository.getPlayer(name).setScore(i);
                if (last != name)
                    i--;
                last = name;
            }
        }
    }

    public LinkedList<Player> getAllPlayersOrderedByScore() {
        LinkedList<Player> linkedList = new LinkedList<>(getAllPlayers());
        linkedList.sort(Comparator.comparingInt(Player::getScore));
        Collections.reverse(linkedList);
        return linkedList;
    }
}
