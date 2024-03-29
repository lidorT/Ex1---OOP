package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), The class implements function and
 * support simple operations as:
 * construction, value at x, derivative, add and multiply.
 * @author Zohar and Lidor
 */
public class Monom implements function {

	private static final long serialVersionUID = 1L;
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}


	/**
	 * Constructor
	 * @param a - double
	 * @param b - Integer
	 */
	public Monom(double a, int b) { // ax^b

		if (a!=0) {
			String str = Double.toString(a);
			str=String.format("%.5g%n",a);
			a= Double.parseDouble(str);
		}
		if (a == 0) {
			this.set_coefficient(0);
			this.set_power(0);
		}
		this.set_coefficient(a);
		this.set_power(b);
	}

	/**
	 * Constructor
	 * @param m - Monom
	 */
	public Monom(Monom m) { 
		this(m.get_coefficient(), m.get_power());
	}


	/**
	 * @return - this._coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}


	/**
	 * @return - this._power
	 */
	public int get_power() {
		return this._power;
	}


	/**
	 * this method returns the derivative of the Monom .
	 * @return derivative of the Monom
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), (this.get_power() - 1));
	}


	/**
	 * This function calculating f(x) from a giving x
	 */
	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}


	/**
	 * @return boolean - true,if this Monom is zero
	 * @return boolean - false,if this Monom is not a zero Monom
	 */
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}


	/**
	 * This function receiving a String s and return a function that represent the giving string.
	 */
	public function initFromString(String s){

		String temp;
		temp = s.substring(0, s.indexOf('='));
		temp = temp.toLowerCase();
		if(temp.charAt(0) != 'y' || temp.charAt(1)!= '=' ){
			throw new RuntimeException("You entered an invaild input, please enter a function at the form of: y=f(x)");
		}
		function f = new Monom(s.substring(s.charAt('=')+1));

		return f;
	}


	/**
	 * This functioin responsible to build a Monom from the input string.
	 * If the string contains more than 1 power symbol ('^') or more than one x we throw exception.
	 * We split the given string into 2 parts: coefficient and power.
	 * This function deals with 3 different cases of inputs:
	 * 1. a Monom that have both coefficient*x and power ex:{"2.0x^5","4x^2","-x^0"}.
	 * 2. a Monom that have only coefficient*x ex:{"2.0x","x","14x","-x"}.
	 * 3. a Monom that have only coefficient ex:{"2.0","3","0"}.
	 * According to the above cases we converts the coefficient of x into Double and the power into Integer.
	 * After that we set the Monom's power and coefficient according to the string.
	 * @param s - given string that should present a Monom.
	 */
	public Monom(String s) {

		s = s.toLowerCase();
		s=clear_spaces(s);

		String err = "ERROR, Invalid input: required ax^b, where a is a real number and b is an integer (summed a none negative)";

		boolean flag = false, pow = false;

		int i = 0, j = 0, k, counter_x = 0, counter_pow = 0;
		for (k = 0; k < s.length(); k++) {
			if (s.charAt(k) == 'x') {
				flag = true;
				counter_x++;
			}
			if (s.charAt(k) == '^') {
				pow = true;
				counter_pow++;
			}
		}

		if (counter_pow < 2 && counter_x < 2) {

			if (flag && pow) {      /////case 1:  if we have 'x' and '^' in String s
				i=s.indexOf('x');
				j=s.indexOf('^');

				String n1 = s.substring(0, i);
				String n2 = s.substring(j + 1);
				if(i != 0 && j+1 != s.length()){

					if(n1.charAt(0) == '+'){
						if(n1.length() == 1){
							n1 = "1"; //case 1.1: if we have +coefficient
						}
						else n1 = n1.substring(1);
					}

					if (n1.charAt(0)=='-'){

						if(n1.length() == 1){
							n1 = "-1"; //case 1.2: if we have +coefficient
						}
						else n1 = n1.substring(0,i);

					}

					if (isDouble(n1)) {
						double ncopy = Double.parseDouble(n1);
						n1=String.format("%.5g%n",ncopy);
						ncopy = Double.parseDouble(n1);
						this.set_coefficient(ncopy);
					} else
						throw new RuntimeException(err);
					if (isInt(n2)) {
						this.set_power(Integer.parseInt(n2));
					} else
						throw new RuntimeException(err);
				}
				if(i == 0){
					this.set_coefficient(1);
					this.set_power(Integer.parseInt(n2));
				}
			} 
			else {
				if (flag && !pow) {		 /////case 2: if there is only 'x' and no '^'

					this.set_power(1);
					i = s.indexOf('x');
					if (i == 0) {
						this.set_coefficient(1); /////case 2.1: if the string include only 'x'
					} else {
						String n1 = s.substring(0, i);
						if (n1.equals("-")) {
							this.set_coefficient(-1); /////case 2.2: if the string include only '-x'

						}
						if (n1.equals("+")) {
							this.set_coefficient(1); /////case 2.3:  if the string include only '+x'
						}
						else if(n1.equals("+")==false && n1.equals("-")==false){
							if (isDouble(n1)){
								double n1copy = Double.parseDouble(n1);
								n1=String.format("%.5g%n",n1copy);
								n1copy = Double.parseDouble(n1);
								this.set_coefficient(n1copy);	
							}
							else
								throw new RuntimeException(err);
						}
					}
				}
			}

			if (!flag && !pow) { ///// case 3: if the String is only a real number

				this.set_power(0);
				boolean intOrdouble = true;
				try {
					String n1;
					if(s.charAt(0) == '+'){
						n1 = s.substring(1, s.length());
						int in = new Integer(n1);
					}
					else{
						int in = new Integer(s);
					}
				} 
				catch (Exception e) {
					intOrdouble = false;
				}

				if (!intOrdouble) {
					double d = Double.parseDouble(s);
					this.set_coefficient(Double.parseDouble(s));
				} else {
					int in = Integer.parseInt(s);
					this.set_coefficient(in);
				}
			}
		} else {
			throw new RuntimeException(err);
		}
	}



	/**
	 *This function calculating the sum of two Monoms.
	 *If the two Monoms powers are not equal it throws RuntimeException.
	 *If the two Monoms powers are equal we set the current Monom coefficient and power as the sum of the two Monoms.
	 *@param m - this given monom should be added to the current monom.
	 */
	public void add(Monom m) {
		if (this.get_power() == m.get_power()) {
			this.set_coefficient(this.get_coefficient() + m.get_coefficient());
		} else {
			throw new RuntimeException("Erorr,the Monoms powers are not equals please build a Polynom.");
		}
	}


	/**
	 *This function subtracts two Monoms.
	 *If the two Monoms powers are not equal it throws RuntimeException.
	 *If the two Monoms powers are equal we set the current Monom coefficient and power as the subtraction result of the two Monoms.
	 *@param m - this given monom should be subtracted from the current monom.
	 */
	public void substract(Monom m) {  

		if (this.get_power() == m.get_power()) {
			m.set_coefficient((m.get_coefficient())*(-1));
			this.set_coefficient(this.get_coefficient() + m.get_coefficient());
		} else {	
			throw new RuntimeException("Error,the powers are not equal please build a Polynom.");
		}
	}


	/**
	 * This function multiply the current Monom with the given one.
	 * If one of the Monoms is a zero we set the current one to be a zero Monom.
	 * Else we get the coefficients and the powers of both Monoms,multiply them and set the results respectively to the current Monom.
	 * @param d - Given Monom that will be multiplied with the current Monom.
	 */
	public void multiply(Monom d) {

		if (this.isZero() || d.isZero()) {
			this.set_coefficient(0);
			this.set_power(0);
		}
		if (this.get_power() == 0) {
			this.set_coefficient(this.get_coefficient() * d.get_coefficient());
			this.set_power(d.get_power());
		} else if (d.get_power() == 0) {
			this.set_coefficient(this.get_coefficient() * d.get_coefficient());
			this.set_power(this._power);
		} else {
			this.set_coefficient(this.get_coefficient() * d.get_coefficient());
			this.set_power(this.get_power() + d.get_power());
		}
	}


	/**
	 * This function prints as a string the current Monom.
	 */
	public String toString() {
		if (this._power == 0) {
			return this._coefficient + "";
		}
		if (this._power == 1) {
			return this._coefficient + "x";
		}
		if (this._coefficient == 0) {
			return 0 + "";
		} else
			return this._coefficient + "x^" + this._power;
	}


	/**
	 * This function checks if two Monoms are equals.
	 * We create two strings and convert the Monoms into strings using the toString<object> function.
	 * If the two strings are the same it returns true , else false.
	 * @param m - a given Monom which will be compared to the current Monom.
	 * @return - true / false according to the written above.
	 */
	public boolean equals(Monom m) { 
		boolean ans = false;

		if(m.isZero() && this.isZero())ans = true;
		String m1 = m.toString();
		String m2 = this.toString();

		if (m1.equals(m2))
			ans = true;

		return ans;
	}


	/**
	 * This is a deep copy function that return function.
	 */
	public function copy() {
		Monom m = new Monom(this);
		function f = new Monom(m);
		return f;
	}


	// *************************************************************
	// ****************** Private Methods and Data *****************
	// *************************************************************



	/**
	 * This is a private function that checks if a given string is a double or not.
	 * If the given string is a double the function returns true, else it returns false.
	 * @param s  - given string that we want to check if it is a double or not.
	 * @return - true / false according to the written above.
	 */
	private boolean isDouble(String s) {
		boolean ans = true;
		try {
			double d = new Double(s);
		} catch (Exception e) {
			ans = false;
		}
		return ans;
	}


	/**
	 * This is a private function that checks if a given string is an Integer or not.
	 * If the given string is an Integer the function returns true, else it returns false.
	 * @param s  - given string that we want to check if it is an Integer or not.
	 * @return - true / false according to the written above.
	 */
	private boolean isInt(String s) {
		boolean ans = true;
		try {
			int d = new Integer(s);
		} catch (Exception e) {ans = false;}
		return ans;
	}


	/**
	 * private method to set a coefficient of a Monom
	 * @param a - a double input that will be set as the current Monom coefficient.
	 */
	private void set_coefficient(double a) {
		this._coefficient = a;
	}


	/**
	 * private method to set a power of a Monom
	 * @param a - an Integer input that will be set as the current Monom power.
	 */
	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}


	/**
	 * This function gets a string and returns it without spaces 
	 * for example:
	 * input : "3x^2 + 6x^3"
	 * output: "3x^2+6x^3"
	 * @param s - a String that represent a ComplexFunction.
	 * @return s - a String without spaces that represent a ComplexFunction.
	 */
	private String clear_spaces(String s) {
		s=s.replaceAll(" ","");
		return s;
	}


	/**
	 * This function generate a Zero monom (0.0,0)
	 * @return Zero Monom
	 */
	private Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;

}
