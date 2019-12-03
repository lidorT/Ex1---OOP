package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import myMath.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * @author Lidor and Zohar
 */

public class Polynom implements Polynom_able {
	/**
	 * Monoms_List is an arrayList of Monoms in order to storage for each Polynom his monoms.
	 */
	private ArrayList<Monom> Monoms_List = new ArrayList<>(); 
	/**
	 * cmp is a Monom comperator to ease the sort and for future use.
	 */
	private Monom_Comperator cmp = new Monom_Comperator();    // 

	/**
	 * Deafult constractor that builds Zero polynom.
	 */
	public Polynom() {
		this.Monoms_List.add(new Monom(0, 0));
	}

	
	public function initFromString(String s){
		
		String temp;
		temp = s.substring(0, s.indexOf('='));
		temp = temp.toLowerCase();
		if(temp.charAt(0) != 'y' || temp.charAt(1)!= '=' ){
			throw new RuntimeException("You entered an invaild input, please enter a function at the form of: y=f(x)");
		}
		function f = new Polynom(s.substring(s.charAt('=')+1));
		
		return f;
	}
	
	
	
	
	/**
	 * Init a Polynom from a String such as: {"x", "3+1.4x^3-34x","3x^3+2x-5"}.
	 * This constructor runs on the string and save each monom, we know to divide them when we find the characters "-" or "+".
	 * After that we save them in a string array including the "-" and "+" characters,
	 * Then we send each index to the monom constructor and storage them inside the polynom's ArrayList of Monoms(Monom_list).
	 * @param s - a string represents a Polynom. 
	 */

	public Polynom(String s) {

		String[] arr = new String[s.length()]; 
		int i = 0, j = 0, k = 0;
		while (i != s.length()) { 

			if (s.charAt(0) == '-' || s.charAt(0) == '+')
				i++; 
			
			while (s.charAt(i) != '+' || s.charAt(i) != '-') { 
		
				if (i + 1 == s.length()) { 
			
					arr[j] = s.substring(k, i + 1);
					k = i;
					j++;
					i++;
					break;

				}
				if (s.charAt(i) == '+' || s.charAt(i) == '-') {

					arr[j] = s.substring(k, i);
					k = i;
					j++;

				}
				i++;
			}
		}

		int x = 0;
		while (arr[x] != null) { 
	
			this.Monoms_List.add(new Monom(arr[x]));
			x++;
		}

	}

	/**
	 * This function calculate the value of f(x) by giving x.
	 * @param X - the value the user enters and wants to retrieve the function value at this point.
	 */
	public double f(double x) {
		double ans = 0;
		Iterator<Monom> Monoms = iteretor();
		while (Monoms.hasNext()) {
			Monom temp = Monoms.next();
			ans += temp.f(x);
		}
		return ans;
	}

	/**
	 * This function calculating the sum of two polynoms and combine them into the current polynom.
	 * This function receives an object of the type Polynom_able and combine it with the current polynom.
	 * @param p1 - the polynom_able we want to add into the current polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> iterMonom = p1.iteretor();
		while (iterMonom.hasNext()) {
			Monom m1 = iterMonom.next();
			this.add(m1);
		}
	}

	/**
	 * This Function allows us to add a Monom to our current Polynom
	 *   @param m1 -  the Monom that we want to add to this Polynom         
	 */
	public void add(Monom m1) {
		boolean Power = false;
		Iterator<Monom> iterMonom = this.iteretor();
		while (iterMonom.hasNext()) {
			Monom m2 = iterMonom.next();
			if (m1.get_power() == m2.get_power()) {
				m2.add(m1);
				Power = true;
				if (m2.get_coefficient() == 0)
					iterMonom.remove();
			}
		}
		if (Power == false && m1.get_power() >= 0) {
			if (m1.get_coefficient() != 0)
				Monoms_List.add(m1);

			this.Monoms_List.sort(cmp);
			
		}
	}

	/**
	 * This function gets polynom_able from the user that will be subtract it of the current Polynom.
	 * @param p1 - user's polynom_able input.
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> iterMonom = p1.iteretor();

		while (iterMonom.hasNext()) {
			Monom m1 = iterMonom.next();
			this.substract(m1);
		}
	}
	
	/**
	 * This function subtract the given Monom from the current Polynom using the add funcion of the given Monom*-1 .
	 * @param m1 - user's input Monom.
	 */
	public void substract(Monom m1) {
		Monom m2 = new Monom (m1.get_coefficient()*-1,m1.get_power());
		this.add(m2);
		if(this.Monoms_List.isEmpty())this.Monoms_List.add(Monom.ZERO);
	}

	/**
	 * This function receives a Polynom_able and multiply it with the current
	 * polynom. we first check if one of the given polynoms is equal to zero, if
	 * it so we define the current polynom as zero. we create 2 iterators in
	 * order to run on the Monom_list of the polynoms and we multiply each monom
	 * with the current polynom.
	 * @param p1  - Polynom_able.     
	 */
	public void multiply(Polynom_able p1) {
		if (this.isZero() || p1.isZero()) { 
			Polynom zero = new Polynom(); 		// zero polynom.
			this.Monoms_List = zero.Monoms_List;
		} else {
			Iterator<Monom> iterthis = this.Monoms_List.iterator();
			Iterator<Monom> iterpoly = p1.iteretor();

			Polynom p = new Polynom();
			while (iterpoly.hasNext()) {
				Monom m1 = new Monom(iterpoly.next());
				while (iterthis.hasNext()) {
					Monom m2 = new Monom(iterthis.next());
					m2.multiply(m1);
					p.add(m2);
				}
				iterthis = this.Monoms_List.iterator();
			}
			p.Monoms_List.sort(cmp);
			this.Monoms_List = p.Monoms_List;
		}
	}

