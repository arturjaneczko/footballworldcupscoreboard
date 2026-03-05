package com.sportradar.fwcsb.domain.rules;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.domain.rules.ex.*;

import java.util.Optional;

public class BusinessRuleEngine {

    public static void checkIfGameCanBeStarted(final Optional<Game> game, final Team home, final Team away) {
        rethrow(() -> {
            if (game.isPresent()) {
                throw new GameAlreadyStartedException(home.getName() + " - " + away.getName());
            }
        });
    }

    public static void checkIfGameCanBeFinished(final Optional<Game> game) {
        rethrow(() -> {
            if (game.isEmpty()) {
                throw new GameDoesNotExistsException();
            }
        });
    }

    public static void checkIfScoreCanBeUpdated(final Optional<Game> game, final Optional<Match> match, final TeamScore home, final TeamScore away) {
        rethrow(() -> {
            if (game.isEmpty() || match.isEmpty()) {
                throw new GameDoesNotExistsException();
            }
            if (isNegativeScore(match.orElseThrow(), home, away)) {
                throw new NegativeScoreException();
            }
        });
    }

    public static void checkTeamsOrigin(final Team home, final Team away) {
        rethrow(() -> {
            if (isSameOrigin(home, away)) {
                throw new TeamSameOriginException();
            }
        });
    }

    private static boolean isNegativeScore(Match match, TeamScore home, TeamScore away) {
        int homeScore = match.getHome().score().value() + home.score().value();
        int awayScore = match.getAway().score().value() + away.score().value();
        return homeScore < 0 || awayScore < 0;
    }

    private static boolean isSameOrigin(Team home, Team away) {
        return (home.getOrigin().ordinal() + away.getOrigin().ordinal()) % 2 == 0;
    }

    @FunctionalInterface
    interface BusinessRuleExceptionRunnable {
        void run() throws Exception;
    }

    public static void rethrow(BusinessRuleExceptionRunnable runnable) {
        try {
            runnable.run();
        } catch (BusinessRuleException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
