package ProblemSet4.written_problems;

/**
 * Created by Paul on 11/14/2015.
 */
public class problem2 {

//    public int depth(int key) {
//        if (root == null) // root is the root of the entire tree
//            throw new IllegalStateException("the tree is empty");
//        return depthInTree(key, root);
//    }


    // original
//    private static int depthInTree(int key, Node root) {
//        if (key == root.key)
//            return 0;
//        if (root.left != null) {
//            int depthInLeft = depthInTree(key, root.left);
//            if (depthInLeft != -1)
//                return depthInLeft + 1;
//        }
//        if (root.right != null) {
//            int depthInRight = depthInTree(key, root.right);
//            if (depthInRight != -1)
//                return depthInRight + 1;
//        }
//        return -1;
//    }


    // revised
//        private static int depthInTree(int key, Node root) {
//        if (key == root.key)
//            return 0;
//        if (root.key < key && root.left != null) {
//            int depthInLeft = depthInTree(key, root.left);
//            if (depthInLeft != -1)
//                return depthInLeft + 1;
//        }
//        if (root.key > key && root.right != null) {
//            int depthInRight = depthInTree(key, root.right);
//            if (depthInRight != -1)
//                return depthInRight + 1;
//        }
//        return -1;
//    }

}
