package com.anton.snl.service;

import com.anton.snl.exception.GameNotActiveException;
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
    public static final int BOARD_SIZE = 100;
    public static final int DICE_SIZE = 6;
    public static final int START_POSITION = 0;

    Token token;
    @Mock
    Game game;
    @InjectMocks
    GameService gameService;

    @BeforeEach
    void before() {
        token = new Token();
        when(game.getBoard()).thenReturn(new int[BOARD_SIZE]);
    }

    @Test
    void whenGameIsActiveTokenIsPlacedOnTheFirstSquare() {
        when(game.isActive()).thenReturn(true);
        when(game.getToken()).thenReturn(token);

        token.setPosition(START_POSITION);

        Game startedGame = gameService.startGame(token);

        assertTrue(startedGame.isActive());
        assertEquals(START_POSITION, game.getToken().getPosition());
    }

    @Test
    void whenTokenIsMovedThreeSpacesItIsLocatedOnFourth() {
        token.setPosition(START_POSITION);

        when(game.getToken()).thenReturn(token);
        when(game.rollDie(DICE_SIZE)).thenReturn(3);

        gameService.moveToken(token);

        assertEquals(3, game.getToken().getPosition());
        verify(game).rollDie(anyInt());
    }

    @Test
    void whenTokenIsMovedThreeSpacesAndThenFourItIsLocatedOnEights() {
        token.setPosition(START_POSITION);

        when(game.getToken()).thenReturn(token);
        when(game.rollDie(DICE_SIZE)).thenReturn(3).thenReturn(4);

        gameService.moveToken(token);
        gameService.moveToken(token);

        assertEquals(7, game.getToken().getPosition());
        verify(game, times(2)).rollDie(anyInt());
    }

    @Test
    void ifPlayerRollsFourTokenShouldMoveFourSpaces() {
        int startingPosition = 3;
        int expectedDifference = 4;

        token.setPosition(startingPosition);;

        when(game.rollDie(DICE_SIZE)).thenReturn(expectedDifference);

        gameService.moveToken(token);

        assertEquals(expectedDifference, token.getPosition() - startingPosition);
    }

    @Test
    void playerHasWonIfLandedOnExactlyOnTheFinish() {
        int spacesToWin = 3;

        token.setPosition(96);

        when(game.isActive()).thenReturn(false).thenReturn(true);
        when(game.rollDie(DICE_SIZE)).thenReturn(spacesToWin);

        assertThrows(GameNotActiveException.class, () -> gameService.turn(token));
        gameService.turn(token);

        assertTrue(token.isWinner());
    }

    @Test
    void playerStayedOnTheSameSpotIfRolledNumberOverTheFinish() {
        int spacesToWin = 3;
        int startingPosition = 96;

        token.setPosition(startingPosition);

        when(game.isActive()).thenReturn(true);
        when(game.getToken()).thenReturn(token);
        when(game.rollDie(6)).thenReturn(spacesToWin + 2);

        gameService.turn(token);

        assertFalse(token.isWinner());
        assertEquals(startingPosition, game.getToken().getPosition());
    }
}