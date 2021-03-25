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
    private final Game game;

    public Game startGame(Token token) {
        Game game = new Game();
        game.setActive(true);

        return game;
    }
}
