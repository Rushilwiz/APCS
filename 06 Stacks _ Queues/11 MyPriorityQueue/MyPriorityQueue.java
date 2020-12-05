//Name:
//Date:
//extension to LunchRoom 
//implements java.util.PriorityQueue
//   uses an ArrayList in sorted order
//LunchRoom is the driver class

import java.util.*;
public class MyPriorityQueue<E extends Comparable>
{
   private List<E> pq;

   public MyPriorityQueue() {
      pq = new ArrayList<E>();
   }
   
   public void add(E add) {
      pq.add(E);
      Collections.sort(pq);
   }
   
   public E remove() {
      return pq.remove(0);
   }
   
   public E peek() {
      return pq[0];
   }
   
   public boolean isEmpty() {
      return pq.isEmpty();
   }
}