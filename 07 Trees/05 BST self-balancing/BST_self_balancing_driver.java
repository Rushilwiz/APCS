import java.util.*;
import java.io.*;
/*  Self-balancing BST's include AVL trees, red-black trees, and AA trees.
Look one up on the internet, and implement it in your BST class.
Test it with this driver.
*/

public class BST_self_balancing_driver
{
   public static void main( String[] args ) throws Exception
   {
      BST balancedTree = new BST();  
      //Scanner in = new Scanner(System.in);
      //System.out.print("Type in a line: ");  
      //String line = in.nextLine();
      String line = "a b c d e f g h i j k l m n o p q r s t u v w x y z";
      String[] str = line.split(" ");
      for(String item : str)
      {
         balancedTree.addBalanced( item );  //implement addBalanced() in your BST class
         System.out.println(balancedTree.display());
         System.out.println("------------------------------");
      }
   }
}

/***************************************************
     ----jGRASP exec: java BST_self_balancing_Teacher
 a
 
 ------------------------------
 	b
 a
 
 ------------------------------
 	c
 b
 	a
 
 ------------------------------
 		d
 	c
 b
 	a
 
 ------------------------------
 		e
 	d
 		c
 b
 	a
 
 ------------------------------
 		f
 	e
 d
 		c
 	b
 		a
 
 ------------------------------
 		g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			h
 		g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			i
 		h
 			g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			j
 		i
 	h
 			g
 		f
 			e
 d
 		c
 	b
 		a
 
 ------------------------------
 			k
 		j
 			i
 	h
 			g
 		f
 			e
 d
 		c
 	b
 		a
 
 ------------------------------
 			l
 		k
 	j
 		i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			m
 		l
 			k
 	j
 		i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			n
 		m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				p
 			o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				q
 			p
 				o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				r
 			q
 		p
 				o
 			n
 				m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				s
 			r
 				q
 		p
 				o
 			n
 				m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				t
 			s
 		r
 			q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				u
 			t
 				s
 		r
 			q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				v
 			u
 		t
 				s
 			r
 				q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				w
 			v
 				u
 		t
 				s
 			r
 				q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				x
 			w
 		v
 			u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 				y
 			x
 				w
 		v
 			u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 				z
 			y
 		x
 				w
 			v
 				u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 ************************************/