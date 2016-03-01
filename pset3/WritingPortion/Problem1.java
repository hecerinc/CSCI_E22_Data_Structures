package ProblemSet3.writtenProblems;

import ProblemSet2.textAnwsers.DNode;

/**
 * Created by Paul on 10/31/2015.
 */
public class Problem1 {



    class DNode{
        private char ch;
        private DNode next;
        private DNode prev;
    }


    public static DNode reverseTraverse(DNode tail){

        DNode trav = tail;
        while ((trav.prev != null)){
            trav.prev.next = trav;
            trav = trav.prev;
        }
        return trav;
    }


}
