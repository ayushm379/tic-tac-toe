package io.at.learn.tikTakToe;

import io.at.learn.dto.Cell;
import io.at.learn.dto.Symbol;
import lombok.Data;

import java.util.Arrays;

@Data
public class Board {

    private int size;
    private Cell[][] board;
    private int countPlays;
    private int maxPlays;

    public Board() {
        this.size = 3;
        this.board = new Cell[3][3];
        this.countPlays = 0;
        this.maxPlays = size * size;
    }

    public boolean updateBlock(int row, int col, Symbol symbol) {
        if(!isInRange(row, col)) throw new RuntimeException("Wrong block coordinate");
        if(board[row][col] != null) throw new RuntimeException("Block already filled");
        board[row][col] = new Cell(symbol);
        this.countPlays++;
        return checkIfWon(row, col, symbol);
    }

    private boolean checkIfWon(int row, int col, Symbol symbol) {
        boolean verticalChecked = true;
        boolean horizontalChecked = true;
        for(int i=0; i<size; i++) {
            if(board[i][col] == null || board[i][col].symbol() != symbol) verticalChecked = false;
            if(board[row][i] == null || board[row][i].symbol() != symbol) horizontalChecked = false;
        }
        boolean leftDiagonalChecked = true;
        boolean righttDiagonalChecked = true;
        for(int i=0; i<size; i++) {
            if(board[i][i] == null || board[i][i].symbol() != symbol) leftDiagonalChecked = false;
            if(board[size - i - 1][i] == null || board[size - i - 1][i].symbol() != symbol) righttDiagonalChecked = false;
        }

        return verticalChecked || horizontalChecked || leftDiagonalChecked || righttDiagonalChecked;
    }

    public boolean isBoardFull() {
        return countPlays >= maxPlays;
    }

    public void printBoard() {
        System.out.println("-----------------------");
        System.out.print("\t");
        for(int i=0; i<board.length; i++)
            System.out.print(i + "\t");
        System.out.println();
        for(int i=0; i<board.length; i++) {
            System.out.print(i + "\t");
            for(int j=0; j<board[0].length; j++) {
                System.out.print(board[i][j] == null ? "-" : board[i][j].symbol());
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    private boolean isInRange(int row, int col) {
        return row >= 0 && row < this.size && col >= 0 && col < this.size;
    }

}
