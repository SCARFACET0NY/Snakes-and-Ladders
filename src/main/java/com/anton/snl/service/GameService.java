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
    private final int BOARD_SIZE = 10;
    private final Game game;

    public Game startGame(Token token) {
        game.setBoard(new int[BOARD_SIZE][BOARD_SIZE]);
        game.setToken(token);
        game.getBoard()[token.getCoordinateY()][token.getCoordinateX()] += token.getPlayerNumber();
        game.setActive(true);

        return game;
    }
}
