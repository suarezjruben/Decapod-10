package systemLife;

import javax.swing.*;
import java.io.IOException;

public class Iterator {
    private int[][] nextIteration;
    int size;

    public Iterator(){
        size = 0;
    }

    public int[][] next(int[][] runningColony){
        System.out.println("In Iterator next()");

        nextIteration = new int[runningColony.length][runningColony.length];
        size = runningColony.length - 2;
        //System.out.println("Size: " + size);
        //newGrid = new int[size][size];    // Creating new array to hold the new generation of cells

        /**
         * Rule Implementation
         */
        for (int l = 1; l < size + 1; l++)
        {
            for (int m = 1; m < size + 1; m++)
            {
                // Finding total number of neighboring cells that are alive

                int aliveTotal = 0;
                // First Step: Checking around [1][1]
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        aliveTotal += runningColony[l + i][m + j];
                    }
                }
                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveTotal -= runningColony[l][m];

                // Starting cell is [1][1]
                // edges are redundant

                // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                if ((runningColony[l][m] == 1) && (aliveTotal < 2))
                    nextIteration[l][m] = 0;

                    // Any live cell with more than three live neighbours dies, as if by overpopulation.
                else if ((runningColony[l][m] == 1) && (aliveTotal > 3))
                    nextIteration[l][m] = 0;

                    // Any live cell with two or three live neighbours lives on to the next generation.
                else if ((runningColony[l][m] == 1) && (aliveTotal >= 2) && (aliveTotal <= 3))
                    nextIteration[l][m] = 1;

                    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                else if ((runningColony[l][m] == 0) && (aliveTotal == 3))
                    nextIteration[l][m] = 1;
            }
        }

        /*System.out.println("Next Generation");
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                // the +1 is to adjust the starting point at [1][1] as top-left corner
                if (nextIteration[i + 1][j + 1] == 0)
                    System.out.print(".");
                else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }*/
        return nextIteration;
    }
}
