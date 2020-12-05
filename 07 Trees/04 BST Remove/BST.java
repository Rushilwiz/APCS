// Name: B6-24
// Date: 2/12/20

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /******
   @param s -- one string to be inserted
   ********/
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) 
   {      
      if (t == null)
         return new TreeNode(s, null, null);
      
      TreeNode p, q;
      p = q = t;
      String str;
      
      while(p != null) {
         str = (String) p.getValue();
         if (str.compareTo(s) >= 0)
            p = p.getLeft();
          else
            p = p.getRight();

           if (p != null)
            q = p;
      }
      
      str = (String) q.getValue();
      
      if (str.compareTo(s) >= 0)
         q.setLeft(new TreeNode(s, null, null));
      else
         q.setRight(new TreeNode(s, null, null));
      
      return t;
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   public boolean contains(TreeNode t, String x)
   {
      if (t == null)
         return false;
      
      TreeNode temp = root;
      String str = (String) root.getValue();
      
      while (!str.equals(x)) {
         
         if (str.compareTo(x) >= 0)
            temp = temp.getLeft();
          else
            temp = temp.getRight();
          
          if (temp == null)
            return false;
         
         str = (String) temp.getValue();
      }
      
      return true;
   }
   
   public String min()
   {
      return min(root);  
   }
   private String min(TreeNode t)  //use iteration
   {
      if (t == null)
         return "";
      TreeNode temp = t;
      while (temp.getLeft() != null) {
         temp = temp.getLeft();
      }
      return String.valueOf(temp.getValue());
   }
   
   public String max()
   {
      return max(root);  
   }
   private String max(TreeNode t)  //use recursion
   {
      if (t == null) return "";
      if (t.getRight() == null)
         return String.valueOf(t.getValue());
      else
         return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal
   {
      if (t == null)
         return "";
      return toString(t.getLeft()) + " " + (String) t.getValue() + " "  + toString(t.getRight());
   }
    
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      if (current == null)
         return null;
   
      String str = (String) current.getValue();
      if (str.equals(target)) {
         if (current.getLeft() == null && current.getRight() == null)
            return null;
         else if (current.getLeft() == null)
            return current.getRight();
         else if (current.getRight() == null)
            return current.getLeft();
         
         current.setValue(min(current.getRight()));
         current.setRight(remove(current.getRight(), (String) current.getValue()));
         return current;
      
      } else if (str.compareTo(target) > 0) {
         current.setLeft(remove(current.getLeft(), target));
      } else if (str.compareTo(target) <= 0) {
         current.setRight(remove(current.getRight(), target));
      }
      
      return current;
   
   }
}