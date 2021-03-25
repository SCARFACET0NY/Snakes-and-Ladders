package com.anton.snl.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Game {
    private int[][] board;
    private Token token;
    private boolean isActive;
}