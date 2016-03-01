package ProblemSet1;

/**
 * Created by Paul on 9/6/2015.
 */
public class StringRecursion {

    public static void printWithSpaces(String str){

        if (str.equals("") || str == null)
            return;

        System.out.print(str.charAt(0) + " ");
        printWithSpaces(str.substring(1, str.length()));
    }

    public static String weave(String str1, String str2){

        if (str1 == null || str2 == null)
            throw new IllegalArgumentException();

        if (str1.equals("")) return str2;
        if (str2.equals("")) return str1;

        return str1.substring(0, 1)
                + str2.substring(0, 1)
                + weave(str1.substring(1), str2.substring(1));
    }

    public static int lastIndexOf(char ch, String str){
        if (str == null || str.equals(""))
            return -1;

        int index = -1;

        if (ch == str.charAt(str.length() - 1)){
            index = str.length()-1;
        } else {
            index = lastIndexOf(ch, str.substring(0, str.length()-1));
        }

        return index;
    }

    public static void main(String[] args) {

        printWithSpaces("space");
        System.out.println("");
        printWithSpaces("123");
        System.out.println("");
        printWithSpaces("");
        System.out.println("");


        System.out.println(weave("123", "123"));
        System.out.println(weave("12341", "123"));
        System.out.println(weave("1", "123"));

        System.out.println(lastIndexOf('1', "121"));

    }

}
