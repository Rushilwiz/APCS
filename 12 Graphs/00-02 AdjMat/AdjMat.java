// Name: B6-24   
// Date: 4/23/20
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix//,Warshall//,Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   public void addEdge(int source, int target) {
   
   }
   
   public void removeEdge(int source, int target) {
      
   }
   
   public boolean isEdge(int from, int to) {
      return false;
   }
   
   public String toString() {
      String str = "";
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            str += grid[i][j];
         } 
         str += "\n";
      }
      return str;
   }
      
   public int edgeCount() {
      return -1;
   }
   
   public List<Integer> getNeighbors(int source) {
      return null;
   }
}
