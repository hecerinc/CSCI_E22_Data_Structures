package ProblemSet3.writtenProblems;

import ProblemSet2.textAnwsers.DNode;

import java.util.Queue;

/**
 * Created by Paul on 11/2/2015.
 */
public class Problem5 {

    public interface Stack {
        boolean push(Object item);
        Object pop();
        Object peek();
        boolean isEmpty();
        boolean isFull();
    }

    public interface Queue<T> {
        boolean insert(T item);
        T remove();
        T peek();
        boolean isEmpty();
        boolean isFull();
    }

    boolean contains(Stack stack, Object item){

        boolean ret = false;
        if (stack.isEmpty())
            return ret;

        Queue queue = new QueueImpl(); // QueueImpl is a class that implements Queue
        while(!stack.isEmpty()){

            Object curr = stack.pop();
            queue.insert(curr);
            if (curr.equals(item))
                ret = true;
        }

        // The following 3 while loops restore the stack back to its original state
        while(!queue.isEmpty()){
            stack.push(queue.remove());
        }

        while(!stack.isEmpty()){
            queue.insert(stack.pop());
        }

        while(!queue.isEmpty()){
            stack.push(queue.remove());
        }

        return ret;

    }

    private class QueueImpl implements Queue{

        @Override
        public boolean insert(Object item) {
            return false;
        }

        @Override
        public Object remove() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean isFull() {
            return false;
        }
    }
}














