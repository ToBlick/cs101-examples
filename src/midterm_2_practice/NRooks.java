package midterm_2_practice;

public class NRooks {
    public static void main(String[] args) {
        boolean[][] board = createBoard(8);
        for (int i = 0; i < board.length; i++) {
            set(board, i, i);
        }
        System.out.println(isValid(board)&& isComplete(board));
        set(board, 0, 1);
        System.out.println(isValid(board));
    }

    public static boolean[][] createBoard(int N) {
        return new boolean[N][N];
    }

    public static void set(boolean[][] board, int row, int col) {
        board[row][col] = true;
    }

    public static boolean isThreatened(boolean[][] board, int row, int col) {
        if (!board[row][col]) {
            return false;
        }
        for(int r = 0; r < board.length; ++r) { // check column
            if(board[r][col] && r != row) {
                return true;
            }
        }
        for(int c = 0; c < board[row].length; ++c) { // check row
            if(board[row][c] && c != col) {
                return true;
            }
        }
        return false;
    }

    public static boolean isComplete(boolean[][] board) {
        int target = board.length;

        int count = 0;
        for(boolean[] row: board) {
            for(boolean v: row) {
                if(v) {
                    count++;
                }
            }
        }
        return count == target;
    }

    public static boolean isValid(boolean[][] board) {
        for(int r = 0; r < board.length; ++r) {
            for(int c = 0; c < board[r].length; ++c) {
                if(board[r][c]) {
                    if(isThreatened(board,r,c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
