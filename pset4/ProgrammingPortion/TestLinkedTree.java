package ProblemSet4;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Created by Paul on 11/15/2015.
 */


public class TestLinkedTree extends TestCase{

    @Test
    public void testTestLinkedTreeConstructor(){

        int[] keys0 = {0, 1, 2, 3, 4, 5, 6};
        Object[] dataItems0 = {"0", "1", "2", "3", "4", "5", "6"};
        LinkedTree tree0 = new LinkedTree(keys0, dataItems0);
        tree0.levelOrderPrint();
        System.out.println("\n");

        int[] keys1 = {0, 1, 2, 3, 4, 5, 6, 7};
        Object[] dataItems1 = {"0", "1", "2", "3", "4", "5", "6", "7"};
        LinkedTree tree1 = new LinkedTree(keys1, dataItems1);
        tree1.levelOrderPrint();
        System.out.println("\n");

        int[] keys2 = {0, 1, 2, 3, 4, 5, 6, 7, 10, 12, 24, 25};
        Object[] dataItems2 = {"0", "1", "2", "3", "4", "5", "6", "7", "10", "12", "24", "25"};
        LinkedTree tree2 = new LinkedTree(keys2, dataItems2);
        tree2.levelOrderPrint();
        System.out.println("\n");

    }

    @Test
    public void testIsomorphic(){
        int[] keys0 = {44, 35, 23, 28, 53, 48, 62, 57, 80, 45};
        Object[] dataItems0 = {"44", "35", "23", "28", "53", "48", "62", "57", "80", "45"};
        LinkedTree tree0 = new LinkedTree(keys0, dataItems0);
        tree0.levelOrderPrint();
        System.out.println("\n");
        tree0.inorderPrint();
        System.out.println("\n");
        LinkedTreeIterator inorderIterator0 = tree0.inorderIterator();
        while (inorderIterator0.hasNext()){
            System.out.println(inorderIterator0.next());
        }
        System.out.println("\n");

        int[] keys1 = {32, 15, 7, 12, 40, 35, 52, 47, 90, 100};
        Object[] dataItems1 = {"32", "15", "7", "12", "40", "35", "52", "47", "90", "100"};
        LinkedTree tree1 = new LinkedTree(keys1, dataItems1);
        tree1.levelOrderPrint();
        System.out.println("\n");
        tree1.inorderPrint();
        System.out.println("\n");
        LinkedTreeIterator inorderIterator1 = tree1.inorderIterator();
        while (inorderIterator1.hasNext()){
            System.out.println(inorderIterator1.next());
        }

        System.out.println("\n");

        assertTrue(tree0.isomorphicTo(tree1));



        int[] keys4 = {32, 15, 7, 12, 40, 35, 52, 47, 90, 34};
        Object[] dataItems4 = {"32", "15", "7", "12", "40", "35", "52", "47", "90", "34"};
        LinkedTree tree4 = new LinkedTree();
        for (int i = 0; i < keys4.length; i++){
            tree4.insert(keys4[i], dataItems4[i]);
        }
        tree4.levelOrderPrint();
        System.out.println("\n");
        tree4.inorderPrint();
        System.out.println("\n");
        LinkedTreeIterator inorderIterator4 = tree4.inorderIterator();
        while (inorderIterator4.hasNext()){
            System.out.println(inorderIterator4.next());
        }
        System.out.println("\n");

    }

}
