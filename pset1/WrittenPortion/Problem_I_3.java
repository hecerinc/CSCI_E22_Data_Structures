package ProblemSet1;

/**
 * Created by Paul on 9/27/2015.
 */
public class Problem_I_3 {


    public static int mystery(int a, int b) {

        System.out.printf("mystery(%d, %d)\n", a, b);

        if (a <= b){
            System.out.printf("return %d\n", a);
            return a;
        }

        else{
            int result =  b + mystery(a - 3, b + 1);
            System.out.printf("return %d\n", result);
            return result;
        }
    }

    public static int sum(int n) {
        System.out.printf("sum(%d)\n",n);
        if (n <= 0) {
            System.out.printf("sum(%d)returns 0\n", n);
            return 0;
        }
        int total = n + sum(n-1);
        System.out.printf("return %d\n", total);
        return total;
    }

    public static void main(String[] args) {
        mystery(10, 1);
        sum(3);
    }
}
