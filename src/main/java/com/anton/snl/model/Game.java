package com.anton.snl.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Component
public class Game {
    private int[] board;
    private Token token;
    private boolean isActive;

    public int rollDie(int diceSize) {
        return new Random().nextInt(diceSize) + 1;
    }
}
