package myMath;

import org.junit.Test;

public class MonomTestJunit {

	
	@Test
	public void testAdd() {
		System.out.println("testAdd: ");
		double a = 3.5;
		int b = 2;
		Monom m1 = new Monom(a,b);
		Monom m2 = new Monom(m1);
		m2.add(m1);
		System.out.println(m1+" + "+m1+" = "+m2);
	}


	@Test
	public void testMultiply() {
		System.out.println();
		System.out.println("testMultiply: ");
	
		Monom m = new Monom(3.5,2);
		Monom m1 = new Monom(13,0);
		Monom m2 = new Monom(m);
		m2.multiply(m1);
		System.out.println(m+" * "+m1+" = "+m2);
	}
	
	@Test
	public void testSubstract() {
		System.out.println();
		System.out.println("testSubstract: ");
		
		Monom m = new Monom(23,5);
		Monom m1 = new Monom(13,5);
		System.out.print(m+" - "+m1+" = ");
		m.substract(m1);
		System.out.print(m);
		System.out.println();
	}
	
	@Test
	public void testIsZero() {
		System.out.println();
		System.out.println("testIsZero: ");
		
		Monom m1 = new Monom(0.0,2);
		Monom m2 = new Monom(13,0);
		Monom m3 = new Monom(0.0,0);
		Monom m4 = new Monom(2,1);
		System.out.println("m1: "+m1);
		System.out.println("m1 is Zero ?: "+m1.isZero());
		System.out.println("m2: "+m2);
		System.out.println("m2 is Zero ?: "+m2.isZero());
		System.out.println("m3: "+m3);
		System.out.println("m3 is Zero ?: "+m3.isZero());
		System.out.println("m4: "+m4);
		System.out.println("m4 is Zero ?: "+m4.isZero());
	}
	
	@Test
	public void testDerivative() {
		System.out.println();
		System.out.println("testDerivative: ");

		Monom m3 = new Monom(2.5,2);
		System.out.println("Before derivative: "+m3);
		m3 = m3.derivative();
		System.out.println("After derivative: "+m3);
	}

	@Test
	public void testFx() {
		System.out.println();
		System.out.println("testFx: ");

		double x = Math.random()*10;
		Monom m1 = new Monom(3,2);
		Monom m2 = new Monom(5,1);

		System.out.println("if x = " + x);
		System.out.println(m1 + " = "  + m1.f(x));
		x = Math.random()*10;
		System.out.println("if x = " + x);
		System.out.println(m2 + " = "  + m2.f(x));
	}

	@Test
	public void testEquals() {
		System.out.println();
		System.out.println("testEquals: ");
		
		Monom m1 = new Monom(3,2);
		Monom m2 = new Monom(m1);
		Monom m3 = new Monom(3,2);
		Monom m4 = new Monom(7,0);

		System.out.println(m1+" equal to "+m2+" = " + m1.equals(m2));
		System.out.println(m3+" equal to "+m4+" = " +m3.equals(m4));
	}

	@Test
	public void testMonomString(){
		System.out.println();
		System.out.println("testMonomString: ");
		
		String s = "-4X";
		String s1 = "X";
		String s2 = "-4.4X";
		String s3 = "-5.6X^0";
		String s4 = "6X^5";
		String s5 = "-X";
		Monom m = new Monom(s);
		System.out.println("String s = " + s);
		System.out.println("Monom m = String s = " + m);
		Monom m1 = new Monom(s1);
		System.out.println("String s = " + s1);
		System.out.println("Monom m = String s1 = " + m1);
		Monom m2 = new Monom(s2);
		System.out.println("String s = " + s2);
		System.out.println("Monom m = String s2 = " + m2);
		Monom m3 = new Monom(s3);
		System.out.println("String s = " + s3);
		System.out.println("Monom m = String s3 = " + m3);
		Monom m4 = new Monom(s4);
		System.out.println("String s = " + s4);
		System.out.println("Monom m = String s4 = " + m4);
		Monom m5 = new Monom(s5);
		System.out.println("String s = " + s5);
		System.out.println("Monom m = String s5 = " + m5);
		System.out.println();	
	}
		
	@Test
	public void testError() {
	
	System.out.println("**********************************");
	System.out.println("Monom Error Test: ");
	String  [] Monoms = {"2","-x^2x","-x","-4x^3p3","s","0x^r","7xx","--7x","3x^3+"};
	String  [] Monoms1 = {"2x","-7x^2","x","13","5x"};
	
	int counter = 0;
	for(int i=0;i<Monoms.length;i++) {
		try {
			Monom a = new Monom(Monoms[i]);
			System.out.println(i+") "+Monoms[i]+"    \tlegal monum");
		}
		catch(Exception e){
			System.out.println(i+") "+Monoms[i]+"    \tInvalid monum");
			counter++;
		}
	}
	
	System.out.println();
	Monom b = new Monom("4x");
	for(int i = 0; i < Monoms1.length ; i++) {
		try { 
			Monom a = new Monom(Monoms[i]);
			a.add(b);
			System.out.println(i+")"+" Summary Monom: "+a+" with monom: "+b+" = "+a);
		}
		catch(Exception e){
			System.out.println("Summary Monom: "+Monoms[i]+" with monom: "+b+" = "+"Erorr,the Monoms powers are not equals");
			System.out.println(i+") Erorr,the Monoms powers are not equals please build a Polynom.");
			counter++;
		}
	}
	
	System.out.println();
	for(int i = 0; i < Monoms1.length ; i++) {
		try { 
			Monom a = new Monom(Monoms[i]);
			a.substract(b);
			System.out.println(i+")"+" substraction Monoms: "+a+" with monom: "+b+" = "+a);
		}
		catch(Exception e){
			System.out.println("Substraction Monom: "+Monoms[i]+" with monom: "+b+" = "+"Erorr,the Monoms powers are not equals");
			System.out.println(i+") Erorr,the Monoms powers are not equals please build a Polynom.");
			counter++;
		}
	}
	
	if (counter==Monoms.length) {
		System.out.println("There is no valid monom in the array");
	}
	else {
		System.out.println();
		System.out.println("There are "+((Monoms.length+Monoms1.length*2)-counter)+" Monoms that legal in the list");
		System.out.println("There are "+(counter)+" Monoms that illegal in the list");
	}
}


}
