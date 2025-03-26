package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static final int[][] FIELD = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    private static final int FIELDLENGTH = FIELD.length;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }

    private void startGame() {
        boolean gameOver = false;
        int currentPlayer = 1;

        while (!gameOver) {
            showField();
            System.out.println("Player " + currentPlayer + ", make your move: ");
            nextMove move = getNextMove();

            if (isFree(move.x(), move.y())) {
                FIELD[move.x()][move.y()] = currentPlayer;
                if (hasWin(move.x(), move.y())) {
                    showField();
                    System.out.println("Player " + currentPlayer + " has won!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                }
            } else {
                System.out.println("Position not free, try again.");
            }
        }
    }

    private record nextMove(int x, int y) {}

    private static nextMove getNextMove() {
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                return new nextMove(x, y);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    private static boolean isFree(int x, int y) {
        return x >= 0 && x < FIELDLENGTH && y >= 0 && y < FIELDLENGTH && FIELD[x][y] == 0;
    }

    private static boolean hasWin(int x, int y) {
        return hasWinHorizontal(x, y) || hasWinVertical(x, y) || hasWinDiagonal();
    }

    private static boolean hasWinHorizontal(int x, int y) {
        return Arrays.stream(FIELD[x]).allMatch(v -> v == FIELD[x][y] && v != 0);
    }

    private static boolean hasWinVertical(int x, int y) {
        return Arrays.stream(FIELD).allMatch(row -> row[y] == FIELD[x][y] && row[y] != 0);
    }

    private static boolean hasWinDiagonal() {
        return (FIELD[0][0] == FIELD[1][1] && FIELD[1][1] == FIELD[2][2] && FIELD[0][0] != 0) ||
                (FIELD[0][2] == FIELD[1][1] && FIELD[1][1] == FIELD[2][0] && FIELD[0][2] != 0);
    }

    private static void showField() {
        for (int[] row : FIELD) {
            System.out.println(Arrays.toString(row));
        }
    }
}
