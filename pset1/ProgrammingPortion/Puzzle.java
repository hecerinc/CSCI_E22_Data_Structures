/*
 * Puzzle.java
 *
 * Implementation of a class that represents a Sudoku puzzle and solves
 * it using recursive backtracking.
 *
 * Computer Science E-22, Harvard University
 *
 * skeleton code by the course staff
 *
 */

/**
 * Created by Paul on 9/6/2015.
 */
package ProblemSet1;

import java.util.*;

public class Puzzle {
    // the dimension of the puzzle grid
    public static final int DIM = 9;
    public static final int NUM_DIGITS = 9;


    // the dimension of the smaller subgrids within the grid
    public static final int SUBGRID_DIM = 3;

    // The valent contents of the cells of the puzzle. 
    // values[r][c] gives the value in the cell at row r, column c.
    // The rows and columns are numbered from 0 to DIM-1.
    private int[][] values;

    // Indicates whether the value in a given cell is fixed 
    // (i.e., part of the original puzzle).
    // valIsFixed[r][c] is true if the value in the cell 
    // at row r, column c is fixed, and valIsFixed[r][c] is false 
    // if the value in that cell is not fixed.
    private boolean[][] valIsFixed;

    // This 3-D array allows us to determine if a given
    // subgrid (i.e., a given SUBGRID_DIM x SUBGRID_DIM region 
    // of the puzzle) already contains a given value.
    // We use 2 indices to identify a given subgrid.
    // For example:
    //
    //    (0,0)   (0,1)   (0,2)
    //
    //    (1,0)   (1,1)   (1,2)
    // 
    //    (2,0)   (2,1)   (2,2)
    // 
    // For example, subgridHasValue[0][2][5] will be true if
    // the subgrid in the upper right-hand corner already has
    // a 5 in it, and false otherwise.
    //
    // If a given cell of the board has indices [r][c], it falls
    // within the subgrid with indices [r/3][c/3] using integer
    // division.
    //
    private boolean[][][] subgridHasValue;

    // XXX: add your additional fields here. 
    // In particular, we recommend adding fields to keep track 
    // of whether a given row or column already contains a given value.
    // You should be able to use a similar approach to what we've
    // done for the subgrids, but it will be simpler, because each
    // row and column can be identified by a single integer.

    private static final int NUM_CELLS = 81; // the total number of cells
    boolean[][] rowHasValue; // 9x9 2-D array
    boolean[][] colHasValue; // 9x9 2-3 array

    /**
     * Constructs a new Puzzle object, which initially
     * has all empty cells.
     */
    public Puzzle() {
        values = new int[DIM][DIM];
        valIsFixed = new boolean[DIM][DIM];

        // Note that the third dimension of the array has a length
        // of DIM + 1, because we want to be able to use the possible
        // values (1 through 9) as indices.
        subgridHasValue = new boolean[SUBGRID_DIM][SUBGRID_DIM][NUM_DIGITS + 1];

        // XXX: add code to initialize the
        // fields that you add.
        rowHasValue = new boolean[DIM][NUM_DIGITS + 1];
        colHasValue = new boolean[DIM][NUM_DIGITS + 1];
    }

    /**
     * This is the key recursive-backtracking method.
     * Returns true if a solution has been found, and false otherwise.
     * <p/>
     * Each invocation of the method is responsible for finding the
     * value of a single cell of the puzzle. The parameter n
     * is the number of the cell that a given invocation of the method
     * is responsible for. We recommend that you consider the cells
     * one row at a time, from top to bottom and left to right,
     * which means that they would be numbered as follows:
     * <p/>
     * 0  1  2  3  4  5  6  7  8
     * 9 10 11 12 13 14 15 16 17
     * 18 ...
     **/

    public boolean solve(int m) {

        int curr = m;

        if (curr >= NUM_CELLS) { // NUM_CELLS is 81
            return true;
        }

        int row = curr / DIM;
        int col = curr % DIM;

        // removing fixed cells from recursive call
        while (valIsFixed[row][col]) {
            curr++;
            row = curr / DIM;
            col = curr % DIM;
        }

        for (int val = 1; val < NUM_DIGITS + 1; val++) {
            if (isValid(val, row, col)) {
                placeVal(val, row, col, true);
                if (solve(curr + 1)) {
                    return true;
                }
                removeVal(val, row, col);
            }
        }
        return false;
    }

    boolean isValid(int val, int row, int col) {
        if (rowHasValue[row][val] || colHasValue[col][val] ||
                subgridHasValue[row / SUBGRID_DIM][col / SUBGRID_DIM][val]) {
            return false;
        }
        return true;
    }

    /**
     * place the specified value in the cell with the
     * specified coordinates, and update the state of
     * the puzzle accordingly.
     */
    public void placeVal(int val, int row, int col, boolean print) {
        values[row][col] = val;
        subgridHasValue[row / SUBGRID_DIM][col / SUBGRID_DIM][val] = true;

        // XXX: add code to make any necessary changes to
        // the fields that you add.
        rowHasValue[row][val] = true;
        colHasValue[col][val] = true;
    }

    /**
     * remove the specified value from the cell with the
     * specified coordinates, and update the state of
     * the puzzle accordingly.
     */
    public void removeVal(int val, int row, int col) {


        values[row][col] = 0;
        subgridHasValue[row / SUBGRID_DIM][col / SUBGRID_DIM][val] = false;

        // XXX: add code to make any necessary changes to
        // the fields that you add.
        rowHasValue[row][val] = false;
        colHasValue[col][val] = false;
    }

    /**
     * Reads in a puzzle specification from the specified Scanner,
     * and uses it to initialize the state of the puzzle.  The
     * specification should consist of one line for each row, with the
     * values in the row specified as digits separated by spaces.  A
     * value of 0 should be used to indicate an empty cell.
     */
    public void readFrom(Scanner input) {
        for (int r = 0; r < DIM; r++) {
            for (int c = 0; c < DIM; c++) {
                int val = input.nextInt();
                placeVal(val, r, c, false);
                if (val != 0)
                    valIsFixed[r][c] = true;
            }
            input.nextLine();
        }
    }

    /**
     * Displays the valent state of the puzzle.
     * You should not change this method.
     */
    public void display() {
        for (int r = 0; r < DIM; r++) {
            printRowSeparator();
            for (int c = 0; c < DIM; c++) {
                System.out.print("|");
                if (values[r][c] == 0)
                    System.out.print("   ");
                else
                    System.out.print(" " + values[r][c] + " ");
            }
            System.out.println("|");
        }
        printRowSeparator();
    }

    // A private helper method used by display() 
    // to print a line separating two rows of the puzzle.
    private static void printRowSeparator() {
        for (int i = 0; i < DIM; i++)
            System.out.print("----");
        System.out.println("-");
    }



}
