package io.at.learn.tikTakToe;

import io.at.learn.dto.Player;
import io.at.learn.dto.Symbol;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameManager {
    private final Scanner sc;
    private final GameHistory gameHistory;
    private final Player player1;
    private final Player player2;

    public GameManager() {
        this.sc = new Scanner(System.in);
        this.gameHistory = new GameHistory();
        this.player1 = new Player("Player X", Symbol.X);
        this.player2 = new Player("Player Y", Symbol.O);
    }

    public void startGame() {
        boolean startGame = true;
        int gameId = 0;
        while(startGame) {
            gameId++;

            Queue<Player> players = new LinkedList<>();
            players.add(player1);
            players.add(player2);

            Game game = new Game(sc, players);
            Player winner = game.start();

            gameHistory.addScore(gameId, winner);

            System.out.println("Want to play again?");
            startGame = sc.nextBoolean();

        }
    }

}
