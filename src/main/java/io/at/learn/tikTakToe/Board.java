package io.at.learn;

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
            if(board[i][row].symbol() != symbol) verticalChecked = false;
            if(board[size][i].symbol() != symbol) horizontalChecked = false;
        }
        boolean leftDiagonalChecked = true;
        boolean righttDiagonalChecked = true;
        for(int i=0; i<size; i++) {
            if(board[i][i].symbol() != symbol) leftDiagonalChecked = false;
            if(board[size - i - 1][i].symbol() != symbol) righttDiagonalChecked = false;
        }

        return verticalChecked || horizontalChecked || leftDiagonalChecked || righttDiagonalChecked;
    }

    public boolean isBoardFull() {
        return countPlays >= maxPlays;
    }

    public void printBoard() {
        System.out.println("-----------------------");
        for(Cell[] row: board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("-----------------------");
    }

    private boolean isInRange(int row, int col) {
        return row >= 0 && row < this.size && col >= 0 && col < this.size;
    }

}
