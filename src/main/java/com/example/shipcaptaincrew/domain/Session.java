package com.example.shipcaptaincrew.domain;

import com.example.shipcaptaincrew.utils.SortedScoreboard;
import lombok.Data;

import java.util.*;

@Data
public class Session {
    Integer id;

    Date date = new Date();

    LinkedList<Player> players = new LinkedList<>();

    private int roundsRemaining=1;

    Map<String,Game> games = new HashMap<>();

    SortedScoreboard scoreboard = new SortedScoreboard();

    public Session(Collection<Player> players) {
        this.players.addAll(players);
        for(Player player : players) {
            games.put(player.name, new Game());
            scoreboard.put(player.name, 0);
        }
    }
    private void resetGame(String playerName) {
        games.replace(playerName, new Game());
    }

    private int currentPlayerIndex;
    Player currentPlayer;

    boolean ended;

    public void nextTurn() {
        scoreboard.replace(
                currentPlayer.name,
                scoreboard.get(currentPlayer.name) + games.get(currentPlayer.name).cargo
        );

        if(++currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
            roundsRemaining--;
        }

        if(roundsRemaining > 0) {
            currentPlayer = players.get(currentPlayerIndex);
            resetGame(currentPlayer.name);
        } else {
            ended =true;
        }
    }

    public Game getGame(String playerName) {
        return games.get(playerName);
    }

    public Player getPlayer(String name) {
        return players.stream().filter(player -> player.getName().equals(name)).findAny().orElse(null);
    }

    public String getState() {
        if(ended)
            return "Finished";
        else
            return "Ongoing";
    }
}
