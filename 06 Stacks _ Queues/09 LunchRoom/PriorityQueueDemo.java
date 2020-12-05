import java.util.*;
public class PriorityQueueDemo
{
   public static void main(String [] args)
   {
      PriorityQueue<Customer> pq = new PriorityQueue<Customer>();
      //MyPriorityQueue pq = new MyPriorityQueue();
      pq.add(new Customer(0,0));
      pq.add(new Customer(5,3));
      pq.add(new Customer(1,0));
      pq.add(new Customer(5,2));
      pq.add(new Customer(2,0));
//       pq.add("b");
//       pq.add("a");
//       pq.add("d");
//       pq.add("b");
//       pq.add("a");
//       pq.add("d");
//       pq.add("b");
//       pq.add("a");     
      while( !pq.isEmpty() )
         System.out.print( pq.remove() + " ");
   }
}

