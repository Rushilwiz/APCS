 // Name: B6-24
 // Date: 3/12/2020

import java.util.*;
import java.text.DecimalFormat;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      System.out.println("===========================");
      Polynomial poly3 = new Polynomial();   // (x+1)
      poly3.makeTerm(1, 1);
      poly3.makeTerm(0, 1);
      System.out.println("Map:  " + poly3.getMap());
      System.out.println("String:  " + poly3.toString());
         
      Polynomial poly4 = new Polynomial();    // (x-1)
      poly4.makeTerm(1, 1);
      poly4.makeTerm(0, -1);
      System.out.println("Map:  " + poly4.getMap());
      System.out.println("String:  " + poly4.toString());
      System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      System.out.println("==========================="); 
      Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      System.out.println("Map:  " + poly5.getMap());  
      System.out.println(poly5);

   }
}
interface PolynomialInterface
{
   public void makeTerm(Double exp, Double coef);
   public Map<Double, Double> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Double, Double> map;

   public Polynomial () {
      map = new TreeMap<>();
   }
   
   public Polynomial (Polynomial other) {
      map = new TreeMap<Double, Double>(other.getMap());
   }
   
   public Polynomial (String arg) {
      map = new TreeMap<>();
      
      String[] terms = arg.split(" ");
      for (String term : terms) {
         if (term.equals("+"))
            continue;
         if (term.contains("x^"))
            makeTerm(Double.parseDouble(term.charAt(term.length() - 1) + ""), Double.parseDouble(term.substring(0, term.length() - 3)));
         else if (term.contains("x"))
            makeTerm(1.0, Double.parseDouble(term.substring(0, term.length() - 1)));
         else
            makeTerm(0.0, Double.parseDouble(term));
      }
      
   }
   
   public void makeTerm(Integer intExp, Integer intCoef) {
      double exp = intExp + 0.0;
      double coef = intCoef + 0.0;
      if (map.containsKey(exp)) {
         map.put(exp, coef + map.get(exp));
      } else {
         map.put(exp, coef);
      }
      
   }

   
   public void makeTerm(Double exp, Double coef) {
      if (map.containsKey(exp)) {
         map.put(exp, coef + map.get(exp));
      } else {
         map.put(exp, coef);
      }
      
   }
   
   public double evaluateAt(double x) {
      double val = 0.0;
      for (double exp : map.keySet()) 
         val += map.get(exp) * Math.pow(x, exp);
      
      return val;
   }
   
   public Polynomial add(Polynomial other) {
      Polynomial sum = new Polynomial(other);
      for (double exp : map.keySet())
         sum.makeTerm(exp, map.get(exp));
         
      return sum;
   }
   
   public Polynomial multiply(Polynomial other) {
      Polynomial product = new Polynomial();
      
      for (double exp : map.keySet())
         for (double otherExp : other.map.keySet())
            product.makeTerm(exp + otherExp, map.get(exp) * other.map.get(otherExp));
         
      return product;
   }
   
   public Map<Double, Double> getMap() {
      return map;
   }
   
   public String toString() {
      String toRet = "";
      DecimalFormat df = new DecimalFormat("#.#");
      
      ArrayList<Double> keys = new ArrayList<Double>(map.keySet());
      
      for (int i = keys.size() - 1; i >= 0; i--) {
         double exp = keys.get(i) + 0.0;
         if (map.get(exp) != 0 && map.get(exp) != 1)
            toRet += df.format(map.get(exp));
         
         if (exp > 1)
            toRet += "x^" + df.format(exp);
         else if (exp == 1)
            toRet += "x";
           
            
         toRet += " + ";
      }
      
      return toRet.substring(0, toRet.length() - 2);
       
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/