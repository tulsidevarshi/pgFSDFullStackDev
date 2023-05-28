package Collection1;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeEx {
    public static void main(String[] args){
        Deque<String> dq = new ArrayDeque<String>();
        dq.add("Ashish");
        dq.add("Pritesh");
        dq.add("Navin");
        dq.add("Chetan");
        dq.offerLast("Vinod");
        System.out.println(dq.poll());
        for (String ab : dq) {
            System.out.print(ab+" ");
        }
        System.out.println();
        System.out.println(dq.pollLast());
        System.out.println(dq.pollFirst());
        System.out.println(dq.peek());
        System.out.println(dq.containsAll(dq));
    }
}
