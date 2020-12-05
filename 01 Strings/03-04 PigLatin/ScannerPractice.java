import java.util.*;
import java.io.*;

public class ScannerPractice {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("What is ur filename?");
        String filename = sc.next();
        Scanner infile = new Scanner(new File(filename));
        String fileout = "results.txt";
        PrintWriter outfile = new PrintWriter(new FileWriter(fileout));
        while(infile.hasNext()) {
            String s = infile.nextLine();
            outfile.print(s + ", ");
        }
        
        outfile.close();
        infile.close();
     }
    
}