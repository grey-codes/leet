import java.util.Arrays;

class Solution {
    static int maskRaw = 128;
    static int numMask = maskRaw-1;
    static int b1=maskRaw*2;
    static int b2=b1*2;
    static int b3=b2*2;
    static int b4=b3*2;
    static int b5=b4*2;
    static int b6=b5*2;
    static int b7=b6*2;
    static int b8=b7*2;
    static int b9=b8*2;
    static int fullBits = b1|b2|b3|b4|b5|b6|b7|b8|b9;
    static int validMask = fullBits & (~numMask);
    static int[] bits = new int[] {numMask,b1,b2,b3,b4,b5,b6,b7,b8,b9};

    public void solveSudoku(char[][] board) {
        int[][] intBoard= new int[9][9];
        int[] rowValues = new int[9];
        int[] colValues = new int[9];
        int[][] gridValues = new int[3][3];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                char raw = board[i][j];
                int val = raw=='.' ? 0 : raw - '0';
                int orVal = bits[val];
                rowValues[i]=rowValues[i] | orVal;
                colValues[j]=colValues[j] | orVal;
                gridValues[i/3][j/3] = gridValues[i/3][j/3] | orVal;
                intBoard[i][j] = val;
            }
        }
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                int val = gridValues[i/3][j/3] | rowValues[i] | colValues[j];
                intBoard[i][j] = (intBoard[i][j] & numMask) | ((~val) & validMask);
            }
        }
        int[][] solved = solve(intBoard);
        if (solved==null)
            return;
        for (int i=0; i <solved.length; i++) {
            for (int j=0; j<solved.length; j++) {
                board[i][j] = (char) ('0' + solved[i][j] & numMask);
            }
        }
    }

    public int[][] solve(int[][] board) {
        for (int row=0; row<board.length; row++) {
            int[] boardRow = board[row];
            for (int col=0; col<boardRow.length; col++) {
                int v = board[row][col] & numMask;
                if (v==0) {
                    int validValues = board[row][col] & validMask;
                    for (int i=1; i<bits.length; i++) {
                        int bit = bits[i];
                        if ((bit&validValues)==bit) {
                            int[][] test = actionIsValid(board, row, col, i);
                            if (test==null)
                                continue;
                            // if (row==0 && col==2) {
                            //     System.out.println(test[row][col]&numMask);
                            // }
                            int[][] solved = solve(test);
                            if (solved!=null)
                                return solved;
                        }
                    }
                    return null;
                }
            }
        }
        return board;
    }

    public int[][] actionIsValid(final int[][] board,  int row, int col, int val) {
        // turns out that array.clone() doesn't deep copy 2d arrays
        // so, we manually clone
        int[][] cloneBoard =new int[board.length][9];
        for (int i=0; i<cloneBoard.length; i++) {
            cloneBoard[i]=Arrays.copyOf(board[i],9);
        }
        // calculate the invval once and store it (excludes val from valid values)
        int invVal = ~bits[val];
        // check row
        for (int i=0; i<cloneBoard.length; i++) {
            cloneBoard[row][i] &= invVal;
            if (i==col)
                continue;
            if ((cloneBoard[row][i]&numMask)!=0)
                continue;
            if ((cloneBoard[row][i] & validMask) == 0)
                return null;
        }
        // check col
        for (int i=0; i<cloneBoard.length; i++) {
            cloneBoard[i][col] &= invVal;
            if (i==row)
                continue;
            if ((cloneBoard[i][col]&numMask)!=0)
                continue;
            if ((cloneBoard[i][col] & validMask) == 0)
                return null;
        }
        // check the 3x3 grid
        int gridRow = (row/3) * 3;
        int gridCol = (col/3) * 3;
        for (int i=gridRow; i<gridRow+3; i++) {
            for (int j=gridCol; j<gridCol+3; j++) {
                cloneBoard[i][j] &= invVal;
                if (i==row && j==col)
                    continue;
                if ((cloneBoard[i][j]&numMask)!=0)
                    continue;
                if ((cloneBoard[i][j] & validMask) == 0)
                    return null;
            }
        }
        // put the actual num val in
        cloneBoard[row][col] |= val & numMask;
        // return
        return cloneBoard;
    }
}
