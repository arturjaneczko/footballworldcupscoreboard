package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.service.Service;

public class Board {

    private final Service service;

    public Board(final Service service) {
        this.service = service;
    }

    public boolean startGame() {
        return service.startGame();
    }

    public boolean finishGame() {
        return service.finishGame();
    }

    public boolean updateScore() {
        return service.updateScore();
    }

    public boolean totalSummary() {
        return service.totalSummary();
    }
}
