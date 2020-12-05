// Name: B6-24
// Date: 3/8/2020

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      System.out.println();
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
      
      while (true) {
         try
      {
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }

         System.out.println("ENGLISH TO SPANISH");
         display(eng2spn);
         System.out.println();
         System.out.println("SPANISH TO ENGLISH");
         display(spn2eng);
         
         System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
         System.out.println("Welcome to the Spanish/English APCS Dictionary!\n\n\tType 1 if you'd like to translate a word from English to Spanish\n\tType 2 if you'd like to translate a word from Spanish to English\n\tType 3 if you'd like to update the dictionary (PASSWORD REQUIRED)\n\tType exit to exit");
         Scanner keyboard = new Scanner(System.in);
         System.out.print("\nOption: ");
         String option = keyboard.nextLine().trim();
         
         if (option.equals("exit")) {
            System.out.println("Bye!");
            break;
         }
         switch (option) {
            case "1":
               clear();
               System.out.println("What would you like to translate to Spanish?");
               String english = keyboard.nextLine().trim().toLowerCase();
               if (eng2spn.containsKey(english)) {
                  String translation = eng2spn.get(english).toString();
                  System.out.println();
                  System.out.print(english + " tranlsates to " + translation.substring(1, translation.length() - 1));
               } else {
                  System.out.println("Sorry '" + english + "' was not found");
               }
               
               System.out.println("\nPress enter to continue");
               keyboard.nextLine();
               
               break;
            case "2":
               clear();
               System.out.println("What would you like to translate to English?");
               String spanish = keyboard.nextLine().trim().toLowerCase();
               if (spn2eng.containsKey(spanish)) {
                  String translation = spn2eng.get(spanish).toString();
                  System.out.println();
                  System.out.print(spanish + " tranlsates to " + translation.substring(1, translation.length() - 1));
               } else {
                  System.out.println("Sorry '" + spanish + "' was not found");
               }
               
               System.out.println("\nPress enter to continue");
               keyboard.nextLine();
               break;
            case "3":
               clear();
               System.out.println("What's the administrative password?");
               String pass = keyboard.nextLine().trim();
               if (pass.equals("admin")) {
                  clear();
                  while (true){
                  try
      {
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }

         System.out.println("ENGLISH TO SPANISH");
         display(eng2spn);
         System.out.println();
         System.out.println("SPANISH TO ENGLISH");
         display(spn2eng);
         
         System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
         System.out.println("Welcome Admin!\n\tType add to add an entry\n\tType remove to remove an entry\n\tType modify to change an entry\n\tType exit to exit");
         keyboard = new Scanner(System.in);
         System.out.print("\nOption: ");
         option = keyboard.nextLine().trim();
         
         if (option.equals("exit")) {
            System.out.println("Bye!");
            break;
         }
                     switch(option) {
                        case "add":
                           clear();
                           System.out.println("What are the english word/words you would like to add (Seperate by commas)");
                           String[] engWords = keyboard.nextLine().trim().split(",");
                           clear();
                           System.out.println("What are the spanish word/words you would like to add (Seperate by commas)");
                           String[] spnWords = keyboard.nextLine().trim().split(",");
                           for (String eng : engWords) {
                              for (String spn : spnWords) {
                                 add(eng2spn, eng, spn);
                              }
                           }
                           
                           for (String spn : spnWords) {
                              for (String eng : engWords) {
                                 add(spn2eng, spn, eng);
                              }
                           }
                           clear();
                           System.out.println ("Done!");
                           keyboard.nextLine();
                           break;
                        case "remove":
                           clear();
                           System.out.println("Type english for an english word and spanish for a spanish word");
                           option = keyboard.nextLine().trim();
                           if (option.equals("english")) {
                              clear();
                              System.out.println("What is your english word to remove?");
                              String word = keyboard.nextLine().trim();
                              if (eng2spn.containsKey(word)) {
                                 eng2spn.remove(word);
                              } else {
                                 break;
                              }
                           } else {
                              clear();
                              System.out.println("What is your spanish word to remove?");
                              String word = keyboard.nextLine().trim();
                              if (eng2spn.containsKey(word)) {
                                 eng2spn.remove(word);
                              } else {
                                 break;
                              }
                           }
                           
                           clear();
                           System.out.println("Done!");
                           keyboard.nextLine();
                           break;
                        case "modify":
                           clear();
                           System.out.println("Type english for an english word and spanish for a spanish word");
                           option = keyboard.nextLine().trim();
                           if (option.equals("english")) {
                              clear();
                              System.out.println("What is your english word to change?");
                              String word = keyboard.nextLine().trim();
                              System.out.println("\nWhat is the new word?");
                              String newWord = keyboard.nextLine().trim();
                              if (eng2spn.containsKey(word)) {
                                 Set<String> temp = eng2spn.remove(word);
                                 eng2spn.put(newWord, temp);
                              } else {
                                 break;
                              }
                           } else {
                              clear();
                              System.out.println("What is your spanish word to change?");
                              String word = keyboard.nextLine().trim();
                              System.out.println("\nWhat is the new word?");
                              String newWord = keyboard.nextLine().trim();
                              if (spn2eng.containsKey(word)) {
                                 Set<String> temp = spn2eng.remove(word);
                                 spn2eng.put(newWord, temp);
                              } else {
                              
                                 break;
                              }
                           }
                           
                           clear();
                           System.out.println("Done!");
                           keyboard.nextLine();
                           break;
                           
                         default:
                         break;
                     }
               
                  }
               } else {
                  System.out.println("Incorrect Password.");
               }
               
               break;
            default:
               System.out.println("Sorry, that option was not recognized");
         }
         
         clear();
      }
   }
   
   public static void clear () {
      for (int i = 0; i < 30; i++)
            System.out.println();
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dictionary = new TreeMap<String, Set<String>>();
        
      while(infile.hasNext()) {
         String word = infile.next();
         String translation = infile.next();
         add(dictionary, word, translation);
      }
      
      return dictionary;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if (dictionary.containsKey(word)) {
         Set<String> temp = dictionary.get(word);
         temp.add(translation);
         dictionary.put(word, temp);
      } else {
         Set<String> temp = new TreeSet<>();
         temp.add(translation);
         dictionary.put(word, temp);
      }   
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      for (String word : m.keySet()) {
         System.out.println("\t" + word + m.get(word));
      }
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> reverse = new TreeMap<>();
      for (String translation : dictionary.keySet()) {
         Set<String> words = dictionary.get(translation);
         for (String word : words) {
            add(reverse, word, translation);
         }
      }
      
      return reverse;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/