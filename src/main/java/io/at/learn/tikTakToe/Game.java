package io.at.learn.tikTakToe;

import io.at.learn.dto.Player;

import java.util.Queue;
import java.util.Scanner;

public class Game {

    private final Board board;
    private final Queue<Player> queue;
    private final Scanner sc;
    private static final Integer MOVE_ERROR_THRESH_HOLD = 3;

    public Game(Scanner sc, Queue<Player> queue) {
        board = new Board();
        this.queue = queue;
        this.sc = sc;
    }

    public Player start() {
        Player winner = null;
        while (!board.isBoardFull() && !this.queue.isEmpty()) {
            Player player = this.queue.poll();
            System.out.printf("Player %s, input row, col \n", player.name());
            this.board.printBoard();

            boolean playerTurnComplete = false;
            int wrongMove = 0;
            while(!playerTurnComplete && wrongMove < MOVE_ERROR_THRESH_HOLD) {
                int row = sc.nextInt();
                int col = sc.nextInt();

                try {
                    boolean didPlayerWin = this.board.updateBlock(row, col, player.symbol());
                    playerTurnComplete = true;
                    if(didPlayerWin) {
                        winner = player;
                        break;
                    }
                    this.queue.add(player);
                } catch (RuntimeException e) {
                    System.out.println("ERROR " + e.getMessage());
                    wrongMove++;
                }
            }
            if(wrongMove == MOVE_ERROR_THRESH_HOLD) {
                throw new RuntimeException("Error while selecting the sell");
            }
        }
        return winner;
    }

}
