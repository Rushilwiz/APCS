// Name: B6-24
// Date: 2/6/20

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> s = new Stack<>();
      TreeNode temp;
   
      for (String token : str.split(" ")) {
         if (!isOperator(token))
            s.push(new TreeNode(token, null, null));
         else {
            temp = s.pop();
            s.push(new TreeNode(token, s.pop(), temp));
         }
      }
      
      root = s.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if (t == null)
         return 0.0;
         
      String token = (String) t.getValue();
         
      if (isOperator(token)) {
         return computeTerm(token, evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      } else
         return Double.valueOf(token);
   }
   
   private double computeTerm(String s, double a, double b)
   {
      switch(s) {
         case "+":
            return a+b;
         case "-":
            return a-b;
         case "*":
            return a*b;
         case "/":
            return a/b;
         default:
            return -1.0;
      }
   }
   
   private boolean isOperator(String s)
   {
      if ("+-/*".contains(s))
         return true;
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      if (t == null)
         return "";
      return inorderTraverse(t.getLeft()) + t.getValue() + " " + inorderTraverse(t.getRight());
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      if (root == null)
         return "";
      return  root.getValue() + " " + preorderTraverse(root.getLeft()) + preorderTraverse(root.getRight());
   }
   
  /* extension */
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }
   
   private String inorderTraverseWithParentheses(TreeNode t)
   {
      if (t == null)
         return "";
         
      String toReturn = "";   
      if (t.getLeft() != null && isOperator((String) t.getLeft().getValue()))
         toReturn += " (" + inorderTraverseWithParentheses(t.getLeft()) + " )";
      else
         toReturn += inorderTraverseWithParentheses(t.getLeft());
      
      toReturn += " " + t.getValue();
      
      if (t.getRight() != null && isOperator((String) t.getRight().getValue()))
         toReturn += " (" + inorderTraverseWithParentheses(t.getRight()) + " )";
      else
         toReturn += inorderTraverseWithParentheses(t.getRight());
         
      return toReturn;
   }
}