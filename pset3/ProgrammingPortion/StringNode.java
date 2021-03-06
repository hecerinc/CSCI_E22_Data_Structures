package ProblemSet3.programmingProblems;/*
 * ProblemSet3.programmingProblems.StringNode.java
 *
 * Computer Science E-22
 * Modified by: <Paul Zeng>, <paulmiaozeng@gmail.com>
 */

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * A class for representing a string using a linked list.  Each
 * character of the string is stored in a separate node.
 * <p/>
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list.  The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter.  This
 * approach allows us to use recursion to write many of the methods.
 */
public class StringNode {
    private char ch;
    private StringNode next;

    /**
     * Constructor
     */
    public StringNode(char c, StringNode n) {
        ch = c;
        next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short, returns null.
     */

    private static StringNode getNode(StringNode str, int i) {
        if (i < 0 || str == null)
            return null;
        if (i == 0)
            return str;

        StringNode trav = str;
        int pos = 1;
        while ((trav = trav.next) != null) {
            if (pos == i) {
                return trav;
            }
            pos++;
        }
        return trav;
    }

    /*****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /**
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNode str, int i) {
        if (str == null)
            throw new IllegalArgumentException("the string is empty");

        StringNode node = getNode(str, i);

        if (node != null)
            return node.ch;
        else
            throw new IllegalArgumentException("invalid index: " + i);
    }

    /**
     * concat - returns the concatenation of two linked-list strings
     */
    public static StringNode concat(StringNode str1, StringNode str2) {
        StringNode first, trav;

        if (str1 == null)
            return copy(str2);

        first = copy(str1);
        trav = first;

        while (trav.next != null) {
            trav = trav.next;
        }

        trav.next = copy(str2);

        return first;
    }

    /**
     * contains - returns true if the linked-list string str contains
     * at least one occurrence of the character ch, and false otherwise.
     */
    public static boolean contains(StringNode str, char ch) {
        if (str == null)
            return false;

        if (str.ch == ch)
            return true;

        StringNode trav = str;
        while ((trav = trav.next) != null) {
            if (trav.ch == ch)
                return true;
        }

        return false;
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNode convert(String s) {
        if (s.length() == 0)
            return null;

        StringNode firstNode = new StringNode(s.charAt(0), null);
        StringNode prevNode = firstNode;
        StringNode nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }

    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNode copy(StringNode str) {
        if (str == null)
            return null;

        int pos = 0;
        StringNode trav = str;
        StringNode copyFirst = new StringNode(trav.ch, null);
        StringNode copy = copyFirst;
        while ((trav = trav.next) != null) {
            pos++;
            StringNode newNode = new StringNode(trav.ch, null);
            if (pos == 1) {
                copyFirst.next = newNode;
            }
            copy.next = newNode;
            copy = copy.next;
        }

        return copyFirst;
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNode deleteChar(StringNode str, int i) {
        if (str == null)
            throw new IllegalArgumentException("string is empty");
        else if (i < 0)
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0)
            str = str.next;
        else {
            StringNode prevNode = getNode(str, i - 1);
            if (prevNode != null && prevNode.next != null)
                prevNode.next = prevNode.next.next;
            else
                throw new IllegalArgumentException("invalid index: " + i);
        }

        return str;
    }

    /**
     * indexOf - returns the position of the first occurrence of
     * character ch in the given linked-list string.  If there is
     * none, returns -1.
     */
    public static int indexOf(StringNode str, char ch) {
        if (str == null)
            return -1;

        if (str.ch == ch) {
            return 0;
        }

        int indexOf = -1;
        int temp = 0;
        StringNode trav = str;
        while ((trav = trav.next) != null) {
            temp++;
            if (trav.ch == ch) {
                indexOf = temp;
                return indexOf;
            }
        }
        return indexOf;
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNode insertChar(StringNode str, int i, char ch) {
        StringNode newNode, prevNode;

        if (i < 0)
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0) {
            newNode = new StringNode(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i - 1);
            if (prevNode != null) {
                newNode = new StringNode(ch, prevNode.next);
                prevNode.next = newNode;
            } else
                throw new IllegalArgumentException("invalid index: " + i);
        }

        return str;
    }

    /**
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNode insertSorted(StringNode str, char ch) {
        StringNode newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNode(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else
            trail.next = newNode;

        return str;
    }


    /**
     * length - iteratively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNode str) {

        if (str == null)
            return 0;

        int length = 1;
        StringNode trav = str;
        while (trav.next != null) {
            length++;
            trav = trav.next;
        }
        return length;
    }

    /**
     * numOccurrences - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccurrences(StringNode str, char ch) {
        if (str == null)
            return 0;

        int numOccur = numOccurrences(str.next, ch);
        if (str.ch == ch)
            numOccur++;

        return numOccur;
    }

    /**
     * print - iteratively writes the specified linked-list string to System.out
     */
    public static void print(StringNode str) {
        if (str == null)
            return;

        StringNode trav = str;
        System.out.print(trav.ch);
        while ((trav = trav.next) != null) {
            System.out.print(trav.ch);
        }
    }

    /**
     * printReverse - iteratively writes the reverse of the specified
     * linked-list string to System.out
     */
    public static void printReverse(StringNode str) {
        if (str == null)
            return;
        if (str.next == null)
            System.out.println(str.ch);

        int index = 0;
        int arraySize = 10; // let the array have a default size of 10
        StringNode[] array = new StringNode[arraySize];

        StringNode trav = str;
        while (trav != null){
            array[index] = trav;

            if (index > arraySize-2){
                array = doubleArray(array, index);
                arraySize *= 2;
            }
            trav = trav.next;
            index++;
        }

        // print;
        for (int i = index-1; i >= 0; i--){
            System.out.print(array[i].ch);
        }
        System.out.println("");

    }

    private static StringNode[] doubleArray(StringNode[] oldArray, int maxIndex){

        int newSize = oldArray.length * 2;
        StringNode[] newArray = new StringNode[newSize];
        for (int i = 0; i <= maxIndex; i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }


    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNode read(InputStream in) throws IOException {
        StringNode str;
        char ch = (char) in.read();

        if (ch == '\n')    // base case
            str = null;
        else
            str = new StringNode(ch, read(in));

        return str;
    }

    /*
     * toString - creates and returns the Java string that
     * the current ProblemSet3.programmingProblems.StringNode represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        StringNode trav = this;   // start trav on the current node

        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }

        return str;
    }

    /**
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     */
    public static void toUpperCase(StringNode str) {

        if (str == null)
            return;

        str.ch = Character.toUpperCase(str.ch);
        toUpperCase(str.next);
    }

    /**
     * printMirrored -  This method uses recursion to print the string represented
     * by str in "mirrored" form - in which the characters in str are followed by
     * the same characters in reverse order
     */
    public static void printMirrored(StringNode str) {
        if (str == null)
            return;
        else {
            System.out.print(str.ch);
            printMirrored(str.next);
            System.out.print(str.ch);
        }

    }

    /**
     * substring -  This method should use recursion to create a new linked list
     * that represents the substring of str that begins with the character at index
     * start and ends with the character at index end - 1 (the character at index
     * end is not included, following the convention used by the substring method in the String class).
     */

    public static StringNode substring(StringNode str, int start, int end) {

        StringNode subStart = null;

        if (start < 0 || end < 0 || start > end || (start < end && str == null))
            throw new IllegalArgumentException();

        if (str == null || start == end)
            return null;

        if (start > 0) {
            subStart = substring(str.next, start - 1, end - 1);
        } else {
            subStart = new StringNode(str.ch, null);
            subStart.next = substring(str.next, start, end - 1);
        }
        return subStart;
    }

    public static int nthIndexOf(StringNode str, int n, char ch) {
        if (str == null || n <= 0)
            return -1;
        else if (str.ch == ch) {
            if (n == 1) {
                return 0;
            } else {
                int index = nthIndexOf(str.next, n - 1, ch);
                if (index == -1) {
                    return -1;
                } else {
                    return 1 + index;
                }
            }
        } else {
            int index = nthIndexOf(str.next, n, ch);
            if (index == -1) {
                return -1;
            } else {
                return 1 + index;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        StringNode copy, str, str1, str2, str3;
        String line;
        int n;
        char ch;

        // convert, print, and toUpperCase
        str = StringNode.convert("fine");
        System.out.print("Here's a string: ");
        StringNode.print(str);
        System.out.println();
        System.out.print("Here it is in upper-case letters: ");
        StringNode.toUpperCase(str);
        StringNode.print(str);
        System.out.println();
        System.out.println();

        Scanner in = new Scanner(System.in);

        // read, toString, length, and printReverse.
        System.out.print("Type a string: ");
        String s = in.nextLine();
        str1 = StringNode.convert(s);
        System.out.print("Your string is: ");
        System.out.println(str1);        // implicit toString call
        System.out.print("\nHere it is reversed: ");
        StringNode.printReverse(str1);
        System.out.println("\nIts length is " + StringNode.length(str1) +
                " characters.");

        // charAt
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to get (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        try {
            ch = StringNode.charAt(str1, n);
            System.out.println("That character is " + ch);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }

        // contains and indexOf
        System.out.print("\nWhat character to search for? ");
        line = in.nextLine();
        ch = line.charAt(0);
        System.out.print("Using contains to see if " + ch + " is in the string...");
        if (StringNode.contains(str1, ch)) {
            System.out.println("it is.");
        } else {
            System.out.println("it is not.");
        }
        n = StringNode.indexOf(str1, ch);
        System.out.println("indexOf returns: " + n);

        // deleteChar and copy
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        copy = StringNode.copy(str1);
        try {
            str1 = StringNode.deleteChar(str1, n);
            StringNode.print(str1);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        System.out.print("\nUnchanged copy: ");
        StringNode.print(copy);
        System.out.println();

        // insertChar
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to insert before (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        System.out.print("What character to insert? ");
        line = in.nextLine();
        try {
            str1 = StringNode.insertChar(str1, n, line.charAt(0));
            StringNode.print(str1);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }

        // concat
        System.out.print("\nType another string: ");
        s = in.nextLine();
        str2 = StringNode.convert(s);
        System.out.println("Its length is " + StringNode.length(str2) +
                " characters.");
        System.out.print("\nconcatenation = ");
        StringNode.print(StringNode.concat(str1, str2));
        System.out.println();

        // insertSorted
        System.out.print("\nType a string of characters in alphabetical order: ");
        s = in.nextLine();
        str3 = StringNode.convert(s);
        System.out.print("What character to insert in order? ");
        line = in.nextLine();
        str3 = StringNode.insertSorted(str3, line.charAt(0));
        StringNode.print(str3);
        System.out.println();
    }
}
