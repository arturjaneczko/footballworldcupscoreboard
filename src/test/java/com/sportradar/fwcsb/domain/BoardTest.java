package com.sportradar.fwcsb.domain;

1import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void testStartGame() {
        Assertions.assertThat(new Board().startGame()).isTrue();
    }
}
