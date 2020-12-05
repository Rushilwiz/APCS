// Name: B6-24
// Date: 1/6/2020

import java.util.*;

public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Integer> s = new Stack<>();
      
      for (String str : postfixParts) {
         if (!isOperator(str))
            s.push(Integer.valueOf(str));
         else {
            if (str.equals("!")) {
              int factorial = 1; int f = s.pop();
              for (int i = 2; i <= f; i++)
                 factorial *= i;
                s.push(factorial);    
            } else
               s.push(eval(s.pop(), s.pop(), str));
         }
      }
      
      return s.pop();
   }
   
   public static int eval(int a, int b, String ch)
   {
      switch(ch) {
         case "+":
            return b + a;
         case "-":
            return b - a;
         case "/":
            return b / a;
         case "*":
            return b * a;
         case "^":
            return (int) Math.pow(b, a);
         case "%":
            return b % a;
      }      
      
      return -1;
   }
   
   public static boolean isOperator(String op)
   {
      if ("+-/*^%!".contains(op))
         return true;
      else
         return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/