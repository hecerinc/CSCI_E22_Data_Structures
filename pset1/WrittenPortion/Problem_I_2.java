package ProblemSet1;

import java.util.Arrays;

/**
 * Created by Paul on 9/6/2015.
 */
public class Problem_I_2 {

    private static final int[] arr0 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private static final int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int[] arr2 = {12, 2, 3123, 41, 52, 6, 7, 8, 91, 10};
    private static int[] arr3;
    private static final int[] arr4 = {};
    private static final int[] arr5 = {1};
    private static final int[] arr6 = {2, 1};

    public static void swapPairs(int[] arr){

        if (arr == null ){
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < arr.length-1; i += 2){
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
        }
    }


    public static int longestSorted(int[] arr){

        if (arr == null) {
            throw new IllegalArgumentException();
        }

        if (arr.length == 0) return 0;
        if (arr.length == 1) return 1;

        int length = 1; // the length of the longest increasing sequence
        int curr_len = 1; // the length of currently running sequence

        // at this point, arr should have at least two elements
        for (int i = 0; i < arr.length-1; i++){

            if (arr[i] < arr[i+1]){
                curr_len++;
            } else {
                curr_len = 1;
            }

            if (curr_len > length) {
                length = curr_len; // update the length of the longest increasing sequence
            }
        }

        return length;
    }


    public static void main(String[] args) {

        swapPairs(arr0);
        System.out.println(Arrays.toString(arr0));

        System.out.println(longestSorted(arr1));
        System.out.println(longestSorted(arr2));
//        System.out.println(longestSorted(arr3));
        System.out.println(longestSorted(arr4));
        System.out.println(longestSorted(arr5));
        System.out.println(longestSorted(arr6));


    }


}
