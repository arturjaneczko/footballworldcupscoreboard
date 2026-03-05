//package com.sportradar.fwcsb;
//
//import com.sportradar.fwcsb.api.Facade;
//import com.sportradar.fwcsb.domain.Board;
//import com.sportradar.fwcsb.domain.game.Score;
//import com.sportradar.fwcsb.domain.game.TeamScore;
//import com.sportradar.fwcsb.domain.game.match.Summary;
//import com.sportradar.fwcsb.domain.game.team.AwayTeam;
//import com.sportradar.fwcsb.domain.game.team.HomeTeam;
//import com.sportradar.fwcsb.domain.game.team.Team;
//import com.sportradar.fwcsb.infra.Storage;
//import com.sportradar.fwcsb.service.Service;
//
//import java.util.List;
//
///**
// * Class created by kind a 'integration test' purpose
// */
//public class Main {
//
//    public static void main(String args[]) {
//        new Main().m();
//    }
//
//    private void m() {
//        Storage storage = new Storage();
//        Service service = new Service(storage);
//        Facade board = new Board(service);
//
//        Team mexico = home("Mexico");
//        Team spain = home("Spain");
//        Team germany = home("Germany");
//        Team uruguay = home("Uruguay");
//        Team argentina = home("Argentina");
//
//        Team canada = away("Canada");
//        Team brazil = away("Brazil");
//        Team france = away("France");
//        Team italy = away("Italy");
//        Team australia = away("Australia");
//
//        board.startGame(mexico, canada);
//        board.startGame(spain, brazil);
//        board.startGame(germany, france);
//        board.startGame(uruguay, italy);
//        board.startGame(argentina, australia);
//
//        board.updateScore(new TeamScore(mexico, new Score(0)), new TeamScore(canada, new Score(5)));
//        board.updateScore(new TeamScore(spain, new Score(10)), new TeamScore(brazil, new Score(2)));
//        board.updateScore(new TeamScore(germany, new Score(2)), new TeamScore(france, new Score(2)));
//        board.updateScore(new TeamScore(uruguay, new Score(6)), new TeamScore(italy, new Score(6)));
//        board.updateScore(new TeamScore(argentina, new Score(3)), new TeamScore(australia, new Score(1)));
//
//        List<Summary> started = board.totalSummary();
//        System.out.println("\nscore size: " + started.size());
//        started.forEach(System.out::println);
//
//        board.finishGame(mexico, canada);
//        board.finishGame(spain, brazil);
//        board.finishGame(germany, france);
//        board.finishGame(uruguay, italy);
//        board.finishGame(argentina, australia);
//
//        List<Summary> finished = board.totalSummary();
//        System.out.println("\nscore size: " + finished.size());
//        finished.forEach(System.out::println);
//    }
//
//    private static Team home(String name) {
//        return new HomeTeam(name);
//    }
//
//    private static Team away(String name) {
//        return new AwayTeam(name);
//    }
//
//}
