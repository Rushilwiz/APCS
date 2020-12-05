// Name: B6-24
// Date: 1/8/20

import java.util.*;

public class McRonald3
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   
   public static void main(String[] args)
   {
      int totalCustomers, totalWait, longestWait, longestQueue;
      totalWait = totalCustomers = longestWait = longestQueue = 0;
      
      int wait = (int) (Math.random() * 6) + 2;
      
      
      ArrayList<Window> windows = new ArrayList<>();
      for (int i = 0; i < 5; i++)
         windows.add(new Window());
         
      Queue<Integer> displayQueue = new LinkedList<Integer>();   
      int i = 0;
      for (i = 0; i < TIME; i++) {
         for (Window window : windows)
            displayQueue.addAll(window.processList(i));
         display(displayQueue, i);
         displayQueue = new LinkedList<Integer>();
      }
      
      while (!windows.get(0).getCustomers().isEmpty() || !windows.get(1).getCustomers().isEmpty() || !windows.get(2).getCustomers().isEmpty()) {
         i++;
         for (Window window : windows)
            if (!window.getCustomers().isEmpty())
               displayQueue.addAll(window.finishList(i));
         display(displayQueue, i);
         displayQueue = new LinkedList<Integer>();
      }
      
   //       }                          
   //          for (Queue customers : windows) {
   //             int i = TIME;
   //             while (!customers.isEmpty()) {
   //                if (wait == 0) {
   //                   if (i - (int)customers.peek() > longestWait)
   //                      longestWait = i - (int)customers.peek();
   //                   totalWait += i - (int)customers.remove();
   //                   totalCustomers++;
   //                } else
   //                   if (!customers.isEmpty())
   //                      wait--;
   //                
   //                if (customers.size() > longestQueue)
   //                   longestQueue = customers.size();
   //                
   //                display(customers, i);
   //                i++;
   //             }
   //          }
         
         
      for (Window window : windows) {
         totalCustomers += window.getTotalCustomers();
         totalWait += window.getTotalWait();
         if (window.getLongestWait() > longestWait)
            longestWait = window.getLongestWait();
         
         if (window.getLongestQueue() > longestQueue)
            longestQueue = window.getLongestQueue();   
      
      }
      
      System.out.println("Total customers served = " + totalCustomers);
      System.out.println("Average wait time = " + (double) totalWait / totalCustomers);
      System.out.println("Longest wait time = " + longestWait);
      System.out.println("Longest queue  = " + longestQueue);
      
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      
      ArrayList list = new ArrayList(q);
      Collections.sort(list);
      System.out.println(min + ": " + list);
   }
}




class Window      // if you want a Customer class
{
   private Queue<Integer> customers;
   private int totalCustomers, totalWait, longestWait, longestQueue, wait;

   public int getTotalCustomers() {
      return totalCustomers;
   }
   
   public int getTotalWait() {
      return totalWait;
   }
   
   public int getLongestWait() {
      return longestWait;
   } 
   
   public int getLongestQueue () {
      return longestQueue;
   }
   
   public void addToLine (int i) {
      customers.add(i);
   }
   
   public Window() {
      customers = new LinkedList<>();
      totalWait = totalCustomers = longestWait = longestQueue = 0;
      wait = (int) (Math.random() * 6) + 2;
   }
   
   public Queue<Integer> processList(int i) {
      //for (int i = 0; i < 1080; i++) {
         if (Math.random() < 0.2)
            customers.add(i);
                    
         if (wait == 0) {
            if (i - (int)customers.peek() > longestWait)
               longestWait = i - (int)customers.peek();
            totalWait += i - (int)customers.remove();
            wait = (int) (Math.random() * 6) + 2;
            totalCustomers++;
         } else
            if (!customers.isEmpty())
               wait--;
            
         if (customers.size() > longestQueue)
            longestQueue = customers.size();
            
         return customers;
      
   }
 public Queue<Integer> finishList(int i) {
      //for (int i = 0; i < 1080; i++) {
         if (wait == 0) {
            if (i - (int)customers.peek() > longestWait)
               longestWait = i - (int)customers.peek();
            totalWait += i - (int)customers.remove();
            wait = (int) (Math.random() * 6) + 2;
            totalCustomers++;
         } else
            if (!customers.isEmpty())
               wait--;
            
         if (customers.size() > longestQueue)
            longestQueue = customers.size();
            
         return customers;
      
   }

 
   
    public void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println(min + ": " + q);
   }
   
   public Queue<Integer> getCustomers(){
      return customers;
   }

}
