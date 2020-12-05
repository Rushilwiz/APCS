// Name: B6-24
// Date:  11/1/19

public class Widget implements Comparable<Widget>
{
   //fields
   private int myCubits, myHands;
   
   //constructors
   public Widget() {
      myCubits = 0;
      myHands = 0;
   }
   
   public Widget(int cubits, int hands) {
      myCubits = cubits;
      myHands = hands;
   }
   
   public Widget(Widget w) {
      myCubits = w.myCubits;
      myHands = w.myHands;
   }   
   
   
   //accessors and modifiers
   int getCubits() {
      return myCubits;
   }
   
   int getHands() {
      return myHands;
   }
   
   void setCubits(int cubits) {
      myCubits = cubits;
   }
   
   void setHands(int hands) {
      myHands = hands;
   }
   //compareTo and equals
   public int compareTo(Widget other) {
      int cubits = myCubits - other.myCubits;
      if (cubits != 0)
         return cubits;
      else
         return myHands - other.myHands; 
   }
       
   public boolean equals(Widget other) {
      if (other.myCubits == myCubits && other.myHands == myHands)
         return true;
      
      return false;
   }
   
   //toString
   
   public String toString() {
      return myCubits + " cubits " + myHands + " hands";
   }
   
}
