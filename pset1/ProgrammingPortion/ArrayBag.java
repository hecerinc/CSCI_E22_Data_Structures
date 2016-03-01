/*
 * ArrayBag.java
 *
 * Author:          Computer Science E-22 staff
 */

package ProblemSet1;
import java.util.*;

/**
 * An implementation of a Bag ADT using an array.
 */
public class ArrayBag implements Bag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Default, no-arg constructor - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        items = new Object[DEFAULT_MAX_SIZE];
        numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0)
            throw new IllegalArgumentException("maxSize must be > 0");
        items = new Object[maxSize];
        numItems = 0;
    }
    
    /** 
     * add - adds the specified item to the Bag.  Returns true on
     * success and false if there is no more room in the Bag.
     */
    public boolean add(Object item) {
        if (item == null)
            throw new IllegalArgumentException("item must not be null");
        if (numItems == items.length)
            return false;              // no more room!
        else {
            items[numItems] = item;
            numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from the Bag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < numItems - 1; j++) 
                    items[j] = items[j + 1];
                items[numItems - 1] = null;
                
                numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < numItems; i++) {
            if (item.equals(items[i]))
                return true;
        }
        
        return false;
    }
    
    /**
     * containsAll - does this ArrayBag contain all of the items in
     * otherBag?  Returns false if otherBag is null or empty. 
     */
    public boolean containsAll(Bag otherBag) {
        if (otherBag == null || otherBag.numItems() == 0)
            return false;
        
        Object[] otherItems = otherBag.toArray();
        for (int i = 0; i < otherItems.length; i++) {
            if (!contains(otherItems[i]))
                return false;
        }
        
        return true;
    }
    
    /**
     * numItems - returns the number of items in the Bag.
     */
    public int numItems() {
        return numItems;
    }
    
    /**
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
        if (numItems == 0)
            throw new NoSuchElementException("the bag is empty");
        int whichOne = (int)(Math.random() * numItems);
        return items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[numItems];
        
        for (int i = 0; i < numItems; i++)
            copy[i] = items[i];
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a readable String object.
     * Overrides the Object version of this method.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < numItems; i++)
            str = str + " " + items[i];
        str = str + " }";
        
        return str;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner in = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("Size of bag 1: ");
        int size = in.nextInt();
        Bag bag1 = new ArrayBag(size);
        in.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = in.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++)
            System.out.println(items[i]);
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = in.nextLine();
        if (bag1.contains(itemStr))
            bag1.remove(itemStr);
        System.out.println("bag 1 = " + bag1);
        System.out.println();

        Bag bag2 = new ArrayBag(4);
        bag2.add("11");
        bag2.add("22");
        bag2.add("33");
        bag2.add("44");
        System.out.println("bag1 contains: " + bag1.toString());
        System.out.println("bag2 contains: " + bag2.toString());
        System.out.println("Does bag1 equal bag2? " + bag1.equals(bag2));
        System.out.println("Does bag1 contain everything in bag2? " + bag1.containsAll(bag2));
        System.out.println("Does bag2 contain everything in bag1? " + bag2.containsAll(bag1));
        System.out.println("What is the union of bag1 and bag2? " + bag1.unionWith(bag2).toString());
    }

    /*
     * This method should return the maximum number of items that the ArrayBag is able to hold.
     * This value does not depend on the number of items that are currently in the ArrayBag
     * – it is the same as the maximum size specified when the ArrayBag was created.
     */
    public int capacity(){
        return DEFAULT_MAX_SIZE;
    };


    /*
     * This method should return true if the ArrayBag is empty, and false otherwise.
     */
    public boolean isEmpty(){
        return numItems == 0;
    };

    /*
     * This method should return the number of times that the parameter item occurs in
     * the called ArrayBag. For example, if b1 represents the bag {7, 5, 3, 5, 7, 2, 7},
     * then b1.numOccur(2) should return 1, b1.numOccur(7) should return 3, and b1.numOccur(20)
     * should return 0.
     */
    public int numOccur(Object item){

        int count = 0;

        for (int i = 0; i < items.length; i++){
            if (item.equals(items[i]))
                count++;
        }

        return count;
    };

    /*
     * This method should attempt to add to the called ArrayBag all of the items found in the parameter other.
     * If there is currently room for all of the items to be added, the items should be added and the
     * method should return true. If there isn’t enough room for all of the items to be added,
     * none of them should be added and the method should return false.
     */
    public boolean addItems(Bag other){

        if (numItems + other.numItems() > DEFAULT_MAX_SIZE)
            return false;

        for (Object item : other.toArray()){
            for (int i = numItems; i < DEFAULT_MAX_SIZE; i++)
                items[i] = item;
        }

        return true;
    };

    /*
     * This method should determine if the called ArrayBag is equal to the parameter other.
     */
    public boolean equals(Bag other){

        if (other == null || other.numItems() != numItems) return false;
        for (Object item : items) {
            if (!other.contains(item))
                return false;
        }
        return true;
    };


    /*
     * This method should create and return an ArrayBag containing one occurrence of any item that
     * is found in either the called object or the parameter other.
     */
    public Bag unionWith(Bag other){
        Bag ret = new ArrayBag(numItems + other.numItems());

        // Adding invoking object's items to the bag
        for (Object item : items){
            if(item == null){
                continue;
            }
            if (!ret.contains(item)){
                ret.add(item);
            }
        }

        // Adding the invoked object's items to the bag
        for (Object item : other.toArray()){
            if (!ret.contains(item)){
                ret.add(item);
            }
        }

        return ret;
    };
}
