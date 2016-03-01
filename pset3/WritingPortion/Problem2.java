package ProblemSet3.writtenProblems;

/**
 * Created by Paul on 11/2/2015.
 */
public class Problem2 {

    public class IntNode {
        private int val;
        private IntNode next;

        public IntNode(IntNode next, int val) {
            this.next = next;
            this.val = val;
        }
    }


    /* test cases;
        1. 1 3 5 7 9
        2. 1 2 3 4 5
        3. 1 3 5 8
        4. 1 3 5 8 10
        5 null
        6. 0
        7. 1, 2
        8. 2, 3, 4
        9. 1
        10. 0, 1

    */
    public static IntNode removeOdds(IntNode head) {

        if (head == null){
            return null;
        }

        // If the list consists entirely of odd valued nodes, then head == null at the end of this while loop
        // and we'd return null at the end of this method
        while ((head.val % 2) != 0) {
            head = head.next;
        }

        // head must be even at this point
        IntNode trav = head;
        IntNode trail = null;

        while (trav != null) {
            if ((trav.val % 2) != 0) {
                trail.next = trav.next;
            } else {
                trail = trav;
            }
            trav = trav.next;

        }

        return head;

    }

    public static void main(String[] args) {

    }
}