	/**
	 * This function compares a given Polynom_able to the current Polynom and returns true if they are equals else return false.
	 * @param p1 - compare Polynom_able to the current Polynom.
	 */
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> iterthis = this.Monoms_List.iterator(); 
		Iterator<Monom> iterpolysize = p1.iteretor();
		
		boolean flag = true;
		int size = 0;
		while (iterpolysize.hasNext()) {
			iterpolysize.next();
			size++;
		}
		if (this.Monoms_List.size() != size)
			flag = false;
		Iterator<Monom> iterp1 = p1.iteretor();

		while (iterthis.hasNext()) {
			Monom m1 = iterthis.next();
			Monom m2 = iterp1.next();
			if (!(m1.equals(m2))) {
				flag = false;
				return flag;
			}
		}
		return flag;
	}

	/**
	 * This function checks if the current Polynom is a Zero Polynom.
	 */
	public boolean isZero() {
		Iterator<Monom> poly = this.iteretor();
		return (poly.next().get_coefficient() == 0);
	}

	/** 
	 * This function calculate the root of a polynom
	 * 
	 */
	public double root(double x0, double x1, double eps) {
		 // Prints root of func(x) with error of EPSILON 
		   
		        if (f(x0) * f(x1) >= 0){
		            throw new RuntimeException("ERROR, Invalid input: You have not assumed right x0 and x1"); 
		        } 
		  
		        double c = x0; 
		        while ((x1-x0) >= Monom.EPSILON) 
		        { 
		            c = (x0+x1)/2;  // Find middle point 
		            if (f(c) == 0.0) // Check if middle point is root
		                break; 
		  
		            else if (f(c)*f(x0) < 0) // Decide the side to repeat the steps 
		            	x1 = c; 
		            else
		                x0 = c; 
		        }
		        return c;
		    }

	/**
	 * This function performs a deep copy of a given Polynom and transform it to a Polynom_able.
	 * we create Polynom_able p1 in order to copy the current polynom to p1.
	 * iterpoly is an iterator that run on the current polynom. we build a new
	 * monom m that holds the current polynom's monom and add it to p1.
	 */
	public Polynom_able copy() {
		Polynom_able p1 = new Polynom();
		Iterator<Monom> iterpoly = this.iteretor();
		while (iterpoly.hasNext()) {
			Monom m = new Monom(iterpoly.next());
			p1.add(m);
		}
		return p1;
	}


	/**
	 * This function derivative the current polynom and returns it as a Polynom_able.
	 * we build 1 iterator for the current polynom in order to run on the
	 * polynom monoms. m is a monom of Polynom, we send him to monom's
	 * derivative method and save him in the Polynom_able p1. in the end we
	 * return p1
	 */
	public Polynom_able derivative() {
		Polynom_able p1 = new Polynom();
		Iterator<Monom> iterPolynom = this.Monoms_List.iterator();
		
		while (iterPolynom.hasNext()) {
			Monom m = new Monom(iterPolynom.next().derivative());
			p1.add(m);
		}
		return p1;
	}

	/**
	 * This method calculate the area of a given polynom using the Riemann's integral from x0 to x1 in eps steps.
	 * First of all we check if f(x0) and f(x1) are lower than 0 and if they are we throw RuntimeExeption.
	 * After that we calculate the are of the polynom by eps steps.
	 */
	public double area(double x0, double x1, double eps) {
		double sum = 0;
		if (eps<0){
			System.out.println("your epsilon is lower than 0 , we took the absolute vaule of your input instead");
			eps=Math.abs(eps);
			
		}
		if (x0>=x1){
			System.out.println("error: x0 should be lower than x1 please fix it");
			return sum;
		}
		if (f(x0) < 0 && f(x1) < 0) {
			throw new RuntimeException("Error: f(x) under to axis of the x");
		}
		for (double i = x0; i <= x1; i+=eps) {
			sum += this.f(i)*eps;
		}
		return Math.abs(sum);
	}

	/**
	 * an Iterator of Monoms to run over the current Polynom.
	 */
	public Iterator<Monom> iteretor() {
		return this.Monoms_List.iterator();
	}

	/**This function gets a monom and multiply it with the current Polynom.
	 * @param m1 - a given Monom which will multiply with the current Polynom.
	 */
	public void multiply(Monom m1) {
		if (m1.get_coefficient() == 0) { // if m1=0
			this.Monoms_List.clear();
		}
		Iterator<Monom> iterMonom = this.Monoms_List.iterator();
		while (iterMonom.hasNext()) {
			Monom m = iterMonom.next();
			m.multiply(m1);
		}
	}
	
//	public function initFromString(String s){
//		
//		
//		
//		
//	}
	
	
	

	/**
	 * This function prints as a string the current Polynom.
	 */
	public String toString() {
		String ans = "";
		this.Monoms_List.sort(cmp);
		Iterator<Monom> iterMonom = this.iteretor();

		while (iterMonom.hasNext()) {

			Monom temp = iterMonom.next();
			String maybe  = temp.toString();
			if (maybe.charAt(0) == '-') {
				ans += temp.toString();
			} else {
				ans += '+';
				ans += temp.toString();
			}
		}
		if (ans.charAt(0) == '+')
			ans = ans.substring(1);
		return ans;
	}
	
}