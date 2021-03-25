package com.anton.snl.service;

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
    public static final int BOARD_SIZE = 10;
    public static final int DICE_SIZE = 6;
    private final Game game;

    public Game startGame(Token token) {
        game.setBoard(new int[BOARD_SIZE][BOARD_SIZE]);
        game.setToken(token);
        game.getBoard()[token.getCoordinateY()][token.getCoordinateX()] += token.getPlayerNumber();
        game.setActive(true);

        return game;
    }

    public void assignNewPosition(Token token) {
        int roll = game.rollDie(DICE_SIZE);
        int coordinateX = token.getCoordinateX();

        if (roll + coordinateX < BOARD_SIZE) {
            token.setCoordinateX(coordinateX + roll);
        } else {
            if (token.getCoordinateY() < BOARD_SIZE - 1) {
                token.setCoordinateY(token.getCoordinateY() + 1);
                token.setCoordinateX(roll + coordinateX - BOARD_SIZE);
            }
        }

        token.setLastRoll(roll);
    }

    public void moveToken(Token token) {
        assignNewPosition(token);
        game.getBoard()[token.getCoordinateY()][token.getCoordinateX()] += token.getPlayerNumber();
        game.setToken(token);
    }
}
