package com.baney.jonathan.islands;

import org.junit.Assert;
import org.junit.Test;

public class IslandCounterTest {

    @Test
    public void testTwoIslands() throws Exception {
        /*
        0 0 1 0
        1 1 1 0
        0 1 0 0
        0 0 0 1
         */

        int[][] grid = new int[][]{{0,1,0,0},{0,1,1,0},{1,1,0,0},{0,0,0,1}};

        IslandCounter counter = new IslandCounter();

        int result = counter.countIslands(grid);

        Assert.assertEquals(2, result);
    }
}
