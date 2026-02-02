package io.at.learn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {

    private Board board;
    private Queue<Player> queue;
    private Scanner sc;

    public Game() {
        board = new Board();
        queue = new LinkedList<>();
        sc = new Scanner(System.in);
    }

    public Player start() {
        Player winner = null;
        while (!board.isBoardFull() && !this.queue.isEmpty()) {
            Player player = this.queue.poll();

            System.out.printf("Player %s, input row, col \n", player.name());
            int row = sc.nextInt();
            int col = sc.nextInt();

            boolean didPlayerWin = this.board.updateBlock(row, col, player.symbol());
            this.board.printBoard();

            if(didPlayerWin) {
                winner = player;
                break;
            }
            this.queue.add(player);
        }
        return winner;
    }

}
