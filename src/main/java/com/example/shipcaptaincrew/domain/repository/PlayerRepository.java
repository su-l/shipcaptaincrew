package com.example.shipcaptaincrew.domain.repository;

import com.example.shipcaptaincrew.domain.Player;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class PlayerRepository {
    private Map<String, Player> playersHashMap = new LinkedHashMap<>();

    public void addPlayer(Player player) {
        playersHashMap.put(player.getName(), player);
    }

    public Collection<Player> getAllPlayers() {
        return playersHashMap.values();
    }

    public Player getPlayer(String name) {
        return playersHashMap.get(name);
    }

    public void reset() {
        playersHashMap.clear();
    }

    @PostConstruct
    public void build() {
        addPlayer(new Player("DomyślnyAndrzej"));
        addPlayer(new Player("DomyślnyAdam"));
        addPlayer(new Player("DomyślnaAnna"));
        addPlayer(new Player("DomyślnaAmelia"));
    }
}
