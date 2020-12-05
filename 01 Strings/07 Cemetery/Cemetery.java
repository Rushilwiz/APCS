   // Name: B6-24
// Date: 09/18/19
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;
//here any additional imports that you may need
import java.io.FileNotFoundException;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

public class Cemetery 
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i]);
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge()); 
      //you may create other testing cases here
      //comment them out when you submt your file to gradeit
      
      
      /* Extension:
         
         For the extension, I made two ways to search the data, for I've got
         much too much time on the bus ride home. Both methods only require
         a Person array as arguments.
         
         runSearch() allows the user to search via a text-based interface by
         age, burial date, and name
         
         searchGUI() starts a GUI(not the prettiest thing) made with swing to 
         be able to search by age, burial date, and name
      
      */
      //Run TUI
      runSearch(cemetery);
      
      //Run GUI
      //new searchGUI(cemetery);
      
   }
   
   
   //
   public static class searchGUI extends JFrame {
   
      public searchGUI (Person[] arr) {
         
         JPanel panel = new JPanel(); 
         panel.setLayout(new BorderLayout());
        
         JLabel title = new JLabel("Cemetery Search System");
         title.setHorizontalAlignment(JLabel.CENTER);
         title.setVerticalAlignment(JLabel.CENTER);
         title.setFont(title.getFont().deriveFont(18.0f));
        
         panel.add(title , BorderLayout.NORTH); 
        
        
         GridLayout gridLayout = new GridLayout(3,3);
         JPanel gridPanel = new JPanel(); 
         gridPanel.setLayout(gridLayout);
        
         JLabel searchAgeLabel, searchNameLabel, searchBurialDateLabel;
         searchAgeLabel = new JLabel("Search by age:");
         searchNameLabel = new JLabel("Search by name:");
         searchBurialDateLabel = new JLabel("Search by burial date:");
        
         searchAgeLabel.setFont(searchAgeLabel.getFont().deriveFont(15f));
         searchBurialDateLabel.setFont(searchBurialDateLabel.getFont().deriveFont(15.0f));
         searchNameLabel.setFont(searchNameLabel.getFont().deriveFont(15.0f));
        
         searchAgeLabel.setHorizontalAlignment(JLabel.CENTER);
         searchNameLabel.setHorizontalAlignment(JLabel.CENTER);
         searchBurialDateLabel.setHorizontalAlignment(JLabel.CENTER);
        
         JButton searchAgeButton, searchNameButton, searchBurialDateButton;
         searchAgeButton = new JButton("SEARCH");
         searchNameButton = new JButton("SEARCH");
         searchBurialDateButton = new JButton("SEARCH");
        
         JTextField searchAgeField, searchNameField, searchBurialDateField;
         searchAgeField = new JTextField(8);
         searchNameField = new JTextField(10);
         searchBurialDateField = new JTextField(11);
        
         JPanel[] gridPanels = new JPanel[9];
         for (int i = 0; i < gridPanels.length; i++)
            gridPanels[i] = new JPanel();
            
         gridPanels[0].add(searchAgeLabel);
         gridPanels[1].add(searchNameLabel);
         gridPanels[2].add(searchBurialDateLabel);
         gridPanels[3].add(searchAgeField);
         gridPanels[4].add(searchNameField);
         gridPanels[5].add(searchBurialDateField);
         gridPanels[6].add(searchAgeButton);
         gridPanels[7].add(searchNameButton);
         gridPanels[8].add(searchBurialDateButton);
        
         for (int i = 0; i < gridPanels.length; i++)
            gridPanel.add(gridPanels[i]);
        
         JTextArea results = new JTextArea(7, 45);
         JButton clearButton = new JButton("Clear");
         JPanel bottomPanel = new JPanel();
         bottomPanel.setPreferredSize(new Dimension(500, 150));
         bottomPanel.add(results);
         bottomPanel.add(clearButton);
         panel.add(bottomPanel, BorderLayout.SOUTH);
                
         panel.add(gridPanel, BorderLayout.CENTER);
        
         add(panel); 
        
         searchAgeButton.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                  results.setText(searchAge(arr, searchAgeField.getText()));
                  searchAgeField.setText("");
               }
            });
         
         searchNameButton.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                  
                  results.setText(searchName(arr, searchNameField.getText()));
                  searchNameField.setText("");
               }
            });
      
         searchBurialDateButton.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e) {
                  results.setText(searchBurialDate(arr, searchBurialDateField.getText()));
                  searchBurialDateField.setText("");
               
               }
            });
         
         clearButton.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  results.setText("");
                  searchAgeField.setText("");
                  searchNameField.setText("");
                  searchBurialDateField.setText("");
               }   
            });       
         
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      
         setSize(500, 300); 
      
         setVisible(true);
      }
      
      
      public static String searchName(Person[] arr, String searchTerm) {
      
         searchTerm = searchTerm.toLowerCase().trim();
         
         
         String queryResults = "";
         
         boolean nameFound = false;
         for (int i = 0; i < arr.length; i++) {
            String currentName = arr[i].getName().toLowerCase().trim();
            if (currentName.equals(searchTerm)) {
               queryResults += arr[i].toString() + "\n";
               nameFound = true;
            }   
         }
               
         if (!nameFound)
            queryResults = "NO PEOPLE FOUND";
               
         return queryResults;
      }
      
      public String searchBurialDate(Person[] arr, String searchTerm) {
         searchTerm = searchTerm.toLowerCase().trim();
         String queryResults = "";
         boolean dateFound = false;
         for (int i = 0; i < arr.length; i++) {
            String currentDate = arr[i].getBurialDate().toLowerCase().trim();
            if (currentDate.equals(searchTerm)) {
               queryResults += arr[i].toString() + "\n";
               dateFound = true;
            }   
         }
         
         if (!dateFound)
            queryResults = "NO PEOPLE FOUND";
         return queryResults;
      }
   
      public String searchAge(Person[] arr, String searchTerm) {
      
         String queryResults = "";
      
            Person holder = new Person("", "", 0.0);
            double searchValue = holder.calculateAge(searchTerm);
            boolean ageFound = false;
            for (int i = 0; i < arr.length; i++) {
               double currentAge = arr[i].getAge();
               if (currentAge == searchValue) {
                  queryResults += arr[i].toString() + "\n";
                  ageFound = true;
               }   
            }
         
            if (!ageFound)
               queryResults = "NO PEOPLE FOUND";
               
            return queryResults;
         
      }
   
   }
   
   
   
      /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      Scanner file = null;
      
      try {
         file = new Scanner(f);
         int counter = 0;
         while (file.hasNextLine()) {
            file.nextLine();
            counter++;
         }
         return counter;
         
      } catch(FileNotFoundException e) {
         return 0;
      }
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return an empty array.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {  
      Scanner file = null;
      
      try {
         file = new Scanner(f);
      } catch(FileNotFoundException e) {
         return new Person[0];
      }
      
      Person[] dataArr = new Person[num];
      
      int counter = 0;
      while (file.hasNextLine()) {
         Person person = makeObjects(file.nextLine());
         System.out.println(person.toString());
         dataArr[counter] = person;
         counter++;
      
      }
      
      return dataArr;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String name = entry.substring(0, 25);
      String date = entry.substring(25, 37);
      String age = entry.substring(37, 42).trim();
      Person person = new Person(name, date, 0.0);
      person.setAge(person.calculateAge(age));
      return person;
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      if (arr.length == 0)
         return -1;
      
      int index = 0;
      double lowestValue = 99999999.0;
      
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() < lowestValue) {
            index = i;
            lowestValue = arr[i].getAge();
         }
      }
      
      return index;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      if (arr.length == 0)
         return -1;
      
      int index = 0;
      double highestValue = 0.0;
      
      for (int i = 0; i < arr.length; i++) {
         if (arr[i].getAge() > highestValue) {
            index = i;
            highestValue = arr[i].getAge();
         }
      }
      
      return index;
   }
   
   
   /* Extension:
   
      The methods below allows the user to search the cemetery by name, burial date, or age of death with a text based interface.
      
   
   */
   
   
   public static void runSearch(Person[] arr) {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("Would you like to run a search on the cemetery(y/n)?");
      String s = sc.nextLine().trim();
      if (s.equals("n") || s.equals("no"))
         System.exit(0);
      
      
      while(true) {
         System.out.println("\n");
         System.out.println("Welcome to the search system for your cemetery!\nType in a field you want to search by(name, burialdate, or age) or type exit to exit:");
           
         s = sc.nextLine().trim();
         System.out.println("\n\n\n\n\n\n\n\n");
         if (s.toLowerCase().equals("exit")) {
            System.out.println("Goodbye!");
            System.exit(0);
         } else if (s.toLowerCase().equals("name")) {
            searchName(arr);
         } else if (s.toLowerCase().equals("burialdate") || s.toLowerCase().equals("burial date")) {
            searchBurialDate(arr);
         } else if (s.toLowerCase().equals("age")) {
            searchAge(arr);
         }
      }
   }
   
   public static void searchName(Person[] arr) {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("You chose NAME! Type in a name to search by, case doesn't matter(or type back to return)");
      String searchTerm = sc.nextLine().toLowerCase().trim();
      System.out.println("\n");         
      if (!searchTerm.equals("back")) {
         System.out.println("Here are the results of your query:");
         boolean nameFound = false;
         for (int i = 0; i < arr.length; i++) {
            String currentName = arr[i].getName().toLowerCase().trim();
            if (currentName.equals(searchTerm)) {
               System.out.println(arr[i].toString());
               nameFound = true;
            }   
         }
         
         if (!nameFound)
            System.out.println("NO PEOPLE FOUND");
      }
   }
   
   public static void searchBurialDate(Person[] arr) {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("You chose BURIAL DATE! Type in a date to search by as follows: 03 Apr 1850(or type back to go back)");
      String searchTerm = sc.nextLine().toLowerCase().trim();
      System.out.println("\n");
      if (!searchTerm.equals("back")) {
         System.out.println("Here are the results of your query:");
         boolean dateFound = false;
         for (int i = 0; i < arr.length; i++) {
            String currentDate = arr[i].getBurialDate().toLowerCase().trim();
            if (currentDate.equals(searchTerm)) {
               System.out.println(arr[i].toString());
               dateFound = true;
            }   
         }
         
         if (!dateFound)
            System.out.println("NO PEOPLE FOUND");
      }
   }
   
   public static void searchAge(Person[] arr) {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("You chose AGE! You can search by weeks(14w), days(6d), years(34), or a decimal value(.011). Type the age below: (or type back to go back)");
      String searchTerm = sc.nextLine().toLowerCase().trim();
      
      System.out.println("\n");
      
      if (!searchTerm.equals("back")) {
         System.out.println("Here are the results of your query:");
         Person holder = new Person("", "", 0.0);
         double searchValue = holder.calculateAge(searchTerm);
         boolean ageFound = false;
         for (int i = 0; i < arr.length; i++) {
            double currentAge = arr[i].getAge();
            if (currentAge == searchValue) {
               System.out.println(arr[i].toString());
               ageFound = true;
            }   
         }
         
         if (!ageFound)
            System.out.println("NO PEOPLE FOUND");
      }
   }
} 

