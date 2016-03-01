package ProblemSet3.writtenProblems;

import ProblemSet3.programmingProblems.LLList;
import ProblemSet3.programmingProblems.ListIterator;

/**
 * Created by Paul on 11/2/2015.
 */
public class Problem4 {

    public static LLList reverse(LLList list) {

        java.util.ListIterator iter = list.iterator();

        LLList rev = new LLList();
        while (iter.hasNext()) {
            Object item = iter.next();
            rev.addItem(item, 0);
        }

        return rev;

    }

}
