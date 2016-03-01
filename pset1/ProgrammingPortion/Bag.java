/*
 * Bag.java
 *
 * Author:          Computer Science E-22 staff
 */

package ProblemSet1;
/**
 * An interface for a Bag ADT.
 */
public interface Bag {
    /** 
     * adds the specified item to the Bag.  Returns true on success
     * and false if there is no more room in the Bag.
     */
    boolean add(Object item);

    /** 
     * removes one occurrence of the specified item (if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    boolean remove(Object item);

    /**
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */
    boolean contains(Object item);

    /**
     * returns true if the calling object contain all of the items in
     * otherBag, and false otherwise.  Also returns false if otherBag 
     * is null or empty. 
     */
    boolean containsAll(Bag otherBag);

    /**
     * returns the number of items in the Bag.
     */
    int numItems();

    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    Object grab();

    /**
     * toArray - return an array containing the current contents of the bag
     */
    Object[] toArray();

    /*
     * This method should return the maximum number of items that the ArrayBag is able to hold.
     * This value does not depend on the number of items that are currently in the ArrayBag
     * – it is the same as the maximum size specified when the ArrayBag was created.
     */
    int capacity();


    /*
     * This method should return true if the ArrayBag is empty, and false otherwise.
     */
    public boolean isEmpty();

    /*
     * This method should return the number of times that the parameter item occurs in
     * the called ArrayBag. For example, if b1 represents the bag {7, 5, 3, 5, 7, 2, 7},
     * then b1.numOccur(2) should return 1, b1.numOccur(7) should return 3, and b1.numOccur(20)
     * should return 0.
     */
    public int numOccur(Object item);

    /*
     * This method should attempt to add to the called ArrayBag all of the items found in the parameter other.
     * If there is currently room for all of the items to be added, the items should be added and the
     * method should return true. If there isn’t enough room for all of the items to be added,
     * none of them should be added and the method should return false.
     */
    public boolean addItems(Bag other);

    /*
     * This method should determine if the called ArrayBag is equal to the parameter other.
     */
    public boolean equals(Bag other);


    /*
     * This method should create and return an ArrayBag containing one occurrence of any item that
     * is found in either the called object or the parameter other.
     */
    public Bag unionWith(Bag other);





} 
