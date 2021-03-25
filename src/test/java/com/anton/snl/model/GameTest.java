package com.anton.snl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    public static final int DIE_SIZE = 6;

    @Test
    void dieRollResultIsBetweenOneAndSixInclusive() {
        Game game = new Game();
        int roll1 = game.rollDie(DIE_SIZE);
        int roll2 = game.rollDie(DIE_SIZE);
        int roll3 = game.rollDie(DIE_SIZE);
        int roll4 = game.rollDie(DIE_SIZE);
        int roll5 = game.rollDie(DIE_SIZE);

        assertTrue(roll1 >= 1 && roll1 <= 6);
        assertTrue(roll2 >= 1 && roll2 <= 6);
        assertTrue(roll3 >= 1 && roll3 <= 6);
        assertTrue(roll4 >= 1 && roll4 <= 6);
        assertTrue(roll5 >= 1 && roll5 <= 6);
    }
}