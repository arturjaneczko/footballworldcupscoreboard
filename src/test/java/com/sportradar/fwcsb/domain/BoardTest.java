package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.service.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BoardTest {

    @InjectMocks
    private Board board;
    @Mock
    private Service service;

    @Test
    void testStartGame() {
        // given
        Team home = new HomeTeam("Mexico");
        Team away = new HomeTeam("Canada");
        // when
        board.startGame();
        // then
        Mockito.verify(service).startGame();
    }

    @Test
    void testFinishGame() {
        // when
        board.finishGame();
        // then
        Mockito.verify(service).finishGame();
    }

    @Test
    void testUpdateScore() {
        // when
        board.updateScore();
        // then
        Mockito.verify(service).updateScore();
    }

    @Test
    void testTotalSummary() {
        // when
        board.totalSummary();
        // then
        Mockito.verify(service).totalSummary();
    }

}
