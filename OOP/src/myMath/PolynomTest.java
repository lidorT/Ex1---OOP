package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testFx();
		testAdd();
		testSubtract();
		testMultiply();
		testEquals();
		testZero();
		testCopy();
		testDerivative();
		testRoot();
		testArea();
		testPolynomString();
		testError();
	}

	public static void testFx(){
		System.out.println("testFx: ");
		Polynom p = new Polynom();
		double x = Math.random()*10;

		Monom a = new Monom(2.8,2);
		Monom b = new Monom(-4.5,3);
		Monom c = new Monom(-1,1);

		p.add(a);
		p.add(b);
		p.add(c);
		System.out.println("x = " + x);
		System.out.println(p + " = " + p.f(x) );

	}

	public static void testAdd(){
		System.out.println();
		System.out.println("testAdd: ");

		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(2.4,1);

		Monom d = new Monom(-1,1);
		Monom e = new Monom(4,2);
		Monom f = new Monom(3.5,3);

		Polynom p = new Polynom();
		Polynom p1 = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);

		p1.add(d);
		p1.add(e);
		p1.add(f);

		System.out.println("p = " + p);
		System.out.println("p1 = " + p1);
		p.add(p1);
		System.out.println("p + p1 = " + p);

	}

	public static void testSubtract(){
		System.out.println();
		System.out.println("testSubtract");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,5);
		Monom c = new Monom(2.4,4);

		Monom d = new Monom(-1,1);
		Monom e = new Monom(4,2);
		Monom f = new Monom(0,3);

		Polynom p = new Polynom();
		Polynom p1 = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);
		p1.add(d);
		p1.add(e);
		p1.add(f);

		System.out.println("polynomial:");
		System.out.println("p = " + p);
		System.out.println("p1 = " + p1);
		p.substract(f);
		p1.substract(c);

		System.out.println();
		System.out.println("p -"+ f +" = " + p);
		System.out.println("p1 -"+ c +" = " + p1);

		System.out.println();
		System.out.println("p = " + p);
		System.out.println("p1 = " + p1);
		p.substract(p1);
		System.out.println("p - p1 = " + p);
	}

	public static void testMultiply(){
		System.out.println();
		System.out.println("testMultiply");
		Monom a = new Monom(1.5,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(2.4,1);

		Monom d = new Monom(-1,1);
		Monom e = new Monom(4,2);
		Monom f = new Monom(3.5,3);

		Polynom p = new Polynom();
		Polynom p1 = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);
		p1.add(d);
		p1.add(e);
		p1.add(f);
		System.out.println("p = " + p);
		System.out.println("p1 = " + p1);
		p.multiply(p1);
		System.out.println("p * p1 = " + p);
	}

	public static void testEquals(){
		System.out.println();
		System.out.println("isZero");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(2.4,1);

		Monom d = new Monom(-1,1);
		Monom e = new Monom(4,2);
		Monom f = new Monom(3.5,3);

		Polynom p = new Polynom();
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();

		p.add(a);
		p.add(b);
		p1.add(a);
		p1.add(b);
		p2.add(c);
		p2.add(d);
		p3.add(e);
		p3.add(f);

		System.out.println(p +" equal to "+p1+" ? " + p.equals(p1));
		System.out.println(p2 +" equal to "+p3+" ? " + p2.equals(p3));
	}

	public static void testZero(){
		System.out.println();
		System.out.println("testZero");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(2.4,1);

		Polynom p = new Polynom();
		Polynom p1 = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);

		System.out.println("Did "+ p+" equal to 0 ? " + p.isZero());
		System.out.println("Did "+ p1+" equal to 0 ? " + p1.isZero());
	}

	public static void testCopy(){
		System.out.println();
		System.out.println("testCopy");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(2.4,1);

		Polynom p = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);

		Polynom_able p1 = p.copy();

		System.out.println("p = " + p);
		System.out.println("p1 = " + p1);
		System.out.println("Did p equal p1 ? " + p.equals(p1));
	}

	public static void testDerivative(){
		System.out.println();
		System.out.println("testDerivative");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(-2.4,1);

		Polynom p3 = new Polynom();

		p3.add(a);
		p3.add(b);
		p3.add(c);

		System.out.println("p3 = " + p3);
		System.out.println("derivative of p3 = " + p3.derivative());
	}

	public static void testRoot(){
		System.out.println();
		System.out.println("testRoot");
		Monom a = new Monom(1.46,2);
		Monom b = new Monom(-10,0);
		Monom c = new Monom(-2.4,1);

		Polynom p = new Polynom();

		p.add(a);
		p.add(b);
		p.add(c);
		double root = p.root(0, 100, 0.001);

		System.out.println("The Polynom: "+p+" have the root: "+root);
	}

	public static void testArea(){
		System.out.println();
		System.out.println("testArea");
		Monom a = new Monom(1,5);
		Monom b = new Monom(3,2);
		Monom c = new Monom(7,0);

		Polynom p = new Polynom();
		p.add(a);
		p.add(b);
		p.add(c);
		double area = p.area(1, 4,0.0001);
		System.out.println("The area of Polynom: "+p+" is: "+area);
	}

	public static void testPolynomString(){
		System.out.println();
		System.out.println("testPolynomString: ");
		String s = "5x^2+3x^6+5x+6";

		System.out.println("Polynom s = " + s);
		System.out.println();
	}

	public static void testError(){
			System.out.println("testError: ");
			Polynom p=new Polynom("-2x^4-3x^3+8+5x^7");
		
			try{
				System.out.println(p.root(-1, 2, Monom.EPSILON));
			}
			catch(Exception e){
				System.out.println("The Polynom: "+p+" has no root\n");
			}
			
			try{
			System.out.println("try to calculate the area of: "+p+ " \nwith the next values: x0=-5,x1=-11, eps=0.0001");
			double area2 = p.area(-5, -11,0.0001);
			System.out.println( "the result is: "+area2+"\n");
			System.out.println("try to calculate the area of: "+p+ " \nwith the next values: x0=-30,x1=-10, eps=0.0001");
			double area = p.area(-30,-10,0.0001);
			System.out.println( "the result is: "+area+"\n");
			}
			catch(Exception e){
				System.out.println("The Polynom: "+p+"  has no area with the inputs x0=-30,x1=-10");
			}
			
			
			System.out.println();
			String s[] = {"4x^^2+7x","12xx+6","--4x^3+5z","4z^2+5z"};
			for(int i = 0; i < s.length ; i++) {
				try { 
					Polynom a = new Polynom(s[i]);
				}
				catch(Exception e){
					System.out.println(i+") "+s[i]+"     Invalid Polynom");
				}
			}
			
	}
		
}


