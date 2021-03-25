package com.anton.snl.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    public static final int DIE_SIZE = 6;

    @Test
    void dieRollResultIsBetweenOneAndSixInclusive() {
        Game game = new Game();
        int roll = game.rollDie(DIE_SIZE);

        assertTrue(roll >= 1 && roll <= 6);
    }
}