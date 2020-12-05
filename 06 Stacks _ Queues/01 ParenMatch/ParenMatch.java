// Name: B6-24
// Date: 1/5/2020

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
//       parenExp.add("5+7");
//       parenExp.add("(5+7)");
//       parenExp.add(")5+7(");
//       parenExp.add("((5+7)*3)");
//       parenExp.add("<{5+7}*3>");
//       parenExp.add("[(5+7)*]3");
//       parenExp.add("(5+7)*3");
//       parenExp.add("5+(7*3)");
//       parenExp.add("((5+7)*3");
//       parenExp.add("[(5+7]*3)");
//       parenExp.add("[(5+7)*3])");
//       parenExp.add("([(5+7)*3]");
         parenExp.add("()[]{}<>");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   
   public static boolean checkParen(String exp) {
      Stack<Character> parens = new Stack<>();
      for (char c : exp.toCharArray()) {
         if (LEFT.contains(String.valueOf(c)))
            parens.push(c);
         else if(RIGHT.contains(String.valueOf(c)))
            if (parens.isEmpty() || RIGHT.indexOf(c) != LEFT.indexOf(parens.pop()))
               return false;
      }
   
      if (parens.isEmpty())
         return true;
      else
         return false;
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