class Person
{
   //constant that can be used for formatting purpose
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private double myAge;
   private String myName, myBurialDate;
   
   
      
   /* a three-arg constructor  
    @param name, birthdate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, double age)
   {
      myName = name;
      myBurialDate = burialDate;
      myAge = age;
   
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same datat type as the field  */
   
   double getAge() {
      return myAge;  
   }
   
   String getName() {
      return myName;
   }
   
   String getBurialDate() {
      return myBurialDate;
   }
   
   void setAge(double age) {
      myAge = age;
   }
   
   void setName(String name) {
      myName = name;
   }
   
   void setBurialDate(String date) {
      myBurialDate = date;
   }
   
   public String toString() {
      return myName+myBurialDate+myAge;
   }
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
      double ageValue = 0.0;
      if (!Character.isLetter(a.charAt(a.length() - 1))) {
         ageValue = Double.parseDouble(a);
      } else {
         if (a.charAt(a.length() - 1) == 'd') {
            ageValue = Double.parseDouble(df.format(Double.parseDouble(a.substring(0, a.length() - 1)) / 365));
         } else if (a.charAt(a.length() - 1) == 'w') {
            ageValue = Double.parseDouble(df.format((Double.parseDouble(a.substring(0, a.length() - 1)) * 7) / 365));
         }  
      }
       
      return Double.parseDouble(df.format(ageValue));
   }
}