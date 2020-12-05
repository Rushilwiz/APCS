// Name:  B6-24
// Date:  3/11/2020

import java.util.*;
public class HailstoneMap_Driver
{
   public static void main(String[] args)
   {
      HailstoneMap hs = new HailstoneMap();
      long startTime, time;
      
      HailstoneMap hm1 = new HailstoneMap(),
                   hm2 = new HailstoneMap();
      int startNum = 5;
      
      System.out.println("Generate " + startNum + " first time on object hm1");
      startTime = System.nanoTime();
      hm1.hailstoneMaps(startNum);
      time = System.nanoTime() - startTime;
      System.out.println(HailstoneMap.steps.get(startNum) +" steps.");
      System.out.println(HailstoneMap.sequence.get(startNum));
      System.out.println("Nanoseconds:  "+ time);
      System.out.println();
      
      System.out.println("Generate " + startNum + " second time on object hm1");
      startTime = System.nanoTime();
      hm1.hailstoneMaps(startNum);
      time = System.nanoTime() - startTime;
      System.out.println(HailstoneMap.steps.get(startNum) +" steps.");
      System.out.println(HailstoneMap.sequence.get(startNum));
      System.out.println("Nanoseconds:  "+ time);
      System.out.println();
      
      System.out.println("Generate " + startNum + " third time on a different object hm2");
      startTime = System.nanoTime();
      hm2.hailstoneMaps(startNum);
      time = System.nanoTime() - startTime;
      System.out.println(HailstoneMap.steps.get(startNum) +" steps.");
      System.out.println(HailstoneMap.sequence.get(startNum));
      System.out.println("Nanoseconds:  "+ time);
      System.out.println();
      
   
      //now test with other numbers  
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter Hailstone starting number:  ");
      startNum = keyboard.nextInt();
      do{
         startTime = System.nanoTime();
         hs.hailstoneMaps(startNum);
         time = System.nanoTime() - startTime;
         System.out.println(HailstoneMap.steps.get(startNum) +" steps.");
         System.out.println(HailstoneMap.sequence.get(startNum));
         System.out.println("Nanoseconds:  "+ time);
         System.out.println();
        
         System.out.print("Enter Hailstone starting number:  ");
         startNum = keyboard.nextInt();
      } while( startNum != -1);
      System.out.println("Goodbye.");
   }
} 

class HailstoneMap
{
   public static HashMap<Integer, Integer> steps = new HashMap<Integer, Integer>();
   public static HashMap<Integer, String> sequence = new HashMap<Integer, String>();

   //setup the static fields in case they are null
   public HailstoneMap()
   {
      steps = new HashMap<>();
      sequence = new HashMap();
   }
   
   public int hailstoneMaps(int k)
   {
      int startNum = k;
      int stepCount = 0;
      String seq = "";
      
      if (sequence.containsKey(k)) {
            stepCount += steps.get(k);
            seq += sequence.get(k);
            k = 1;
      } else {
      
      seq = k + ", ";
      
      while (k != 1) {
         if (sequence.containsKey(k)) {
            stepCount += steps.get(k);
            seq += sequence.get(k);
            k = 1;
         } else {
            
            if (k % 2 == 0) {
               k /= 2;
            } else {
               k = 3*k + 1;
            }
            
            stepCount++;
            seq += k + ", ";
         
         }
      }
      }
      steps.put(startNum, stepCount);
      sequence.put(startNum, seq.substring(0, seq.length() - 2));
      
      return stepCount;
   
   }
   
   public static HashMap<Integer, Integer> getSteps() //implement for gradeIt
   {
      return steps;
   }
   
   public static HashMap<Integer, String> getSequece() //implement for gradeIt
   {
      return sequence;
   }
   
}

/*********************************

  - ----jGRASP exec: java HailstoneMap_Driver_teacher
 Generate 5 first time on object hm1
 5 steps.
 5, 16, 8, 4, 2, 1
 Nanoseconds:  7686900
 
 Generate 5 second time on object hm1
 5 steps.
 5, 16, 8, 4, 2, 1
 Nanoseconds:  5500
 
 Generate 5 third time on a different object hm2
 5 steps.
 5, 16, 8, 4, 2, 1
 Nanoseconds:  3000
 
 Enter Hailstone starting number:  37
 21 steps.
 37, 112, 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  147600
 
 Enter Hailstone starting number:  37
 21 steps.
 37, 112, 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  9300
 
 Enter Hailstone starting number:  37
 21 steps.
 37, 112, 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  6400
 
 Enter Hailstone starting number:  37
 21 steps.
 37, 112, 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  7200
 
 Enter Hailstone starting number:  112
 20 steps.
 112, 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  6700
 
 Enter Hailstone starting number:  56
 19 steps.
 56, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  6700
 
 Enter Hailstone starting number:  14
 17 steps.
 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
 Nanoseconds:  6700
 
 Enter Hailstone starting number:  4
 2 steps.
 4, 2, 1
 Nanoseconds:  6200
 
 Enter Hailstone starting number:  -1
 Goodbye.
 
  ----jGRASP: operation complete.
  **********************************************/