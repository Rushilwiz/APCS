// Name: B6-24
// Date: 09/26/19
  
import java.util.*;
public class Permutations
{
   public static int count = 0;
    
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      leftRight("", n);          
      oddDigits("", n);
      superprime(n);
      if(count==0)
         //Extension #1:
         System.out.println("there are no " + n + "-digit superprimes");
      else
         System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   public static void leftRight(String s, int n)
   {
      if (s.length() < n) {
         leftRight(s + "L", n);
         leftRight(s + "R", n);   
      } else if (s.length() == n)
         System.out.println(s);
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   public static void oddDigits(String s, int n)
   {
      if (s.length() < n){
         for (int i = 1; i < 10; i += 2)
            oddDigits(s + i, n);
      } else if (s.length() == n) {
         System.out.println(s);
      }
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }

    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   private static void recur(int k, int n)
   {
      if (isPrime(k)) {
         if (n == 1) {
            System.out.println(k);
            //Extension #2:
            count++;
         } else {
            for (int i = 1; i < 10; i+=2)
               recur(k*10 + i, n - 1);
         }
      }
   }

    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n) {
      
      //Extension #3:
      
      if (n < 2) 
         return false;
      
      if (n < 4)
         return true;
         
      if (n % 2 == 0 || n % 3 == 0)
         return false;
         
      for (int i = 5; i * i <= n; i += 6)               // since we already checked for 6, 8, 9, and 10 with 2 & 3 we can add by 6 and only check 5 and 7
         if (n % i == 0 || n % (i + 2) == 0)
            return false;
            
       return true;
   }
}