package com.anton.snl.service;

import com.anton.snl.model.Game;
import com.anton.snl.model.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    Token token;
    @Mock
    Game game;
    @InjectMocks
    GameService gameService;

    @Test
    void whenGameIsActiveTokenIsPlacedOnTheFirstSquare() {
        when(game.isActive()).thenReturn(true);
        when(game.getToken()).thenReturn(token);

        token.setCoordinateX(0);
        token.setCoordinateY(0);

        Game startedGame = gameService.startGame(token);

        assertTrue(startedGame.isActive());
        assertEquals(0, game.getToken().getCoordinateX());
        assertEquals(0, game.getToken().getCoordinateY());
    }
}