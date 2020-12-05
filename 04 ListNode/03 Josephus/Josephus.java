// Name: B6-24
// Date: 11/15/19

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Rushil";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode head, p;
      head = p = new ListNode("A", null);
      p.setNext(head);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
       Scanner sc = new Scanner(System.in);
       System.out.print("How many names (2-20)? ");
       int n = Integer.parseInt(sc.next());
       System.out.print("How many names to count off each time?"  );
       int countOff = Integer.parseInt(sc.next());
       ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
       System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
         System.out.println("\n ****  Now start all over. **** \n");
         System.out.print("Where should "+WINNER+" stand?  ");
         int winPos = Integer.parseInt(sc.next());        
         ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
         System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner file = null;
      
      try {
         file = new Scanner(f);
      } catch(FileNotFoundException e) {
         return null;
      }
      
      ListNode list = null;
      for (int i = 0; i < n; i++)
         list = insert(list, file.nextLine());
      return list;
   }
   
   
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      if (p == null) {
         ListNode node = new ListNode(obj, null);
         node.setNext(node);
         return node;
      } else {
         ListNode head = new ListNode(obj, p.getNext());
         p.setNext(head);
         return head;
      }
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      if (p == null)
         return p;
      
      for (int i = 0; i < n; i++) {
         print(p);
         p = remove(p, count);
      }
      
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      p = p.getNext();
      ListNode temp = p;
      if (count == 1) {
         do {
            temp = temp.getNext();
         } while (temp.getNext() != p);
         temp.setNext(p.getNext());
         return temp;
      } else {
         
         for (int i = 1; i < count - 1; i++) {
            temp = temp.getNext();
         }
         temp.setNext(temp.getNext().getNext());
         return temp;
      }
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode pointer = p.getNext();
      if (p != null) {
         do {
            System.out.print(pointer.getValue());
            pointer = pointer.getNext();
            if (pointer != p.getNext())
               System.out.print(" ");
         } while (pointer != p.getNext());
         System.out.println();
      }
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      for (int i = 0; i < pos; i++)
         p = p.getNext();
      p.setValue(obj);
      
   }
}

