package com.example.shipcaptaincrew.utils;

import lombok.Data;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.util.*;
import java.util.stream.Collectors;

public class SortedScoreboard {
    Map<String,Integer> scoreboard = new LinkedHashMap<>();

    public void put(String playerName, Integer points) {
        scoreboard.put(playerName, points);
    }

    private void sort() {
        scoreboard = scoreboard.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void replace(String playerName, Integer points) {
        scoreboard.replace(playerName, points);
        sort();
    }

    public Integer get(String playerName) {
        return scoreboard.get(playerName);
    }

    public Collection<String> getKeys() {
        return scoreboard.keySet();
    }

    public Collection<Integer> getValues() {
        return scoreboard.values();
    }

    public String getWinnerString() {
        String first = scoreboard.keySet().iterator().next();
        if(scoreboard.get(first) <= 0)
            return "There are no winners.";
        else
            return first.concat(" is the winner!");
    }

    public String getWinnerName() {
        String first = scoreboard.keySet().iterator().next();
        if(scoreboard.get(first) <= 0)
            return "None";
        else
            return first;
    }
}
