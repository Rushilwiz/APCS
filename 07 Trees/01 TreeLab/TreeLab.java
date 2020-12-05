// Name: B6-24
// Date: 1/29/20

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";

      toReturn += postorderTraverse(t.getLeft());   //recurse left
      toReturn += postorderTraverse(t.getRight());  //recurse right
      toReturn += t.getValue() + " ";              //preorder visit
      
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      return inorderTraverse(t).split(" ").length;
   }
   
   public static int countLeaves(TreeNode t)
   {
      int toReturn = 0;
      if (t == null)
         return 0;
         
      if (t.getLeft() == null && t.getRight() == null)
         return 1;
      
      toReturn += countLeaves(t.getLeft()) + countLeaves(t.getRight());
      return toReturn;
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if (t == null) return 0;
      
      int toReturn = 0;
      if (height(t) >= 2) toReturn++;
      if (height(t.getLeft()) >= 2) toReturn += countGrandParents(t.getLeft());
      if (height(t.getRight()) >= 2) toReturn += countGrandParents(t.getRight());
      
      return toReturn;
   }
   
   public static int countOnlys(TreeNode t)
   {
      if (t == null)
         return  0;
         
      
      if ((t.getRight() != null && t.getLeft() == null) || t.getRight() == null && t.getLeft() != null)
         return 1 + countOnlys(t.getRight()) + countOnlys(t.getLeft());    
        
      
      return countOnlys(t.getRight()) + countOnlys(t.getLeft());
   }
   
  /* returns the max of the heights to the left and the heights to the right  
     returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   {
      if (t == null)
         return -1;
         
      return Math.max(height(t.getLeft()), height(t.getRight())) + 1;
   }
   
 /* return the max of the sum of the heights to the left and the heights to the right  
 */
   public static int longestPath(TreeNode t)
   {
      if (t == null)
         return 0;
       return Math.max(height(t.getRight()) + height(t.getLeft()) + 2, Math.max(longestPath(t.getRight()), longestPath(t.getLeft())));
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if (t == null) return t;
      
      Comparable min = (Comparable) t.getValue();
      Comparable right = (Comparable) min(t.getRight());
      Comparable left = (Comparable) min(t.getLeft());
      
      if (right != null && min.compareTo(right) > 0)
         min = right;
      
      if (left != null && min.compareTo(left) > 0)
         min = left;
      
      return min;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if (t == null) return t;
      
      Comparable max = (Comparable) t.getValue();
      Comparable right = (Comparable) max(t.getRight());
      Comparable left = (Comparable) max(t.getLeft());
      
      if (right != null && max.compareTo(right) < 0)
         max = right;
      
      if (left != null && max.compareTo(left) < 0)
         max = left;
      
      return max;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      if (t == null)
         return "";
      String out = "";
      Queue<TreeNode> q = new LinkedList<>();
      q.add(t);
      while (!q.isEmpty()) {
         TreeNode temp = q.remove();
         if (temp.getLeft() != null)
            q.add(temp.getLeft());
         if (temp.getRight() != null)
            q.add(temp.getRight());
         out += temp.getValue().toString();
      }
      
      return out;
   }
}

/***************************** **********************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
