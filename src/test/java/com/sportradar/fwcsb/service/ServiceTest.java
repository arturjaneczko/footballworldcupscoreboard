package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @InjectMocks
    private Service service;

    @Test
    void testStartGame() {
        // given
        Team mexico = new HomeTeam("Mexico");
        Team canada = new HomeTeam("Canada");
        Game game = new Game(mexico, canada);
        // when
        service.startGame(game);
        // then
        Mockito.verify(storage).updateScoreboard(Mockito.eq(game), Mockito.any(Match.class));
    }
}