class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int top, right, bottom, left, dir, row, col;
        top=0;
        left=-1;
        right=matrix[0].length;
        bottom=matrix.length;
        dir = 0;
        row = 0;
        col = 0;
        List<Integer> r = new ArrayList<>();
        if (matrix.length==0 || matrix[0].length==0) {
            return r;
        }
        r.add(matrix[row][col]);
        while (top!=bottom && left!=right) {
            switch(dir) {
                case 0:
                    col++;
                    if (col==right) {
                        col--;
                        dir++;
                        right=col;
                    } else {
                        r.add(matrix[row][col]);
                    }
                    break;
                case 1:
                    row++;
                    if (row==bottom) {
                        row--;
                        dir++;
                        bottom=row;
                    } else {
                        r.add(matrix[row][col]);
                    }
                    break;
                case 2:
                    col--;
                    if (col==left) {
                        col++;
                        dir++;
                        left=col;
                    } else {
                        r.add(matrix[row][col]);
                    }
                    break;
                case 3:
                    row--;
                    if (row==top) {
                        row++;
                        dir=0;
                        top=row;
                    } else {
                        r.add(matrix[row][col]);
                    }
                    break;
            }
        }
        return r;
    }
}
