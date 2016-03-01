package ProblemSet2.ProgrammingProblems;
/*
 * Median.java
 *
 * Starter code by Computer Science E-22, Harvard University
 * Modified by <Paul Zeng>
 */

import java.util.Arrays;

public class Median {
    /* partition - helper method for your recursive median-finding method */
    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last)/2];
        int i = first - 1;  // index going left to right
        int j = last + 1;   // index going right to left

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);

            if (i < j)
                Sort.swap(arr, i, j);
            else
                return j;   // index of last element in the left subarray
        }
    }

    /*
     * findMedian - "wrapper" method for your recursive median-finding method.
     * It just makes the initial call to that method, passing it
     * whatever initial parameters make sense.
     */
    public static void findMedian(int[] arr) {
        // add an appropriate call to your recursive method
        medianHelper(arr, 0, arr.length - 1);
    }

    /*
     * Put the definition of your recursive median-finding method below.
     */

    private static void medianHelper(int[] arr, int first, int last){
        int split = partition(arr, first, last); // split is an index
        int median_index = arr.length/2;

        if (median_index < split)
            medianHelper(arr, first, split);      // sort left subarray

        if (median_index >= split+1)
            medianHelper(arr, split+1, last);   // sort right subarray
    }

    private static void qSort(int[] arr, int first, int last) {
        int split = partition(arr, first, last);

        if (first < split)
            qSort(arr, first, split);      // left subarray
        if (last > split + 1)
            qSort(arr, split + 1, last);   // right subarray
    }


    public static void main(String[] args) {
        // the median of this array is 15
        int[] oddLength = {4, 18, 12, 34, 7, 42, 15, 22, 5};

        // the median of this array is the average of 15 and 18 = 16.5
        int[] evenLength = {4, 18, 12, 34, 7, 42, 15, 22, 5, 27};


        /* Put code to test your method here. */
        findMedian(oddLength);
        System.out.println(Arrays.toString(oddLength));
        System.out.println("median by findMedian() is: " + oddLength[oddLength.length / 2]);
        Sort.mergeSort(oddLength);
        System.out.println(Arrays.toString(oddLength));
        System.out.println("median by mergeSort() is: " + oddLength[oddLength.length / 2]);

        System.out.println(" ");

        findMedian(evenLength);
        System.out.println(Arrays.toString(evenLength));
        System.out.println("median by findMedian() is: " + ((double) evenLength[(evenLength.length-1) / 2] + (double) evenLength[evenLength.length / 2])/2);
        Sort.mergeSort(evenLength);
        System.out.println(Arrays.toString(evenLength));
        System.out.println("median by mergeSort() is: " + ((double) evenLength[(evenLength.length-1) / 2] + (double) evenLength[evenLength.length / 2])/2);
        System.out.println(" ");

        // more tests

        // odd lengths arrays
        int[] oddLength1 = {9, 15, 15, 19, 0, 49, 1115, 2232, 511};
        int[] oddLength2 = {0, 0, 0, 0, 1, 1, 1, 1, 1};
        int[] oddLength3 = {9, 8, 7, 6, 5, 4, 3, 2, 1};


        findMedian(oddLength1);
        System.out.println(Arrays.toString(oddLength1));
        System.out.println("median by findMedian() is: " + oddLength1[oddLength1.length / 2]);
        Sort.mergeSort(oddLength1);
        System.out.println(Arrays.toString(oddLength1));
        System.out.println("median by mergeSort() is: " + oddLength1[oddLength1.length / 2]);
        System.out.println(" ");

        findMedian(oddLength2);
        System.out.println(Arrays.toString(oddLength2));
        System.out.println("median by findMedian() is: " + oddLength2[oddLength2.length / 2]);
        Sort.mergeSort(oddLength2);
        System.out.println(Arrays.toString(oddLength2));
        System.out.println("median by mergeSort() is: " + oddLength2[oddLength2.length / 2]);
        System.out.println(" ");

        findMedian(oddLength3);
        System.out.println(Arrays.toString(oddLength3));
        System.out.println("median by findMedian() is: " + oddLength3[oddLength3.length / 2]);
        Sort.mergeSort(oddLength3);
        System.out.println(Arrays.toString(oddLength3));
        System.out.println("median by mergeSort() is: " + oddLength3[oddLength3.length / 2]);
        System.out.println(" ");

        // even lengths arrays
        int[] evenLength1 = {657, 43, 3453, 2345345, 23, 68756, 234, 2342, 2334, 872, 234};
        int[] evenLength2 = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] evenLength3 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] evenLength4 = {1, 3, 5, 7, 9, 11, 13, 15};
        int[] evenLength5 = {0, 3, 6, 9, 12, 15, 18, 21};
        int[] evenLength6 = {25, 20, 15, 10, 5, 0, -5, -10};

        findMedian(evenLength1);
        System.out.println(Arrays.toString(evenLength1));
        System.out.println("median by findMedian() is: " + evenLength1[evenLength1.length / 2]);
        Sort.mergeSort(evenLength1);
        System.out.println(Arrays.toString(evenLength1));
        System.out.println("median by mergeSort() is: " + evenLength1[evenLength1.length / 2]);
        System.out.println(" ");

        findMedian(evenLength2);
        System.out.println(Arrays.toString(evenLength2));
        System.out.println("median by findMedian() is: " + evenLength2[evenLength2.length / 2]);
        Sort.mergeSort(evenLength2);
        System.out.println(Arrays.toString(evenLength2));
        System.out.println("median by mergeSort() is: " + evenLength2[evenLength2.length / 2]);
        System.out.println(" ");

        findMedian(evenLength3);
        System.out.println(Arrays.toString(evenLength3));
        System.out.println("median by findMedian() is: " + evenLength3[evenLength3.length / 2]);
        Sort.mergeSort(evenLength3);
        System.out.println(Arrays.toString(evenLength3));
        System.out.println("median by mergeSort() is: " + evenLength3[evenLength3.length / 2]);
        System.out.println(" ");

        findMedian(evenLength4);
        System.out.println(Arrays.toString(evenLength4));
        System.out.println("median by findMedian() is: " + evenLength4[evenLength4.length / 2]);
        Sort.mergeSort(evenLength4);
        System.out.println(Arrays.toString(evenLength4));
        System.out.println("median by mergeSort() is: " + evenLength4[evenLength4.length / 2]);
        System.out.println(" ");

        findMedian(evenLength5);
        System.out.println(Arrays.toString(evenLength5));
        System.out.println("median by findMedian() is: " + evenLength5[evenLength5.length / 2]);
        Sort.mergeSort(evenLength5);
        System.out.println(Arrays.toString(evenLength5));
        System.out.println("median by mergeSort() is: " + evenLength5[evenLength5.length / 2]);
        System.out.println(" ");

        findMedian(evenLength6);
        System.out.println(Arrays.toString(evenLength6));
        System.out.println("median by findMedian() is: " + evenLength6[evenLength6.length / 2]);
        Sort.mergeSort(evenLength6);
        System.out.println(Arrays.toString(evenLength6));
        System.out.println("median by mergeSort() is: " + evenLength6[evenLength6.length / 2]);
        System.out.println(" ");
    }
}

