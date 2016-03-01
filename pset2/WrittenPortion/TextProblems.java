package ProblemSet2.textAnwsers;

import ProblemSet2.ProgrammingProblems.Sort;

/**
 * Created by Paul on 10/7/2015.
 *
 */
public class TextProblems {
    public static int findMode0(int[] arr) {
        int mode = -1;
        int modeFreq = 0; // number of times that the mode appears
        for (int i = 0; i < arr.length; i++) {
            int freq = 1; // number of times that arr[i] appears from
            // position i to the end of the array
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == arr[i]) // how many times is this executed?
                    freq++;
            }

            if (freq > modeFreq || (freq == modeFreq && arr[i] < mode)) {
                mode = arr[i];
                modeFreq = freq;
            }
        }
        return mode;
    }


    public static int findMode(int[] arr) {

        Sort.quickSort(arr);

        int total_freq = 1;
        int mode = arr[0];
        for (int i = arr.length-2; i > -1; i--){

            int freq = 1; // frequency of the current element
            if (arr[i] == arr[i+1]){
                freq++;
                if (freq > total_freq) {
                    total_freq = freq;
                    mode = arr[i];
                }
            }
        }

        return mode;
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{10, 8, 12, 8, 10, 5, 8};
        int[] input2 = new int[]{7, 5, 3, 5, 7, 11, 11};
        int[] input3 = new int[]{71, 775, 13, 25, 7, 131, 11};

        System.out.println(findMode(input1));
        System.out.println(findMode(input2));
        System.out.println(findMode(input3));

    }

    public static void insertAfter(char a){
        DNode head;
//
//        x.next = y;
//        x.prev = y.prev;
//        y.prev.next = x;
//        y.prev = x;
//
    }

}
