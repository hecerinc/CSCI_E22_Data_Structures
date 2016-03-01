package ProblemSet1;

/**
 * Created by Paul on 9/6/2015.
 */
public class Problem_I_1 {

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8, 10, 12};
        int[] b = new int[6];
        for (int i = 0; i < b.length; i++)
            b[i] = a[i];
        int[] c = b;
        c[4] = a[5];
        a[5] = b[4];
        b[5] = a[4];
        System.out.println(a[5] + " "+ b[5] + " " + c[5]);
    }

}
