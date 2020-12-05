// Name: B6-24
// Date: 1/8/20

import java.util.*;

public class McRonald1000
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   
   public static void main(String[] args)
   {
      int totalCustomers, totalWait, totalOverall, longestWait, longestQueue, longestDay;
      totalCustomers = totalWait = totalOverall = longestWait = longestQueue = longestDay = 0;
      
      int wait = (int) (Math.random() * 6) + 2;
      
      Queue<Integer> customers = new LinkedList<>();
      for (int j  = 0; j < 1000; j++) {
         for (int i = 0; i < TIME; i++) {
            if (Math.random() < 0.2)
               customers.add(i);
            if (wait == 0) {
               if (i - customers.peek() > longestWait)
                  longestWait = i - customers.peek();
               totalWait += i - customers.remove();
               wait = (int) (Math.random() * 6) + 2;
               totalCustomers++;
            } else
               if (!customers.isEmpty())
                  wait--;
            
            if (customers.size() > longestQueue)
               longestQueue = customers.size();
         }
         
         for (int i = TIME; !customers.isEmpty(); i++) {                 
            if (wait == 0) {
               if (i - customers.peek() > longestWait)
                  longestWait = i - customers.peek();
               totalWait += i - customers.remove();
               totalCustomers++;
            } else
               if (!customers.isEmpty())
                  wait--;
            
            if (customers.size() > longestQueue)
               longestQueue = customers.size();
         }
         
         totalOverall += totalCustomers;
         if (totalCustomers > longestDay)
            longestDay = totalCustomers;
            
            
         totalCustomers = 0;
         wait = (int) (Math.random() * 6) + 2;
      }
      
      
      System.out.println("Total customers served = " + totalOverall);
      System.out.println("Average wait time = " + (double) totalWait / totalOverall);
      System.out.println("Longest wait time = " + longestWait);
      System.out.println("Longest queue  = " + longestQueue);
      System.out.println("Average served per day = " + (double) totalOverall / 1000);
      System.out.println("Largest day = " + longestDay);
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println(min + ": " + q);
   }
}

// class Customer      // if you want a Customer class
// {
//
// }
