package com.example.shipcaptaincrew.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.List;
import java.util.*;

@Getter
public class Game {
    private int n=5; // number of dices
    private int tries=3;

    LinkedList<Integer> rolls;

    int ship;
    int captain;
    int crew;

    int cargo;

    int rollSingleDice() { // number between one and six
        return (int)(Math.random()*6)+1;
    }

    LinkedList<Integer> rollAllDices() {
        LinkedList<Integer> dices = new LinkedList<>();
        for(int i=0; i<n; i++)
            dices.add(rollSingleDice());

        dices.sort(Collections.reverseOrder());

        return dices;
    }

    public void roll() {
        if(tries > 0) {
            rolls = rollAllDices();
            LinkedList<Integer> rollsCopy = new LinkedList<>(rolls);

            for (int roll : rolls) {
                if (ship != 6 && roll == 6) {
                    ship = 6;
                    rollsCopy.remove(Integer.valueOf(6));
                    n--;
                }
                if(ship == 6) {
                    if (captain != 5 && roll == 5) {
                        captain = 5;
                        rollsCopy.remove(Integer.valueOf(5));
                        n--;
                    }
                    if(captain == 5) {
                        if (crew != 4 && roll == 4) {
                            crew = 4;
                            rollsCopy.remove(Integer.valueOf(4));
                            n--;
                        }
                    }
                }
            }

            if(isShipCaptainCrew())
                cargo = rollsCopy.stream().mapToInt(Integer::intValue).sum();

            tries--;
        }
    }

    public boolean areAllRollsDone() {
        return tries <= 0;
    }

    public boolean isShipCaptainCrew() {
        return ship==6 && captain==5 && crew==4;
    }

    public String triesToString() {
       if(tries <= 0)
           return "none";
       else
           return Integer.toString(tries);
    }

    public void stopRolling() {
        if(isShipCaptainCrew())
            tries=0;
    }
}
