package ProblemSet1;

/**
 * Created by Paul on 9/27/2015.
 */
public class Problem_I_4 {
    public static void printReverse(Object[] arr, int i){

        if (i >= arr.length-1){
            System.out.println(arr[i]);
            return;
        } else {
            printReverse(arr, i+1);
            System.out.println(arr[i]);
            return;
        }
    }

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        printReverse(arr, 0);

    }

}
