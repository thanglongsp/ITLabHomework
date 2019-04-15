import java.util.LinkedList;
import java.util.Queue;

public class QueueLinkedList {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();

        for (int i = 0; i < 5; i++){
            queue.add("thanglong"+i);
        }

        // show all
        for (String s : queue)
            System.out.println(s);

        // delete
        System.out.println("way 1 : ");
        queue.remove();
        for (String s : queue)
            System.out.println(s);

        System.out.println("way 2 : ");
        queue.remove("thanglong3");
        for (String s : queue)
            System.out.println(s);
    }
}
