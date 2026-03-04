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
        // when
        board.startGame();
        // then
        Mockito.verify(service).startGame();
    }

}
