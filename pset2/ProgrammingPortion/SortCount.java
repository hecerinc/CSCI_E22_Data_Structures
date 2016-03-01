package ProblemSet2.ProgrammingProblems;
/*
 * SortCount.java
 *
 * Computer Science E-22, Harvard University
 *
 * Modified by:   <Paul Zeng>
 */

import ProblemSet2.textAnwsers.ReadInputUtil;

import java.util.*;

/**
 * This class contains implementations of various array-sorting
 * algorithms.  All comparisons and moves are performed using helper
 * methods that maintain counts of these operations.  Each sort method
 * takes an array of integers.  The methods assume that all of the
 * elements of the array should be sorted.  The algorithms sort the array
 * in place, altering the original array.
 */
public class SortCount {
    /*
     * the integers in the test arrays are drawn from the range
     * 0, ..., MAX_VAL
     */
    private static int MAX_VAL = 65536;

    private static long compares;     // total number of comparisons
    private static long moves;        // total number of moves

    /*
     * compare - a wrapper that allows us to count comparisons.
     */
    private static boolean compare(boolean comparison) {
        compares++;
        return comparison;
    }

    /*
     * move - moves an element of the specified array to a different
     * location in the array.  move(arr, dest, source) is equivalent
     * to arr[dest] = arr[source].  Using this method allows us to
     * count the number of moves that occur.
     */
    private static void move(int[] arr, int dest, int source) {
        moves++;
        arr[dest] = arr[source];
    }

