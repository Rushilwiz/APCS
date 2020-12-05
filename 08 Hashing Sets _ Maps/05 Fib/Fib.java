// Name: B6-24
// Date: 3/11/2020

import java.util.*;

interface Fibber
{
   public abstract int fib(int n);
}

public class Fib
{
   public static final int FIBsubN = 40;
   public static void main(String[] args)
   {
      System.out.println("******************************");
      Fib1 f1= new Fib1();
      System.out.println("Fib1, Recursive, no storing");
      calculate(f1, FIBsubN);
      System.out.println("Fib1 again with the same Fib1 object");
      calculate(f1, FIBsubN);
      System.out.println("Fib1 with a new Fib1 object");
      calculate(new Fib1(), FIBsubN);
      
      System.out.println("******************************");
      Fib2 f2 = new Fib2(FIBsubN + 1);
      System.out.println("Fib2, Iterative, stored in an array");
      calculate(f2, FIBsubN);
      System.out.println("Fib2 again with the same Fib2 object");
      calculate(f2, FIBsubN);      
      System.out.println("Fib2 with a new Fib2 object");
      calculate(new Fib2(FIBsubN + 1), FIBsubN);
      
      System.out.println("******************************");
      Fib3 f3 = new Fib3();
      System.out.println("Fib3, Recursive, stored in a static arrayList");
      calculate(f3, FIBsubN);
      System.out.println("Fib3 again with the same Fib3 object");
      calculate(f3, FIBsubN);
      System.out.println("Fib3 with a new Fib3 object");
      calculate(new Fib3(), FIBsubN);
      
      System.out.println("******************************");
      Fib4 f4 = new Fib4();
      System.out.println("Fib4, Recursive, stored in a static hashMap");
      calculate(f4, FIBsubN);
      System.out.println("Fib4 again with the same Fib4 object");
      calculate(f4, FIBsubN);
      System.out.println("Fib4 with a new Fib4 object");
      calculate(new Fib4(), FIBsubN);	
   }
      
   public static void calculate(Fibber fibber, int n)
   {
      long start = System.nanoTime();
      int f = fibber.fib(n);
      long finish = System.nanoTime();
      long time = finish - start;
      
      System.out.print("fib(" + n + ") = " + f);
      System.out.println(" (" + time + "nanoseconds)");		
      System.out.println();		
   }
}
    
class Fib1 implements Fibber
{
   public Fib1()    
   {
   }
   
   public int fib(int n)
   {
      if(n == 1 || n == 2)
         return 1;
      else
         return fib(n - 1) + fib(n - 2);
   }
}
   
class Fib2 implements Fibber
{
   private int[] array;
   
   public Fib2(int n)
   {
      array = new int[n];
      array[1] = 1;
      array[2] = 1;
   }
   
   public int fib(int n)
   {
      if (n <= 2)
         return 1;
         
       for (int i = 3; i <= n; i++) {
         array[i] = array[i-1] + array[i-2];
       }
       
       return array[n];
   }
   
   public int[] getArray()   //nice to have
   {
    return array;
   }
}
   
class Fib3 implements Fibber
{
   private static ArrayList<Integer> myFibList;
   
   public Fib3()
   {
      myFibList = new ArrayList<>();
      myFibList.add(1);
      myFibList.add(1);
   }
   
   public int fib(int n)
   {
      if(myFibList.size() >= n){
         return myFibList.get(n-1);
      }
      
      myFibList.add(fib(n-1) + fib(n-2));
      return myFibList.get(n-1);
   }
   
   public static ArrayList<Integer> getArrayList()   //nice to have
   {
      return myFibList;
   }
}

class Fib4 implements Fibber
{
   private static Map<Integer, Integer> myFibMap;
   
   public Fib4()
   {
      myFibMap = new HashMap<>();
      myFibMap.put(1,1);
      myFibMap.put(2,1);
   }
   
   public int fib(int n)
   {
      if(myFibMap.containsKey(n)){
         return myFibMap.get(n);
      }
      
      myFibMap.put(n, fib(n-1) + fib(n-2));
      return myFibMap.get(n);
   }
   
   public static Map<Integer, Integer> getMap()  //nice to have
   {
      return myFibMap;
   }
}
	
/**********************************************

      ----jGRASP exec: java Fib_teacher
 ******************************
 Fib1, Recursive, no storing
 fib(40) = 102334155 (344219300 nanoseconds)
 
 Fib1 again with the same Fib1 object
 fib(40) = 102334155 (343141500 nanoseconds)
 
 Fib1 with a new Fib1 object
 fib(40) = 102334155 (350600600 nanoseconds)
 
 ******************************
 Fib2, Iterative, stored in an array
 fib(40) = 102334155 (3200 nanoseconds)
 
 Fib2 again with the same Fib2 object
 fib(40) = 102334155 (1600 nanoseconds)
 
 Fib2 with a new Fib2 object
 fib(40) = 102334155 (1600 nanoseconds)
 
 ******************************
 Fib3, Recursive, stored in a static arrayList
 fib(40) = 102334155 (59600 nanoseconds)
 
 Fib3 again with the same Fib3 object
 fib(40) = 102334155 (1200 nanoseconds)
 
 Fib3 with a new Fib3 object
 fib(40) = 102334155 (1100 nanoseconds)
 
 ******************************
 Fib4, Recursive, stored in a static hashMap
 fib(40) = 102334155 (97000 nanoseconds)
 
 Fib4 again with the same Fib4 object
 fib(40) = 102334155 (1900 nanoseconds)
 
 Fib4 with a new Fib4 object
 fib(40) = 102334155 (1900 nanoseconds)
 
 ***********************************************/