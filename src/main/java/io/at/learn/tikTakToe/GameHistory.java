package io.at.learn.tikTakToe;

import io.at.learn.dto.GameReport;
import io.at.learn.dto.Player;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {

    private List<GameReport> history;
    public GameHistory() {
        this.history = new ArrayList<>();
    }

    public void addScore(int gameId, Player player) {
        this.history.add(new GameReport(gameId, player == null ? "None" : player.name()));
    }

    public void displayScore() {
        history.forEach(h -> {
            System.out.println(h.gameId() + " \t | \t " + h.playerWon());
        });
    }

}
