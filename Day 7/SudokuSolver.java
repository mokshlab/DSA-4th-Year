import java.util.Scanner;

public class SudokuSolver {
    static class Solution {
        public void solveSudoku(char[][] board) {
            solve(board);
        }
        boolean solve(char[][] board) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(board[i][j] == '.') {
                        for(char c = '1'; c <= '9'; c++) {
                            if(isValid(board, i, j, c)) {
                                board[i][j] = c;
                                if(solve(board)) return true;
                                else board[i][j] = '.';
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        boolean isValid(char[][] board, int row, int col, char c) {
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == c) return false;
            if(board[row][i] == c) return false;
            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
    }
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[][] board = new char[9][9];
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            board[i][j] = sc.next().charAt(0);
        }
    }
    Solution sol = new Solution();
    sol.solveSudoku(board);
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            System.out.print(board[i][j]);
            if (j < 8)
                System.out.print(" ");
        }
        System.out.println();
    }
    sc.close();
}
}