public class Main {

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }
        };

        if (solveSudoku(grid)) printSolution(grid);
        else System.out.println("No solution");
    }

    public static boolean isValid(int[][] grid, int row, int column, int number) {
        // check the row
        for (int c = 0; c < grid.length; c++) {
            if (grid[row][c] == number) return false;
        }

        // check the column
        for (int r = 0; r < grid.length; r++) {
            if (grid[r][column] == number) return false;
        }

        // check the 3x3 subgrid
        int subSize = (int) Math.sqrt(grid.length);
        int subRowStart = row - row % subSize;
        int subColStart = column - column % subSize;

        for (int r = subRowStart; r < subRowStart + subSize; r++) {
            for (int c = subColStart; c < subColStart + subSize; c++) {
                if (grid[r][c] == number) return false;
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] grid) {
        int row = -1;
        int column = -1;
        boolean toSolve = false;
        int gridSize = grid.length;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                // if empty, we want to fill it so we assign the coords
                if (grid[i][j] == 0) {
                    row = i;
                    column = j;

                    toSolve = true;
                    break;
                }
            }
            if (toSolve) break;
        }

        // if no more to solve
        if (!toSolve) return true;

        // assign numbers 1 - 9
        for (int number = 1; number <= gridSize; number++) {
            // check if they respond to the rules
            if (isValid(grid, row, column, number)) {
                // assign it
                grid[row][column] = number;
                // continue with next
                if (solveSudoku(grid)) return true;
                else grid[row][column] = 0;
            }
        }

        return false;
    }

    public static void printSolution(int[][] grid) {
        int gridSize = grid.length;

        for (int row = 0; row < gridSize; row++) {
            if (row % 3 == 0) System.out.println("-------------------------");
            System.out.print("| ");

            for (int column = 0; column < gridSize; column++) {
                System.out.print(grid[row][column]);

                if (column % 3 == 2) System.out.print(" | ");
                else System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }
}
