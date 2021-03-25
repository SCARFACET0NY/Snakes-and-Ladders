package com.anton.snl.service;

import com.anton.snl.model.Game;
import com.anton.snl.model.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    public static final int BOARD_SIZE = 10;
    public static final int START_POSITION_X = 0;
    public static final int START_POSITION_Y = 0;

    Token token;
    @Mock
    Game game;
    @InjectMocks
    GameService gameService;

    @BeforeEach
    void before() {
        token = new Token();
        when(game.getBoard()).thenReturn(new int[BOARD_SIZE][BOARD_SIZE]);
    }

    @Test
    void whenGameIsActiveTokenIsPlacedOnTheFirstSquare() {
        when(game.isActive()).thenReturn(true);
        when(game.getToken()).thenReturn(token);

        token.setCoordinateX(START_POSITION_X);
        token.setCoordinateY(START_POSITION_Y);

        Game startedGame = gameService.startGame(token);

        assertTrue(startedGame.isActive());
        assertEquals(START_POSITION_X, game.getToken().getCoordinateX());
        assertEquals(START_POSITION_Y, game.getToken().getCoordinateY());
    }

    @Test
    void whenTokenIsMovedThreeSpacesItIsLocatedOnFourth() {
        token.setCoordinateX(START_POSITION_X);
        token.setCoordinateY(START_POSITION_Y);

        when(game.getToken()).thenReturn(token);

        gameService.moveToken(token, 3);

        assertEquals(3, game.getToken().getCoordinateX());
        assertEquals(START_POSITION_Y, game.getToken().getCoordinateY());
    }

    @Test
    void whenTokenIsMovedThreeSpacesAndThenFourItIsLocatedOnEights() {
        token.setCoordinateX(START_POSITION_X);
        token.setCoordinateY(START_POSITION_Y);

        when(game.getToken()).thenReturn(token);

        gameService.moveToken(token, 3);
        gameService.moveToken(token, 4);

        assertEquals(7, game.getToken().getCoordinateX());
        assertEquals(START_POSITION_Y, game.getToken().getCoordinateY());
    }


}