package com.anton.snl.controller;

import com.anton.snl.model.Game;
import com.anton.snl.model.Token;
import com.anton.snl.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Token token) {
        log.info("start game request: {}", token);

        return ResponseEntity.ok(gameService.startGame(token));
    }

    @PostMapping("/turn")
    public ResponseEntity<Game> turn(@RequestBody Token token) {
        log.info("turn request: {}", token);

        return ResponseEntity.ok(gameService.turn(token));
    }
}
