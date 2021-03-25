package com.anton.snl.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Token {
    private String playerName;
    private int playerNumber;
    private int coordinateX;
    private int coordinateY;
}
