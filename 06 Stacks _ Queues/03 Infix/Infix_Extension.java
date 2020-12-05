// Name: B6-24
// Date: 1/7/20

import java.util.*;

public class Infix_Extension
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 * (4 + 5]");
      infixExp.add("4 2 : 3 +");

      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get this to work first
         int result = checkParen(infix);
         if (result == 2)
            pf = "ERROR: String contains an invalid character";
         else if (result == 1)
            pf = "ERROR: String does not have matching parentheses";
         
         System.out.println(infix + "\t\t\t" + pf);
         // System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      Stack<String> s = new Stack<>();
      String postfix = "";
      
      for (String str : infixParts) {
         switch(str) {
            case "(":
               s.push(str);
               break;
            case ")":
               while (!s.peek().equals("("))
                  postfix += s.pop() + " ";
               s.pop();
               break;
            case "+": case "-": case "/": case "*":
               while(!s.isEmpty() && isLowerOrEqual(str.charAt(0), s.peek().charAt(0)))
                  postfix += s.pop() + " ";
               s.push(str);
               break;
             
            default: 
               postfix += str + " ";
               break;
         }
      }
      
      while (!s.isEmpty())
         postfix += s.pop() + " ";
      
      return postfix.trim();
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLowerOrEqual(char c1, char c2)
   {
      String str1 = Character.toString(c1), str2 = Character.toString(c2);
      if (("*/".contains(str2) && "+-".contains(str1)) || ("+-".contains(str2) && "+-".contains(str1)) || ("*/".contains(str2) && "*/".contains(str1)))
         return true;
      else
         return false;
   }
   
   public static int checkParen(String exp) {
      String LEFT  = "([{<";
      String RIGHT = ")]}>";
      String OPS = ".1234567890+-/*([{<)]}>%^! ";
   
      for (char c : exp.toCharArray())
         if (!OPS.contains(String.valueOf(c)))
            return 2;
   
      exp = exp.replace(" ", "");

   
      Stack<Character> parens = new Stack<>();
      for (char c : exp.toCharArray()) {
         if (LEFT.contains(String.valueOf(c)))
            parens.push(c);
         else if(RIGHT.contains(String.valueOf(c)))
            if (parens.isEmpty() || RIGHT.indexOf(c) != LEFT.indexOf(parens.pop()))
               return 1;
      }
   
      if (parens.isEmpty())
         return 0;
      else
         return 1;
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 - 4 + 5			3 4 - 5 +			4
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 3 * ( 4 * 5 - 6 + 2 )			3 4 5 * 6 - 2 + *			48
  
***********************************************/