    /*
     * swap - swap the values of two variables.
     * Used by several of the sorting algorithms below.
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        moves += 3;
    }

    public static void swapSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++){
            for (int j = i+1; j < arr.length; j++){
                if (compare(arr[j] < arr[i])){
                    swap(arr, i,j);
                }
            }
        }
    }

    /**
     * randomArray - creates an array of randomly generated integers
     * with the specified number of elements and the specified
     * maximum value
     */
    public static int[] randomArray(int n, int maxVal) {
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * (maxVal + 1));
        }

        return arr;
    }

    public static int[] randomArray(int n) {
        return randomArray(n, MAX_VAL);
    }

    /**
     * almostSortedArray - creates an almost sorted array of integers
     * with the specified number of elements
     */
    public static int[] almostSortedArray(int n) {
        /* Produce a random array and sort it. */
        int[] arr = randomArray(n);
        quickSort(arr);

        /*
         * Move one quarter of the elements out of place by Fbetween 1
         * and 5 places.
         */
        for (int i = 0; i < n/8; i++) {
            int j = (int)(Math.random() * n);
            int displace = -5 + (int)(Math.random() * 11);
            int k = j + displace;
            if (k < 0)
                k = 0;
            if (k > n - 1)
                k = n - 1;

            swap(arr, j, k);
        }

        return arr;
    }

    /**
     * Sets the counts of moves and comparisons to 0.
     */
    public static void initStats() {
        compares = 0;
        moves = 0;
    }

    /**
     * Prints the current counts of moves and comparisons.
     */
    public static void printStats() {
        int spaces;
        if (compares == 0)
            spaces = 0;
        else
            spaces = (int)(Math.log(compares)/Math.log(10));
        for (int i = 0; i < (10 - spaces); i++)
            System.out.print(" ");
        System.out.print(compares + " comparisons\t");

        if (moves == 0)
            spaces = 0;
        else
            spaces = (int)(Math.log(moves)/Math.log(10));
        for (int i = 0; i < (10 - spaces); i++)
            System.out.print(" ");
        System.out.println(moves + " moves");
    }

    /*
     * indexSmallest - returns the index of the smallest element
     * in the subarray from arr[lower] to arr[upper].  Used by
     * selectionSort.
     */
    private static int indexSmallest(int[] arr, int lower, int upper) {
        int indexMin = lower;

        for (int i = lower + 1; i <= upper; i++)
            if (compare(arr[i] < arr[indexMin]))
                indexMin = i;

        return indexMin;
    }

    /** selectionSort */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = indexSmallest(arr, i, arr.length - 1);
            swap(arr, i, j);
        }
    }

    /** insertionSort */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (compare(arr[i] < arr[i-1])) {
                // Save a copy of the element to be inserted.
                int toInsert = arr[i];
                moves++;

                // Shift right to make room for element.
                int j = i;
                do {
                    move(arr, j, j - 1); // dest, source;
                    j = j - 1;
                } while (j > 0 && compare(toInsert < arr[j-1]));

                // Put the element in place.
                arr[j] = toInsert;
                moves++;
            }
        }
    }

    /** shellSort */
    public static void shellSort(int[] arr) {
        /*
         * Find initial increment: one less than the largest
         * power of 2 that is <= the number of objects.
         */
        int incr = 1;
        while (2 * incr <= arr.length)
            incr = 2 * incr;
        incr = incr - 1;

        /* Do insertion sort for each increment. */
        while (incr >= 1) {
            for (int i = incr; i < arr.length; i++) {
                if (compare(arr[i] < arr[i-incr])) { // incr 3
                    int toInsert = arr[i]; // i ==4, toInsert == 62
                    moves++;

                    int j = i; // j == 4
                    do {
                        move(arr, j, j-incr); // move (4, 1), 36 replaces 53
                        j = j - incr; // j == 1
                    } while (j > incr-1 && compare(toInsert < arr[j-incr]));

                    arr[j] = toInsert; //
                    moves++;
                }
            }

            // Calculate increment for next pass.
            incr = incr / 2;
        }
    }

    /** bubbleSort */
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (compare(arr[j] > arr[j+1]))
                    swap(arr, j, j+1);
            }
        }
    }

    /*
     * A helper method for qSort that takes the array that begins with
     * element arr[first] and ends with element arr[last] and
     * partitions it into two subarrays using the middle element of
     * the array for the pivot.  It returns the index of the last
     * element of the left subarray formed by the partition.  All
     * elements in the left subarray are <= the pivot, and all
     * elements in the right subarray are >= the pivot.
     */
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last)/2];
        moves++;   // for the above assignment
        int i = first - 1;  // index going left to right
        int j = last + 1;   // index going right to left

        while (true) {
            // Moving from left to right, find an element >= the pivot.
            do {
                i++;
            } while (compare(arr[i] < pivot));

            // Moving from right to left, find an element <= the pivot.
            do {
                j--;
            } while (compare(arr[j] > pivot));

            // Swap the elements so that they end up in the correct
            // subarray, or quit if the indices have overlapped or crossed.
            if (i < j)
                swap(arr, i, j);
            else
                return j;   // index of last element in the left subarray
        }
    }

    /*
     * A recursive helper method that actually implements quicksort.
     * The initial recursive call is made by quicksort() -- see below.
     */
    private static void qSort(int[] arr, int first, int last) {
        // Partition the array.  split is the index of the last
        // element of the left subarray formed by the partition.
        int split = partition(arr, first, last);

        //
        // Note that we only make recursive calls on subarrays that
        // have two or more elements, and thus the base case is when
        // neither subarray has two or more elements.
        //
        if (first < split)
            qSort(arr, first, split);      // left subarray
        if (last > split + 1)
            qSort(arr, split + 1, last);   // right subarray
    }

    /** quickSort */
    public static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    /* merge - helper method for mergesort */
    private static void merge(int[] arr, int[] tmp,
                              int leftStart, int leftEnd, int rightStart, int rightEnd)
    {
        int i = leftStart;    // index into left subarray
        int j = rightStart;   // index into right subarray
        int k = leftStart;    // index into tmp

        while (i <= leftEnd && j <= rightEnd) {
            if (compare(arr[i] < arr[j]))
                tmp[k++] = arr[i++];
            else
                tmp[k++] = arr[j++];
            moves++;
        }

        while (i <= leftEnd) {
            tmp[k++] = arr[i++];
            moves++;
        }

        while (j <= rightEnd) {
            tmp[k++] = arr[j++];
            moves++;
        }

        for (i = leftStart; i <= rightEnd; i++) {
            arr[i] = tmp[i];
            moves++;
        }
    }

    /** mSort - recursive method for mergesort */
    private static void mSort(int[] arr, int[] tmp, int start, int end) {
        if (start >= end)
            return;

        int middle = (start + end)/2;
        mSort(arr, tmp, start, middle);
        mSort(arr, tmp, middle + 1, end);
        merge(arr, tmp, start, middle, middle + 1, end);
    }

    /** mergesort */
    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mSort(arr, tmp, 0, arr.length - 1);
    }

    /**
     * Prints the specified array in the following form:
     * { arr[0] arr[1] ... }
     */
    public static void printArray(int[] arr) {
        // Don't print it if it's more than 10 elements.
        if (arr.length > 10)
            return;

        System.out.print("{ ");

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

        System.out.println("}");
    }

    public static void main(String args[]) {
        int[] a;       // the array
        int[] asave;   // a copy of the original unsorted array
        int numItems;
        String arrayType;

        /*
         * Get various parameters from the user.
         */
        Scanner in = new Scanner(System.in);
        System.out.print("How many items in the array? ");
        numItems = in.nextInt();
        in.nextLine();
        System.out.print("Random (r), almost sorted (a), or fully sorted (f)? ");
        arrayType = in.nextLine();
        System.out.println();

        /*
         * Create the arrays.
         */
        if (arrayType.equalsIgnoreCase("A"))
            a = almostSortedArray(numItems);
        else {
            a = randomArray(numItems);
            if (arrayType.equalsIgnoreCase("F"))
                quickSort(a);
        }

        asave = new int[numItems];
        System.arraycopy(a, 0, asave, 0, a.length);
        printArray(a);

        /*
         * Try each of the various algorithms, starting each time
         * with a fresh copy of the initial array.
         */
        System.out.print("quickSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        quickSort(a);
        printStats();
        printArray(a);

        System.out.print("mergeSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        mergeSort(a);
        printStats();
        printArray(a);

        System.out.print("shellSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        shellSort(a);
        printStats();
        printArray(a);

        System.out.print("insertionSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        insertionSort(a);
        printStats();
        printArray(a);

        System.out.print("selectionSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        selectionSort(a);
        printStats();
        printArray(a);

        System.out.print("bubbleSort\t\t");
        System.arraycopy(asave, 0, a, 0, asave.length);
        initStats();
        bubbleSort(a);
        printStats();
        printArray(a);

        runSwapSort();

    }



    public static void runSwapSort(){

        List<int[]> almost16000 = new ArrayList<int[]>();
        List<int[]> random16000 = new ArrayList<int[]>();
        int[] almostSorted1_16000 = ReadInputUtil.readVerticalListInput("almostSorted1.txt");
        int[] almostSorted2_16000 = ReadInputUtil.readVerticalListInput("almostSorted2.txt");
        int[] almostSorted3_16000 = ReadInputUtil.readVerticalListInput("almostSorted3.txt");
        int[] almostSorted4_16000 = ReadInputUtil.readVerticalListInput("almostSorted4.txt");
        int[] almostSorted5_16000 = ReadInputUtil.readVerticalListInput("almostSorted5.txt");
        int[] almostSorted6_16000 = ReadInputUtil.readVerticalListInput("almostSorted6.txt");
        int[] almostSorted7_16000 = ReadInputUtil.readVerticalListInput("almostSorted7.txt");
        int[] almostSorted8_16000 = ReadInputUtil.readVerticalListInput("almostSorted8.txt");
        int[] almostSorted9_16000 = ReadInputUtil.readVerticalListInput("almostSorted9.txt");
        int[] almostSorted10_16000 = ReadInputUtil.readVerticalListInput("almostSorted10.txt");
        int[] random1_16000 = ReadInputUtil.readVerticalListInput("random1.txt");
        int[] random2_16000 = ReadInputUtil.readVerticalListInput("random2.txt");
        int[] random3_16000 = ReadInputUtil.readVerticalListInput("random3.txt");
        int[] random4_16000 = ReadInputUtil.readVerticalListInput("random4.txt");
        int[] random5_16000 = ReadInputUtil.readVerticalListInput("random5.txt");
        int[] random6_16000 = ReadInputUtil.readVerticalListInput("random6.txt");
        int[] random7_16000 = ReadInputUtil.readVerticalListInput("random7.txt");
        int[] random8_16000 = ReadInputUtil.readVerticalListInput("random8.txt");
        int[] random9_16000 = ReadInputUtil.readVerticalListInput("random9.txt");
        int[] random10_16000 = ReadInputUtil.readVerticalListInput("random10.txt");
        almost16000.add(almostSorted1_16000);
        almost16000.add(almostSorted2_16000);
        almost16000.add(almostSorted3_16000);
        almost16000.add(almostSorted4_16000);
        almost16000.add(almostSorted5_16000);
        almost16000.add(almostSorted6_16000);
        almost16000.add(almostSorted7_16000);
        almost16000.add(almostSorted8_16000);
        almost16000.add(almostSorted9_16000);
        almost16000.add(almostSorted10_16000);
        random16000.add(random1_16000);
        random16000.add(random2_16000);
        random16000.add(random3_16000);
        random16000.add(random4_16000);
        random16000.add(random5_16000);
        random16000.add(random6_16000);
        random16000.add(random7_16000);
        random16000.add(random8_16000);
        random16000.add(random9_16000);
        random16000.add(random10_16000);


        List<int[]> almost1000 = new ArrayList<int[]>();
        List<int[]> random1000 = new ArrayList<int[]>();
        int[] almostSorted1_1000 = Arrays.copyOfRange(almostSorted1_16000, 0,1000);
        int[] almostSorted2_1000 = Arrays.copyOfRange(almostSorted2_16000, 0, 1000);
        int[] almostSorted3_1000 = Arrays.copyOfRange(almostSorted3_16000, 0, 1000);
        int[] almostSorted4_1000 = Arrays.copyOfRange(almostSorted4_16000, 0, 1000);
        int[] almostSorted5_1000 = Arrays.copyOfRange(almostSorted5_16000, 0, 1000);
        int[] almostSorted6_1000 = Arrays.copyOfRange(almostSorted6_16000, 0, 1000);
        int[] almostSorted7_1000 = Arrays.copyOfRange(almostSorted7_16000, 0, 1000);
        int[] almostSorted8_1000 = Arrays.copyOfRange(almostSorted8_16000, 0, 1000);
        int[] almostSorted9_1000 = Arrays.copyOfRange(almostSorted9_16000, 0, 1000);
        int[] almostSorted10_1000 = Arrays.copyOfRange(almostSorted10_16000, 0, 1000);
        int[] random1_1000 = Arrays.copyOfRange(random1_16000, 0, 1000);
        int[] random2_1000 = Arrays.copyOfRange(random2_16000, 0, 1000);
        int[] random3_1000 = Arrays.copyOfRange(random3_16000, 0, 1000);
        int[] random4_1000 = Arrays.copyOfRange(random4_16000, 0, 1000);
        int[] random5_1000 = Arrays.copyOfRange(random5_16000, 0, 1000);
        int[] random6_1000 = Arrays.copyOfRange(random6_16000, 0, 1000);
        int[] random7_1000 = Arrays.copyOfRange(random7_16000, 0, 1000);
        int[] random8_1000 = Arrays.copyOfRange(random8_16000, 0, 1000);
        int[] random9_1000 = Arrays.copyOfRange(random9_16000, 0, 1000);
        int[] random10_1000 = Arrays.copyOfRange(random10_16000, 0, 1000);
        almost1000.add(almostSorted1_1000);
        almost1000.add(almostSorted2_1000);
        almost1000.add(almostSorted3_1000);
        almost1000.add(almostSorted4_1000);
        almost1000.add(almostSorted5_1000);
        almost1000.add(almostSorted6_1000);
        almost1000.add(almostSorted7_1000);
        almost1000.add(almostSorted8_1000);
        almost1000.add(almostSorted9_1000);
        almost1000.add(almostSorted10_1000);
        random1000.add(random1_1000);
        random1000.add(random2_1000);
        random1000.add(random3_1000);
        random1000.add(random4_1000);
        random1000.add(random5_1000);
        random1000.add(random6_1000);
        random1000.add(random7_1000);
        random1000.add(random8_1000);
        random1000.add(random9_1000);
        random1000.add(random10_1000);

        List<int[]> almost2000 = new ArrayList<int[]>();
        List<int[]> random2000 = new ArrayList<int[]>();
        int[] almostSorted1_2000 = Arrays.copyOfRange(almostSorted1_16000, 0,2000);
        int[] almostSorted2_2000 = Arrays.copyOfRange(almostSorted2_16000, 0, 2000);
        int[] almostSorted3_2000 = Arrays.copyOfRange(almostSorted3_16000, 0, 2000);
        int[] almostSorted4_2000 = Arrays.copyOfRange(almostSorted4_16000, 0, 2000);
        int[] almostSorted5_2000 = Arrays.copyOfRange(almostSorted5_16000, 0, 2000);
        int[] almostSorted6_2000 = Arrays.copyOfRange(almostSorted6_16000, 0, 2000);
        int[] almostSorted7_2000 = Arrays.copyOfRange(almostSorted7_16000, 0, 2000);
        int[] almostSorted8_2000 = Arrays.copyOfRange(almostSorted8_16000, 0, 2000);
        int[] almostSorted9_2000 = Arrays.copyOfRange(almostSorted9_16000, 0, 2000);
        int[] almostSorted10_2000 = Arrays.copyOfRange(almostSorted10_16000, 0, 2000);
        int[] random1_2000 = Arrays.copyOfRange(random1_16000, 0, 2000);
        int[] random2_2000 = Arrays.copyOfRange(random2_16000, 0, 2000);
        int[] random3_2000 = Arrays.copyOfRange(random3_16000, 0, 2000);
        int[] random4_2000 = Arrays.copyOfRange(random4_16000, 0, 2000);
        int[] random5_2000 = Arrays.copyOfRange(random5_16000, 0, 2000);
        int[] random6_2000 = Arrays.copyOfRange(random6_16000, 0, 2000);
        int[] random7_2000 = Arrays.copyOfRange(random7_16000, 0, 2000);
        int[] random8_2000 = Arrays.copyOfRange(random8_16000, 0, 2000);
        int[] random9_2000 = Arrays.copyOfRange(random9_16000, 0, 2000);
        int[] random10_2000 = Arrays.copyOfRange(random10_16000, 0, 2000);
        almost2000.add(almostSorted1_2000);
        almost2000.add(almostSorted2_2000);
        almost2000.add(almostSorted3_2000);
        almost2000.add(almostSorted4_2000);
        almost2000.add(almostSorted5_2000);
        almost2000.add(almostSorted6_2000);
        almost2000.add(almostSorted7_2000);
        almost2000.add(almostSorted8_2000);
        almost2000.add(almostSorted9_2000);
        almost2000.add(almostSorted10_2000);
        random2000.add(random1_2000);
        random2000.add(random2_2000);
        random2000.add(random3_2000);
        random2000.add(random4_2000);
        random2000.add(random5_2000);
        random2000.add(random6_2000);
        random2000.add(random7_2000);
        random2000.add(random8_2000);
        random2000.add(random9_2000);
        random2000.add(random10_2000);

        List<int[]> almost4000 = new ArrayList<int[]>();
        List<int[]> random4000 = new ArrayList<int[]>();
        int[] almostSorted1_4000 = Arrays.copyOfRange(almostSorted1_16000, 0,4000);
        int[] almostSorted2_4000 = Arrays.copyOfRange(almostSorted2_16000, 0, 4000);
        int[] almostSorted3_4000 = Arrays.copyOfRange(almostSorted3_16000, 0, 4000);
        int[] almostSorted4_4000 = Arrays.copyOfRange(almostSorted4_16000, 0, 4000);
        int[] almostSorted5_4000 = Arrays.copyOfRange(almostSorted5_16000, 0, 4000);
        int[] almostSorted6_4000 = Arrays.copyOfRange(almostSorted6_16000, 0, 4000);
        int[] almostSorted7_4000 = Arrays.copyOfRange(almostSorted7_16000, 0, 4000);
        int[] almostSorted8_4000 = Arrays.copyOfRange(almostSorted8_16000, 0, 4000);
        int[] almostSorted9_4000 = Arrays.copyOfRange(almostSorted9_16000, 0, 4000);
        int[] almostSorted10_4000 = Arrays.copyOfRange(almostSorted10_16000, 0, 4000);
        int[] random1_4000 = Arrays.copyOfRange(random1_16000, 0, 4000);
        int[] random2_4000 = Arrays.copyOfRange(random2_16000, 0, 4000);
        int[] random3_4000 = Arrays.copyOfRange(random3_16000, 0, 4000);
        int[] random4_4000 = Arrays.copyOfRange(random4_16000, 0, 4000);
        int[] random5_4000 = Arrays.copyOfRange(random5_16000, 0, 4000);
        int[] random6_4000 = Arrays.copyOfRange(random6_16000, 0, 4000);
        int[] random7_4000 = Arrays.copyOfRange(random7_16000, 0, 4000);
        int[] random8_4000 = Arrays.copyOfRange(random8_16000, 0, 4000);
        int[] random9_4000 = Arrays.copyOfRange(random9_16000, 0, 4000);
        int[] random10_4000 = Arrays.copyOfRange(random10_16000, 0, 4000);
        almost4000.add(almostSorted1_4000);
        almost4000.add(almostSorted2_4000);
        almost4000.add(almostSorted3_4000);
        almost4000.add(almostSorted4_4000);
        almost4000.add(almostSorted5_4000);
        almost4000.add(almostSorted6_4000);
        almost4000.add(almostSorted7_4000);
        almost4000.add(almostSorted8_4000);
        almost4000.add(almostSorted9_4000);
        almost4000.add(almostSorted10_4000);
        random4000.add(random1_4000);
        random4000.add(random2_4000);
        random4000.add(random3_4000);
        random4000.add(random4_4000);
        random4000.add(random5_4000);
        random4000.add(random6_4000);
        random4000.add(random7_4000);
        random4000.add(random8_4000);
        random4000.add(random9_4000);
        random4000.add(random10_4000);


        List<int[]> almost8000 = new ArrayList<int[]>();
        List<int[]> random8000 = new ArrayList<int[]>();
        int[] almostSorted1_8000 = Arrays.copyOfRange(almostSorted1_16000, 0,8000);
        int[] almostSorted2_8000 = Arrays.copyOfRange(almostSorted2_16000, 0, 8000);
        int[] almostSorted3_8000 = Arrays.copyOfRange(almostSorted3_16000, 0, 8000);
        int[] almostSorted4_8000 = Arrays.copyOfRange(almostSorted4_16000, 0, 8000);
        int[] almostSorted5_8000 = Arrays.copyOfRange(almostSorted5_16000, 0, 8000);
        int[] almostSorted6_8000 = Arrays.copyOfRange(almostSorted6_16000, 0, 8000);
        int[] almostSorted7_8000 = Arrays.copyOfRange(almostSorted7_16000, 0, 8000);
        int[] almostSorted8_8000 = Arrays.copyOfRange(almostSorted8_16000, 0, 8000);
        int[] almostSorted9_8000 = Arrays.copyOfRange(almostSorted9_16000, 0, 8000);
        int[] almostSorted10_8000 = Arrays.copyOfRange(almostSorted10_16000, 0, 8000);
        int[] random1_8000 = Arrays.copyOfRange(random1_16000, 0, 8000);
        int[] random2_8000 = Arrays.copyOfRange(random2_16000, 0, 8000);
        int[] random3_8000 = Arrays.copyOfRange(random3_16000, 0, 8000);
        int[] random4_8000 = Arrays.copyOfRange(random4_16000, 0, 8000);
        int[] random5_8000 = Arrays.copyOfRange(random5_16000, 0, 8000);
        int[] random6_8000 = Arrays.copyOfRange(random6_16000, 0, 8000);
        int[] random7_8000 = Arrays.copyOfRange(random7_16000, 0, 8000);
        int[] random8_8000 = Arrays.copyOfRange(random8_16000, 0, 8000);
        int[] random9_8000 = Arrays.copyOfRange(random9_16000, 0, 8000);
        int[] random10_8000 = Arrays.copyOfRange(random10_16000, 0, 8000);
        almost8000.add(almostSorted1_8000);
        almost8000.add(almostSorted2_8000);
        almost8000.add(almostSorted3_8000);
        almost8000.add(almostSorted4_8000);
        almost8000.add(almostSorted5_8000);
        almost8000.add(almostSorted6_8000);
        almost8000.add(almostSorted7_8000);
        almost8000.add(almostSorted8_8000);
        almost8000.add(almostSorted9_8000);
        almost8000.add(almostSorted10_8000);
        random8000.add(random1_8000);
        random8000.add(random2_8000);
        random8000.add(random3_8000);
        random8000.add(random4_8000);
        random8000.add(random5_8000);
        random8000.add(random6_8000);
        random8000.add(random7_8000);
        random8000.add(random8_8000);
        random8000.add(random9_8000);
        random8000.add(random10_8000);

        List<Long> almost1000moves = new ArrayList<Long>();
        List<Long> almost1000compares = new ArrayList<Long>();
        for (int[] i : almost1000){
            moves = 0;
            compares = 0;
            swapSort(i);
            almost1000moves.add(moves);
            almost1000compares.add(compares);
        }
        System.out.println("Moves for 1000 almost sorted numbers: " + Arrays.toString(almost1000moves.toArray()));
        System.out.println("Compares for 1000 almost sorted numbers: " + Arrays.toString(almost1000compares.toArray()));


        List<Long> almost2000moves = new ArrayList<Long>();
        List<Long> almost2000compares = new ArrayList<Long>();
        for (int[] i : almost2000){
            moves = 0;
            compares = 0;
            swapSort(i);
            almost2000moves.add(moves);
            almost2000compares.add(compares);
        }
        System.out.println("Moves for 2000 almost sorted numbers: " + Arrays.toString(almost2000moves.toArray()));
        System.out.println("Compares for 2000 almost sorted numbers: " + Arrays.toString(almost2000compares.toArray()));


        List<Long> almost4000moves = new ArrayList<Long>();
        List<Long> almost4000compares = new ArrayList<Long>();
        for (int[] i : almost4000){
            moves = 0;
            compares = 0;
            swapSort(i);
            almost4000moves.add(moves);
            almost4000compares.add(compares);
        }
        System.out.println("Moves for 4000 almost sorted numbers: " + Arrays.toString(almost4000moves.toArray()));
        System.out.println("Compares for 4000 almost sorted numbers: " + Arrays.toString(almost4000compares.toArray()));


        List<Long> almost8000moves = new ArrayList<Long>();
        List<Long> almost8000compares = new ArrayList<Long>();
        for (int[] i : almost8000){
            moves = 0;
            compares = 0;
            swapSort(i);
            almost8000moves.add(moves);
            almost8000compares.add(compares);
        }
        System.out.println("Moves for 8000 almost sorted numbers: " + Arrays.toString(almost8000moves.toArray()));
        System.out.println("Compares for 8000 almost sorted numbers: " + Arrays.toString(almost8000compares.toArray()));

        List<Long> almost16000moves = new ArrayList<Long>();
        List<Long> almost16000compares = new ArrayList<Long>();
        for (int[] i : almost16000){
            moves = 0;
            compares = 0;
            swapSort(i);
            almost16000moves.add(moves);
            almost16000compares.add(compares);
        }
        System.out.println("Moves for 16000 almost sorted numbers: " + Arrays.toString(almost16000moves.toArray()));
        System.out.println("Compares for 16000 almost sorted numbers: " + Arrays.toString(almost16000compares.toArray()));

        System.out.println("");

        List<Long> random1000moves = new ArrayList<Long>();
        List<Long> random1000compares = new ArrayList<Long>();
        for (int[] i : random1000){
            moves = 0;
            compares = 0;
            swapSort(i);
            random1000moves.add(moves);
            random1000compares.add(compares);
        }
        System.out.println("Moves for 1000 random numbers: " + Arrays.toString(random1000moves.toArray()));
        System.out.println("Compares for 1000 random numbers: " + Arrays.toString(random1000compares.toArray()));


        List<Long> random2000moves = new ArrayList<Long>();
        List<Long> random2000compares = new ArrayList<Long>();
        for (int[] i : random2000){
            moves = 0;
            compares = 0;
            swapSort(i);
            random2000moves.add(moves);
            random2000compares.add(compares);
        }
        System.out.println("Moves for 2000 random numbers: " + Arrays.toString(random2000moves.toArray()));
        System.out.println("Compares for 2000 random numbers: " + Arrays.toString(random2000compares.toArray()));


        List<Long> random4000moves = new ArrayList<Long>();
        List<Long> random4000compares = new ArrayList<Long>();
        for (int[] i : random4000){
            moves = 0;
            compares = 0;
            swapSort(i);
            random4000moves.add(moves);
            random4000compares.add(compares);
        }
        System.out.println("Moves for 4000 random numbers: " + Arrays.toString(random4000moves.toArray()));
        System.out.println("Compares for 4000 random numbers: " + Arrays.toString(random4000compares.toArray()));


        List<Long> random8000moves = new ArrayList<Long>();
        List<Long> random8000compares = new ArrayList<Long>();
        for (int[] i : random8000){
            moves = 0;
            compares = 0;
            swapSort(i);
            random8000moves.add(moves);
            random8000compares.add(compares);
        }
        System.out.println("Moves for 8000 random numbers: " + Arrays.toString(random8000moves.toArray()));
        System.out.println("Compares for 8000 random numbers: " + Arrays.toString(random8000compares.toArray()));

        List<Long> random16000moves = new ArrayList<Long>();
        List<Long> random16000compares = new ArrayList<Long>();
        for (int[] i : random16000){
            moves = 0;
            compares = 0;
            swapSort(i);
            random16000moves.add(moves);
            random16000compares.add(compares);
        }
        System.out.println("Moves for 16000 random numbers: " + Arrays.toString(random16000moves.toArray()));
        System.out.println("Compares for 16000 random numbers: " + Arrays.toString(random16000compares.toArray()));

    }

    public static void testSwapSort(){
        int[] test1 = new int[]{1, 2, 3};
        int[] test2 = new int[]{3, 2, 1, 0, -1};
        int[] test3 = new int[]{15, 8, 20, 5, 12};

        moves = 0;
        compares = 0;
        swapSort(test1);
        System.out.printf("Test1 moves: %d, compares %d \n", moves, compares);

        moves = 0;
        compares = 0;
        swapSort(test2);
        System.out.printf("Test2 moves: %d, compares %d \n", moves, compares);

        moves = 0;
        compares = 0;
        swapSort(test3);
        System.out.printf("Test3 moves: %d, compares %d \n", moves, compares);
    }
}