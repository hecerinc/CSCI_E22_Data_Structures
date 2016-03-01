package ProblemSet2.textAnwsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Paul on 10/5/2015.
 */
public class LectureSort {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static int num_selection_sort = 0;
    private static int indexSmallest(int[] arr,
                                     int lower, int upper) {
        int indexMin = lower;
        for (int i = lower+1; i <= upper; i++){
            num_selection_sort++;
            if (arr[i] < arr[indexMin])
                indexMin = i;
        }

        return indexMin;
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int j = indexSmallest(arr, i, arr.length-1);
            swap(arr, i, j);
        }
    }

    static int num_insertion_sort = 0;
    public static void insertionSort(int[] arr) {

        Set<Integer> has = new HashSet<Integer>();
        for (int i = 1; i < arr.length; i++) {
            num_insertion_sort++;
            if (arr[i] < arr[i-1]) {
                int toInsert = arr[i];
                int j = i;
                do {
                    has.add(i);
                    arr[j] = arr[j-1];
                    j = j - 1;
                } while (j > 0 && toInsert < arr[j-1]);
                arr[j] = toInsert;
            }
        }
        System.out.printf("has %d items \n", has.size());
    }

    public static void shellSort(int[] arr) {
        int incr = 3;
//        while (2 * incr <= arr.length)
//            incr = 2 * incr;
//        incr = incr - 1;

        while (incr >= 1) {
            for (int i = incr; i < arr.length; i++) {
                if (arr[i] < arr[i-incr]) {
                    int toInsert = arr[i];
                    int j = i;
                    do {
                        arr[j] = arr[j-incr];
                        j = j - incr;
                    } while (j > incr-1 &&
                            toInsert < arr[j-incr]);
                    arr[j] = toInsert;
                }
            }
            incr = incr/2;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1])
                    swap(arr, j, j+1);
            }
        }
    }

    private static int partition(int[] arr, int first, int last)
    {
        int pivot = arr[(first + last)/2];
        int i = first - 1; // index going left to right
        int j = last + 1; // index going right to left
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i < j)
                swap(arr, i, j);
            else
                return j; // arr[j] = end of left array
        }
    }

    public static void quickSort(int[] arr) {
        qSort(arr, 0, arr.length-1);
    }
    private static void qSort(int[] arr, int first, int last) {
        int split = partition(arr, first, last);
        if (first < split)
            qSort(arr, first, split); // left subarray
        if (last > split + 1)
            qSort(arr, split + 1, last); // right subarray
    }

    static int num_merge_sort = 0;
    public static void merge(int[] arr, int[] temp,
                             int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart; // index into left subarray
        int j = rightStart; // index into right subarray
        int k = leftStart; // index into temp
        while (i <= leftEnd && j <= rightEnd) {
            num_merge_sort++;
            if (arr[i] < arr[j])
                temp[k++] = arr[i++]; // assign and then increment
            else
                temp[k++] = arr[j++];
        }
        while (i <= leftEnd)
            temp[k++] = arr[i++];
        while (j <= rightEnd)
            temp[k++] = arr[j++];
        for (i = leftStart; i <= rightEnd; i++)
            arr[i] = temp[i];
    }

    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        mSort(arr, temp, 0, arr.length - 1);
    }

    public static void mSort(int[] arr, int[] temp,
                             int start, int end) {
        if (start >= end) // base case
            return;
        int middle = (start + end) / 2;
        mSort(arr, temp, start, middle);
        mSort(arr, temp, middle + 1, end);
        merge(arr, temp, start, middle, middle + 1, end);
    }

    public static void main(String[] args) {
        int[] arrSelectionSortA = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrInsertionSortB = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrShellSortC = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrBubbleSortD = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrQuickSortE = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrRadixSortF = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
        int[] arrMergeSortG = new int[]{24, 3, 27, 13, 34, 2, 50, 12};
//        LectureSort lectureSort = new LectureSort();
//        selectionSort(arrSelectionSortA);
//        insertionSort(arrInsertionSortB);
//        shellSort(arrShellSortC);
//        bubbleSort(arrBubbleSortD);
//        quickSort(arrQuickSortE);
//        mergeSort(arrMergeSortG);


        int[] sortedSelectionSort = new int[]{0, 1, 2, 3, 4, 5};
        int[] sortedInsertionSort = new int[]{0, 1, 2, 3, 4, 5};
        int[] sortedMergeSort = new int[]{0, 1, 2, 3, 4, 5};

        selectionSort(sortedSelectionSort);
        System.out.println("num_selection_sort: " + num_selection_sort);

        insertionSort(sortedInsertionSort);
        System.out.println("num_insertion_sort: " + num_insertion_sort);

        mergeSort(sortedMergeSort);
        System.out.println("num_merge_sort: " + num_merge_sort);


    }

}
