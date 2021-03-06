package ProblemSet3.programmingProblems;/*
 * ProblemSet3.programmingProblems.StringNodeCopy.java
 *
 * Computer Science E-22
 * Modified by:     <Paul Zeng>
 */

import java.io.*;
import java.util.*;

/**
 * A class for representing a string using a linked list.  Each
 * character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list.  The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter.  This
 * approach allows us to use recursion to write many of the methods.
 */
public class StringNodeCopy {
    private char ch;
    private StringNodeCopy next;

    /**
     * Constructor
     */
    public StringNodeCopy(char c, StringNodeCopy n) {
        ch = c;
        next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short, returns null.
     */
    public static StringNodeCopy getNode(StringNodeCopy str, int i) {
        if (i < 0 || str == null)
            return null;
        else if (i == 0)
            return str;
        else
            return getNode(str.next, i-1);
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
    public static char charAt(StringNodeCopy str, int i) {
        if (str == null)
            throw new IllegalArgumentException("the string is empty");

        StringNodeCopy node = getNode(str, i);

        if (node != null)
            return node.ch;
        else
            throw new IllegalArgumentException("invalid index: " + i);
    }

    /**
     * concat - returns the concatenation of two linked-list strings
     */
    public static StringNodeCopy concat(StringNodeCopy str1, StringNodeCopy str2) {
        StringNodeCopy cat;

        if (str1 == null)
            cat = copy(str2);
        else
            cat = new StringNodeCopy(str1.ch, concat(str1.next, str2));

        return cat;
    }

    /**
     * contains - returns true if the linked-list string str contains
     * at least one occurrence of the character ch, and false otherwise.
     */
    public static boolean contains(StringNodeCopy str, char ch) {
        if (str == null)
            return false;
        if (str.ch == ch)
            return true;
        else
            return contains(str.next, ch);
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNodeCopy convert(String s) {
        if (s.length() == 0)
            return null;

        StringNodeCopy firstNode = new StringNodeCopy(s.charAt(0), null);
        StringNodeCopy prevNode = firstNode;
        StringNodeCopy nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNodeCopy(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }

    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNodeCopy copy(StringNodeCopy str) {
        if (str == null)
            return null;

        StringNodeCopy copyFirst = new StringNodeCopy(str.ch, null);
        copyFirst.next = copy(str.next);
        return copyFirst;
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNodeCopy deleteChar(StringNodeCopy str, int i) {
        if (str == null)
            throw new IllegalArgumentException("string is empty");
        else if (i < 0)
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0)
            str = str.next;
        else {
            StringNodeCopy prevNode = getNode(str, i-1);
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
    public static int indexOf(StringNodeCopy str, char ch) {
        if (str == null)         // base case 1: ch wasn't found
            return -1;
        else if (str.ch == ch)   // base case 2: ch was just found
            return 0;
        else {
            int indexInRest = indexOf(str.next, ch);
            if (indexInRest == -1)
                return -1;
            else
                return 1 + indexInRest;
        }
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNodeCopy insertChar(StringNodeCopy str, int i, char ch) {
        StringNodeCopy newNode, prevNode;

        if (i < 0)
            throw new IllegalArgumentException("invalid index: " + i);
        else if (i == 0) {
            newNode = new StringNodeCopy(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i-1);
            if (prevNode != null) {
                newNode = new StringNodeCopy(ch, prevNode.next);
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
    public static StringNodeCopy insertSorted(StringNodeCopy str, char ch) {
        StringNodeCopy newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNodeCopy(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else
            trail.next = newNode;

        return str;
    }

    /**
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNodeCopy str) {
        if (str == null)
            return  0;
        else
            return 1 + length(str.next);
    }

    /**
     * numOccurrences - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccurrences(StringNodeCopy str, char ch) {
        if (str == null)
            return 0;

        int numOccur = numOccurrences(str.next, ch);
        if (str.ch == ch)
            numOccur++;

        return numOccur;
    }

    /**
     * print - recursively writes the specified linked-list string to System.out
     */
    public static void print(StringNodeCopy str) {
        if (str == null)
            return;
        else {
            System.out.print(str.ch);
            print(str.next);
        }
    }

    /**
     * printReverse - recursively writes the reverse of the specified 
     * linked-list string to System.out
     */
    public static void printReverse(StringNodeCopy str) {
        if (str == null)
            return;
        else {
            printReverse(str.next);
            System.out.print(str.ch);
        }
    }

    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNodeCopy read(InputStream in) throws IOException {
        StringNodeCopy str;
        char ch = (char)in.read();

        if (ch == '\n')    // base case
            str = null;
        else
            str = new StringNodeCopy(ch, read(in));

        return str;
    }

    /*
     * toString - creates and returns the Java string that
     * the current ProblemSet3.programmingProblems.StringNodeCopy represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        StringNodeCopy trav = this;   // start trav on the current node

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
    public static void toUpperCase(StringNodeCopy str) {
        StringNodeCopy trav = str;
        while (trav != null) {
            trav.ch = Character.toUpperCase(trav.ch);
            trav = trav.next;
        }
    }

    public static void main(String[] args) throws IOException {
        StringNodeCopy copy, str, str1, str2, str3;
        String line;
        int n;
        char ch;

        // convert, print, and toUpperCase
        str = StringNodeCopy.convert("fine");
        System.out.print("Here's a string: ");
        StringNodeCopy.print(str);
        System.out.println();
        System.out.print("Here it is in upper-case letters: ");
        StringNodeCopy.toUpperCase(str);
        StringNodeCopy.print(str);
        System.out.println();
        System.out.println();

        Scanner in = new Scanner(System.in);

        // read, toString, length, and printReverse.
        System.out.print("Type a string: ");
        String s = in.nextLine();
        str1 = StringNodeCopy.convert(s);
        System.out.print("Your string is: ");
        System.out.println(str1);        // implicit toString call
        System.out.print("\nHere it is reversed: ");
        StringNodeCopy.printReverse(str1);
        System.out.println("\nIts length is " + StringNodeCopy.length(str1) +
                " characters.");

        // charAt
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to get (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        try {
            ch = StringNodeCopy.charAt(str1, n);
            System.out.println("That character is " + ch);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }

        // contains and indexOf
        System.out.print("\nWhat character to search for? ");
        line = in.nextLine();
        ch = line.charAt(0);
        System.out.print("Using contains to see if " + ch + " is in the string...");
        if (StringNodeCopy.contains(str1, ch)) {
            System.out.println("it is.");
        } else {
            System.out.println("it is not.");
        }
        n = StringNodeCopy.indexOf(str1, ch);
        System.out.println("indexOf returns: " + n);

        // deleteChar and copy
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        copy = StringNodeCopy.copy(str1);
        try {
            str1 = StringNodeCopy.deleteChar(str1, n);
            StringNodeCopy.print(str1);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        System.out.print("\nUnchanged copy: ");
        StringNodeCopy.print(copy);
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
            str1 = StringNodeCopy.insertChar(str1, n, line.charAt(0));
            StringNodeCopy.print(str1);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }

        // concat
        System.out.print("\nType another string: ");
        s = in.nextLine();
        str2 = StringNodeCopy.convert(s);
        System.out.println("Its length is " + StringNodeCopy.length(str2) +
                " characters.");
        System.out.print("\nconcatenation = ");
        StringNodeCopy.print(StringNodeCopy.concat(str1, str2));
        System.out.println();

        // insertSorted
        System.out.print("\nType a string of characters in alphabetical order: ");
        s = in.nextLine();
        str3 = StringNodeCopy.convert(s);
        System.out.print("What character to insert in order? ");
        line = in.nextLine();
        str3 = StringNodeCopy.insertSorted(str3, line.charAt(0));
        StringNodeCopy.print(str3);
        System.out.println();
    }
}
