// Name: B6-24
// Date: 2/26/2020
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public ArrayList<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   /******
   @param s -- one string to be inserted
   ********/
   public E add(E s) 
   {
      root = add(root, s);
      size++;
      return s;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) 
   {      
      if (t == null)
         return new TreeNode<E>(s, null, null);
      
      TreeNode<E> p, q;
      p = q = t;
      E str;
      
      while(p != null) {
         str = p.getValue();
         if (str.compareTo(s) >= 0)
            p = p.getLeft();
          else
            p = p.getRight();

           if (p != null)
            q = p;
      }
      
      str = q.getValue();
      
      if (str.compareTo(s) >= 0)
         q.setLeft(new TreeNode<E>(s, null, null));
      else
         q.setRight(new TreeNode<E>(s, null, null));
      
      return t;
   }
   
   public String display()
   {
      return display(root, 0);      
   }
   private String display(TreeNode<E> t, int level)
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
   
   public boolean contains(E obj)
   {
      return contains(root, obj);
   }
   public boolean contains(TreeNode<E> t, E x)
   {
      if (t == null)
         return false;
      
      TreeNode<E> temp = root;
      E str = root.getValue();
      
      while (!str.equals(x)) {
         
         if (str.compareTo(x) >= 0)
            temp = temp.getLeft();
          else
            temp = temp.getRight();
          
          if (temp == null)
            return false;
         
         str = temp.getValue();
      }
      
      return true;
   }
   
   public E min()
   {
      return min(root);  
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if (t == null)
         return null;
      TreeNode<E> temp = t;
      while (temp.getLeft() != null) {
         temp = temp.getLeft();
      }
      return temp.getValue();
   }
   
   public E max()
   {
      return max(root);  
   }
   private E max(TreeNode<E> t)  //use recursion
   {
      if (t == null) return null;
      if (t.getRight() == null)
         return t.getValue();
      else
         return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal
   {
      if (t == null)
         return "";
      return toString(t.getLeft()) + " " + t.getValue().toString() + " "  + toString(t.getRight());
   }   
   
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      if (current == null)
         return null;
   
      E str = current.getValue();
      if (str.equals(target)) {
         if (current.getLeft() == null && current.getRight() == null)
            return null;
         else if (current.getLeft() == null)
            return current.getRight();
         else if (current.getRight() == null)
            return current.getLeft();
         
         current.setValue(min(current.getRight()));
         current.setRight(remove(current.getRight(), current.getValue()));
         return current;
      
      } else if (str.compareTo(target) > 0) {
         current.setLeft(remove(current.getLeft(), target));
      } else if (str.compareTo(target) <= 0) {
         current.setRight(remove(current.getRight(), target));
      }
      
      return current;
   
   }

   public ArrayList<E> toList () {
      return toList(root);
   }
   
   private ArrayList<E> toList(TreeNode<E> t) {
      ArrayList<E> toReturn = new ArrayList<>();
      if(t == null)
         return null;
      if (t.getLeft() != null)
         toReturn.addAll(toList(t.getLeft()));
      
      toReturn.add(t.getValue());              //preorder visit
      
      if (t.getRight() != null)
         toReturn.addAll(toList(t.getRight()));  //recurse right
      return toReturn;
   }
}

/*******************
  Copy your TreeNode<E> code.  Implement generics.
**********************/
class TreeNode<E>
{
      private E value; 
      private TreeNode<E> left, right;
   
       public TreeNode(E initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
       public TreeNode (E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
       public E getValue()
      { 
         return value; 
      }
   
       public TreeNode<E> getLeft() 
      { 
         return left; 
      }
   
       public TreeNode<E> getRight() 
      { 
         return right; 
      }
   
       public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
       public void setLeft(TreeNode<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
       public void setRight(TreeNode<E> theNewRight)
      { 
         right = theNewRight;
      }
}