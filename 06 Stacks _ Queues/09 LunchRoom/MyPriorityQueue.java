//Name: B6-24
//Date: 1/23/20
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
      pq.add(add);
      Collections.sort(pq);
   }
   
   public E remove() {
      return pq.remove(0);
   }
   
   public E peek() {
      return pq.get(0);
   }
   
   public boolean isEmpty() {
      return pq.isEmpty();
   }
   
   public String toString() {
      return pq.toString();
   }
}