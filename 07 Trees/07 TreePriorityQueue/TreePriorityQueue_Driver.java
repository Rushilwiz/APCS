// Name:      
// Date:   
public class TreePriorityQueue_Driver
{
   private static TreePriorityQueue tpq = null;
   public static void main(String[] args)
   {
      tpq = new TreePriorityQueue();
      int[] array = {13, 11, 14, 11, 15, 14, 14};
       //	int[] array = {4, 6, 5, 7}; 
      //  int[] array = {7, 6, 4, 5}; 
         //int[] array = {7, 6, 4, 5, 4, 4};
      //   int[] array = {4, 5, 4, 4, 7, 6, 7, 7};
        
      tpq = build( tpq, array );
      System.out.println( tpq.display() );      
      System.out.println("Peek min: " + tpq.peekMin());
      System.out.println("Removing");
      while( !tpq.isEmpty() )
         System.out.println( "     " + tpq.removeMin() );
   }
   public static TreePriorityQueue build( TreePriorityQueue tpq, int[] array )
   {
      for( int x : array )
         tpq.add(x);
      return tpq;
   }
}

class TreePriorityQueue
{
   private TreeNode root;
   
   public TreePriorityQueue()
   {   root = null;  }
   
   //postcondition:  returns true if the priority queue is empty;
   //					  otherwise, returns false
   public boolean isEmpty()
   {  
      return root == null;
   }
   
   //postcondition:  obj has been added to the priority queue
   public void add(Object obj)
   {	
      root = addHelper((Comparable) obj, root);  
   }
   
   //postcondition:  obj has been added to the subtree rooted at t;
   //					  the updated subtree is returned
   private TreeNode addHelper(Comparable obj, TreeNode t)
   {
     if (t == null)
      return new TreeNode(new Item(obj));
      
     if (obj.compareTo(((Item) t.getValue()).getData()) > 0)
         t.setRight(addHelper(obj, t.getRight()));
     else if (obj.compareTo(((Item) t.getValue()).getData()) < 0)
         t.setLeft(addHelper(obj, t.getLeft()));
     else
         ((Item) t.getValue()).incrementCount();
     
     return t; 
   }
   			
   					
   //precondition:  the priority queue is not empty
   //postcondition:  a data value of highest priority (smallest value) has been 
   //						removed and returned
   public Object removeMin()
   { 
       
       if (root == null)
         return null;
       
       if (root.getLeft() == null) {
         Object temp = root.getValue();
         root.setLeft(null);
         return temp;
       }
       
       TreeNode pointer = root;
       while (pointer.getLeft().getLeft() != null)
         pointer = pointer.getLeft();
       
                Object temp = root.getValue();
         root.setLeft(null);
         return temp;


   }
   
   //precondition:   the priority queue is not empty
   //postcondition: a data value of highest priority (smallest value) if returned; 
   //					 the priority queue is unchanged
   public Object peekMin()
   {	
        if (root == null)
         return null;
       
       TreeNode pointer = root;
       while (pointer.getLeft() != null)
         pointer = pointer.getLeft();
         
       return ((Item)pointer.getValue()).getData();
   }
   public String display()
   {
      return display( root, 0 );
   }
   private String display( TreeNode t, int level )							
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
}
  
class Item
{
   private Comparable data;
   private int count;
   public Item(Comparable d)
   {  
      data = d;
      count = 1;
        
   }
   public void incrementCount()
   {	
      count++;
   }
   //precondition:  the count of this item is greater than 0;
   public void decrementCount()
   {  
     count--; 
   }
   public int getCount()
   {	
      return count;
   }
   public Comparable getData()
   {  
       return data;
   }
   public String toString()
   {
         return data.toString() + "[" + count + "]";
   }
}