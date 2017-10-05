package com.baney.jonathan.islands;

/**
 * Given a grid (2D int array of 0 for water or 1 for land), find all
 * islands and return the count.  Islands are pixels of land that are
 * adjacent (left, right, top, bottom).
 *
 *
 */
public class IslandCounter {

    public static final int WATER = 0;
    public static final int LAND = 1;


    /**
     * Passin a 2D int array of the grid and I will return the number of islands found.
     * @param grid
     * @return
     */
    public int countIslands(int[][] grid) {
        int width = grid.length;
        int height = grid[0].length;

        int xIndex = 0;
        int yIndex = 0;

        int[][] clonedGrid = cloneGrid(grid);

        int islandCount = 0;

        for (yIndex = 0; yIndex < height; ++yIndex) {
            for (xIndex = 0; xIndex < width; ++xIndex) {
//                System.out.println("(" + xIndex + ", " + yIndex + ") = " + clonedGrid[xIndex][yIndex]);
                if (clonedGrid[xIndex][yIndex] == LAND) {
                    // Land Ho.  We found a new island.  Now search adjacent pixels
                    ++islandCount;
                    // We now need to follow each path of land pixels until we reach and end.  Rinse and repeat
                    search(clonedGrid, xIndex, yIndex);
                }
            }
        }

        return islandCount;
    }

    private void search(int[][] grid, int x, int y) {
        int maxRight = grid.length - 1;
        int maxLeft = 0;
        int maxTop = 0;
        int maxBottom = grid[0].length - 1;

        // This pixel was land.  Now flip it to water so we don't search it again
        grid[x][y] = WATER;

        // Now test right, left, top, bottom from the current pixel and follow the
        // path until no more land is found.  This algorithm uses recursion, so
        // read that chapter and see if it makes sense.  Recursion relies on using what is
        // referred to as the "call stack".  A stack (read about the stack abstract data type on
        // wikipedia) maintains a "last in, first out" state.  Think about a cafeteria where there
        // is a literal stack of plates.  If you put one on top, the next person in line gets that
        // newest plate.  In a similar manner, each call we make to "search(...)" results in a new
        // call being added to the stack.  We pass in an incremented value for either x or y to the
        // new call to search so we are always moving in single direction at a time (right, left, top,
        // bottom).  If we find land in the next pixel, we keep recursing, adding to the call stack.
        // When we hit water for a given pixel in a single direction, we change directions.  If
        // we find water in all directions (remember, we destroy the land so we don't search again),
        // the method returns and is removed from the call stack.  Then the previous pixel is at the
        // top of the call stack and continues.  Step though in the debugger.

        if (x+1 <= maxRight && grid[x+1][y] == LAND) {
            // Found more land to the right, keep searching that direction
            search(grid, x+1, y);
        }
        if (x-1 >= maxLeft && grid[x-1][y] == LAND) {
            // Found more land to the left, keep searching that direction
            search(grid, x-1, y);
        }
        if (y-1 >= maxTop && grid[x][y-1] == LAND) {
            // Found land to the top, go that direction
            search(grid, x, y-1);
        }
        if (y+1 <= maxBottom && grid[x][y+1] == LAND) {
            search(grid, x, y+1);
        }
    }

    /**
     * The traversal algorithm is considered destructive since we are overwriting visited pixels in an
     * attempt to determine if a pixel has been searched or not.  If we didn't want to be desctructive,
     * we could simply track the x/y coordinates that had been visited and do a check before
     * evaluating.
     *
     * Since we are destructive, we'll be nice to the caller and not overwrite their grid.  We'll make a
     * clone of the grid for our own purposes.
     *
     * @param originalGrid
     * @return
     */
    private int[][] cloneGrid(int[][] originalGrid) {
        int originalWidth = originalGrid.length;
        int originalHeight = originalGrid[0].length;
        int[][] clonedGrid = new int[originalWidth][originalHeight];

        int x;
        int y;

        for (y = 0; y < originalHeight; ++y) {
            // New row, restart x traversal
            for (x = 0; x < originalWidth; ++x) {
//                System.out.println("(" + x + ", " + y + ") = " + originalGrid[x][y]);
                clonedGrid[x][y] = originalGrid[x][y];
            }
        }

        return clonedGrid;
    }

}
