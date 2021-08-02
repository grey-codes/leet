class Solution {
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        int half = size/2;
        for (int depth=0; depth<=half-1; depth++) {
            for (int i=depth; i<size-depth-1; i++) {
                // row, col -> col, size-1-row
                // col, size-1-row = row, col

                // coord1 = top left
                int x1 = depth;
                int y1 = i;

                // coord2 - top right
                int x2 = y1;
                int y2 = (size-1)-x1;

                // coord3 - bottom right
                int x3 = y2;
                int y3 = (size-1)-x2;

                // coord4 - bottom left
                int x4 = y3;
                int y4 = (size-1)-x3;

                // copy
                int tmp = matrix[x4][y4]; // save bottom left
                matrix[x4][y4] = matrix[x3][y3]; // bottom left = bottom right
                matrix[x3][y3] = matrix[x2][y2]; // bottom right = top right
                matrix[x2][y2] = matrix[x1][y1]; // top right = bottom left
                matrix[x1][y1] = tmp; // top left = bottom left
            }
        }
    }
}
