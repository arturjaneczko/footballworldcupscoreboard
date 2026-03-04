package com.sportradar.fwcsb.infra;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class StorageTest {

    @AfterEach
    void tearDown() {
        new Storage().removeFromScoreboard(giveGame());
    }

    @Test
    void updateScoreboardTest() {
        // given
        Storage storage = new Storage();
        Game game = giveGame();
        Match match = new Match(new TeamScore(game.getHomeTeam(), 1), new TeamScore(game.getAwayTeam(), 1));
        // when
        Match result = storage.updateScoreboard(game, match);
        // then
        Assertions.assertThat(result).isNull();
        Assertions.assertThat(storage.getScoreboard()).containsExactly(match);
    }

    @Test
    void removeFromScoreboardTest() {
        // given
        Storage storage = new Storage();
        Game game = giveGame();
        Match match = new Match(new TeamScore(game.getHomeTeam(), 1), new TeamScore(game.getAwayTeam(), 1));
        Assertions.assertThat(storage.getScoreboard()).isEmpty();
        Assertions.assertThat(storage.updateScoreboard(game, match)).isNull();
        Assertions.assertThat(storage.getScoreboard()).contains(match);
        // when
        Match result = storage.removeFromScoreboard(game);
        // then
        Assertions.assertThat(result).isEqualTo(match);
        Assertions.assertThat(storage.getScoreboard()).isEmpty();
    }

    private static Game giveGame() {
        Team mexico = new HomeTeam("Mexico");
        Team canada = new AwayTeam("Canada");
        return new Game(mexico, canada);
    }

}