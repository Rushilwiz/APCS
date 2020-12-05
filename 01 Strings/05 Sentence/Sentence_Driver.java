// Name: B6-24
// Date: 09/13/19
  
import java.util.Scanner;

public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
         s = new Sentence( "A Santa lived as a devil at NASA." );
         System.out.println( s.getSentence() );
         System.out.println( s.getNumWords() );
         System.out.println( s.isPalindrome() );
         System.out.println();
        
      
         s = new Sentence( "Flo, gin is a sin! I golf." );
         System.out.println( s.getSentence() );
         System.out.println( s.getNumWords() );
         System.out.println( s.isPalindrome() );
         System.out.println();
         
      
         s = new Sentence( "Eva, can I stab bats in a cave?" );
         System.out.println( s.getSentence() );
         System.out.println( s.getNumWords() );
         System.out.println( s.isPalindrome() );
         System.out.println();
      
         s = new Sentence( "Madam, I'm Adam." );
         System.out.println( s.getSentence() );
         System.out.println( s.getNumWords() );
         System.out.println( s.isPalindrome() );
         System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
         s = new Sentence("REd ru????M, SiR, i..................s !m!u!r!d!e!r!");
         System.out.println( s.getSentence() );
         System.out.println( s.getNumWords() );
         System.out.println( s.isPalindrome() );
         System.out.println();
   
         
      // Scanner sc = new Scanner(System.in);
//       while(true) {
//          System.out.print("\nWhat sentence? ");
//          s = new Sentence(sc.nextLine());
//          System.out.println( s.getSentence() );
//          System.out.println( s.getNumWords() );
//          System.out.println( s.isPalindrome() );
//          System.out.println();
//    
// 
//       }
   
   }
}

class Sentence
{
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str )
   { 
      mySentence = str;
      String[] words = str.split(" ");
      myNumWords = words.length;
   }
   
   public int getNumWords()
   {  
      return myNumWords;  
   }
   
   public String getSentence()
   {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
      String s = removeBlanks(lowerCase(removePunctuation(mySentence)));
      return isPalindrome(s, 0, s.length() - 1);
   }
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      if (start >= end) {
         return true;
      } else if (s.charAt(start) == s.charAt(end)) {
         start++; end--;
         return isPalindrome(s, start, end);
         
      } else {
         return false;
      }
   }
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {  
      return s.replace(" ", "");
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s )
   {  
      return s.toLowerCase();
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String removePunctuation( String s )
   { 
      String punct = ".,'?!:;\"(){}[]<>-"; 
      String noPunct = "";
      
      String[] words = s.split(" ");
      for (int i = 0; i < words.length; i++) {
         char[] letters = words[i].toCharArray();
         String newWord = "";
         for (int j = 0; j < letters.length; j++) {
            if (!punct.contains("" + letters[j])){ 
               newWord += letters[j];
            }
         }
         noPunct += newWord;
         if (i != (words.length - 1))
             noPunct += " ";
      }
      
      return noPunct;
   }
   
   
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/

