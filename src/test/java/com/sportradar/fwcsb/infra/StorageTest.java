package com.sportradar.fwcsb.infra;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StorageTest {

    @Test
    void updateScoreboardTest() {
        // given
        Storage storage = new Storage();
        Team mexico = new HomeTeam("Mexico");
        Team canada = new AwayTeam("Canada");
        Game game = new Game(mexico, canada);
        Match match = new Match(new TeamScore(mexico, 1), new TeamScore(canada, 1));
        // when
        storage.updateScoreboard(game, match);
        // then
        Assertions.assertThat(storage.getScoreboard());
    }
}