package ProblemSet3.programmingProblems;/*
 * ProblemSet3.programmingProblems.ListIterator.java
 *
 * Computer Science E-22, Harvard University
 */

/**
 * An interface for an iterator that iterates over a ProblemSet3.programmingProblems.List.
 */
public interface ListIterator {
    /**
     * does the iterator have additional items to visit?
     */
    boolean hasNext();
    
    /**
     * return a reference to the next Object in the iteration
     */
    Object next();
}
