// Name: B6-24
// Date: 09/26/19
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
       long start, end, fib; //why long?
       int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
       System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
       for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
       { 
           start = System.nanoTime();
           fib = fibIterate(n);
           end = System.nanoTime();
           System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
           start = System.nanoTime();   	
           fib = fibRecur(n);      
           end = System.nanoTime();
           System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
       }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      long n1, n2 = 0, n3 = 1;
      for (int i = 1; i < n; i++) {
         n1 = n2;
         n2 = n3;
         n3 = n1 + n2;
      }
      
      return n3;
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
      if (n == 0) return 0;
      if (n == 1) return 1;
      
      return fibRecur(n - 1) + fibRecur(n - 2);
   }
}