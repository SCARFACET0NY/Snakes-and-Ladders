package com.anton.snl.service;

import com.anton.snl.exception.GameNotActiveException;
import com.anton.snl.model.Game;
import com.anton.snl.model.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
public class GameService {
    public static final int BOARD_SIZE = 100;
    public static final int DICE_SIZE = 6;
    private final Game game;

    public Game startGame(Token token) {
        game.setBoard(new int[BOARD_SIZE]);
        game.setToken(token);
        game.getBoard()[token.getPosition()] += token.getPlayerNumber();
        game.setActive(true);

        return game;
    }

    public void clearOldPosition(Token token) {
        game.getBoard()[token.getPosition()] -= token.getPlayerNumber();
    }

    public void assignNewPosition(Token token) {
        int roll = game.rollDie(DICE_SIZE);
        int position = token.getPosition();

        if (roll + position < BOARD_SIZE) {
            token.setPosition(position + roll);
        }

        token.setLastRoll(roll);
    }

    public void moveToken(Token token) {
        assignNewPosition(token);
        game.getBoard()[token.getPosition()] += token.getPlayerNumber();
        game.setToken(token);
    }

    public void checkWin(Token token) {
        if (token.getPosition() == BOARD_SIZE - 1) {
            token.setWinner(true);
            game.setActive(false);
        }
    }

    public Game turn(Token token) {
        if (!game.isActive()) {
            throw new GameNotActiveException("You should start game before making turns");
        }

        clearOldPosition(token);
        moveToken(token);
        checkWin(token);

        return game;
    }
}
