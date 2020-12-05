// Name: B6-24
// Date: 9/7/19
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
       //String str = "Atwhay!?";
       //System.out.print(str + "\t\t" + pigReverse(str));
       //str = "{(Ellohay!)}";
       //System.out.print("\n" + str + "\t\t" + pigReverse(str));
       //str = "\"OnaldmcDay???\"";
       //System.out.println("\n" + str + "  " + pigReverse(str));
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      String beginPunct, endPunct;
      int index = 0;
      for(int i = 0; i < s.length( ); i++){
         if(Character.isLetter(s.charAt(i))) {
            break;
         } else if(punct.contains("" + s.charAt(i))) {
            index++;
         } else {
            break;
         }
      }
      
      beginPunct = s.substring(0, index);
      s = s.substring(index);
           
      //remove and store the ending punctuation 
      index = s.length();
      
      for(int i = s.length() - 1; i < s.length(); i--){
         if(Character.isLetter(s.charAt(i))) {
            break;
         } else if(punct.contains("" + s.charAt(i))) {
            index--;
         } else {
            break;
         }
      }
      
      endPunct = s.substring(index);
      s = s.substring(0,index);
      
      //START HERE with the basic case:
      //find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu is a consonant
      String newWord = "";
      
      for (int i = 0; i < s.length(); i++) {
         String currentChar = "" + s.charAt(i);
         if (vowels.contains(currentChar) || currentChar.toLowerCase().equals("y")) {
            //checks if first character is vowel
            if (i == 0) {
               if (!currentChar.toLowerCase().equals("y")) {
                  newWord = s + "way";
                  break;
               }   
            } else if (vowels.contains(currentChar) || currentChar.toLowerCase().equals("y")) {
               if (currentChar.toLowerCase().equals("u")) {
                  if (Character.toLowerCase(s.charAt(i-1)) == 'q') {
                     if (Character.isUpperCase(s.charAt(0))) {
                        newWord = s.substring(i+1) + Character.toLowerCase((s.substring(0, i+1)).charAt(0)) + s.substring(1, i+1) + "ay";
                        newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
                        break;
                     } else {
                        newWord = s.substring(i+1) + s.substring(0, i+1) + "ay";
                        break;
                     }
                  } else {
                     if (Character.isUpperCase(s.charAt(0))) {
                
                        newWord =  s.substring(i) + Character.toLowerCase((s.substring(0, i)).charAt(0)) + s.substring(1, i) + "ay";
                        newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
                        break;
                     } else {
                        newWord = s.substring(i) + s.substring(0, i) + "ay";
                        break;
                     }
                  }
              
               } else {
                  if (Character.isUpperCase(s.charAt(0))) {
             
                     newWord =  s.substring(i) + Character.toLowerCase((s.substring(0, i)).charAt(0)) + s.substring(1, i) + "ay";
                     newWord = Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
                     break;
                  } else {
                     newWord = s.substring(i) + s.substring(0, i) + "ay";
                     break;
                  }
               }
            }
         }
      }
      
      if (newWord.equals(""))
         newWord = "**** NO VOWEL ****";
      
      newWord = beginPunct + newWord + endPunct;
      
      return newWord;
      
      //if no vowel has been found
      
      
      //is the first letter capitalized?
      
      
      //return the piglatinized word 
      
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      String newFile = "";
      while(infile.hasNextLine()) {
         String[] words = infile.nextLine().split(" ");
         for (int i = 0; i < words.length; i++)
            words[i] = (pig(words[i]));
         
         newFile += String.join(" ", words) + "\n";
      }            
      
      outfile.print(newFile.substring(0, newFile.length() - 2));
      
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      //s = pig(s);
   
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      String beginPunct, endPunct;
      int index = 0;
      for(int i = 0; i < s.length( ); i++){
         if(Character.isLetter(s.charAt(i))) {
            break;
         } else if(punct.contains("" + s.charAt(i))) {
            index++;
         } else {
            break;
         }
      }
      
      beginPunct = s.substring(0, index);
      s = s.substring(index);
           
      //remove and store the ending punctuation 
      index = s.length();
      
      for(int i = s.length() - 1; i < s.length(); i--){
         if(Character.isLetter(s.charAt(i))) {
            break;
         } else if(punct.contains("" + s.charAt(i))) {
            index--;
         } else {
            break;
         }
      }
      
      endPunct = s.substring(index);
      s = s.substring(0,index);
      
      //store capitalization order
      boolean[] caps = new boolean[s.length()];
      for (int i = 0; i < s.length(); i++) {
         if (Character.isUpperCase(s.charAt(i)))
            caps[i] = true;
         else
            caps[i] = false;
      }
      
      //reverse String
      String reversed = "";
      for (int i = s.length() - 1; i >= 0; i--) {
           reversed += s.charAt(i);
      }
      
      //fix capitalization
      
      char[] reversedArray = reversed.toCharArray();
      
      for (int i = 0; i < reversedArray.length; i++) {
      
          if (caps[i])
             reversedArray[i] = Character.toUpperCase(reversedArray[i]);
          else
             reversedArray[i] = Character.toLowerCase(reversedArray[i]);
           
      }
      reversed = beginPunct + new String(reversedArray) + endPunct;
            
      //return reversed string
      return reversed;
   }
   
   
}