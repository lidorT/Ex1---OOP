package myMath;

import org.junit.Test;


/**
 * @author Lidor_t and Zohar_m
 *
 */
public class ComplexFunctionTest {

	
	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction()}.
	 */
	@Test
	public void testComplexFunction() {
		ComplexFunction cf = new ComplexFunction();
		
	}

	
	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(myMath.Polynom)}.
	 */
	@Test
	public void test_ComplexFunction_Polynom() {
		
		System.out.println("ComplexFunction(Polynom p): ");
		
		Polynom p1 = new Polynom("4x^2");
		Polynom p2 = new Polynom("4x^2+6");
		Polynom p3 = new Polynom("x^3+2x+19");
		
		ComplexFunction cf1 = new ComplexFunction(p1);
		ComplexFunction cf2 = new ComplexFunction(p2);
		ComplexFunction cf3 = new ComplexFunction(p3);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(myMath.function, myMath.Operation, myMath.function)}.
	 */
	@Test
	public void test_ComplexFunction_Function_Operation_Function() {
		
		System.out.println("ComplexFunction(Function f1,Operation op,Function f2): ");
		
		String s1 = "2x^2+6";
		String s2 = "x^3+2x+19";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Operation op = Operation.Plus;
		
		
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p2,op,p1);
		ComplexFunction cf3 = new ComplexFunction(cf1,op,cf2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(java.lang.String, myMath.function, myMath.function)}.
	 */
	@Test
	public void test_ComplexFunction_String_Function_Function() {
		
		System.out.println("ComplexFunction(String s,Function f1,Function f2): ");
		
		String s1 = "2x^4+6.3";
		String s2 = "x^3";
		String s3 = "5x^3+6x";
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3);
		String op1 = "plus";
		String op2 = "comp";
		String op3 = "mul";
		
		
		ComplexFunction cf1 = new ComplexFunction(op1,p1,p2);
		ComplexFunction cf2 = new ComplexFunction(op2,p2,p3);
		ComplexFunction cf3 = new ComplexFunction(op3,cf1,cf2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(myMath.function, myMath.Operation)}.
	 */
	@Test
	public void test_ComplexFunction_Function_Operation() {
		
		System.out.println("ComplexFunction(Function f,Operation op): ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("3x^5+2x^2+19x+15");
		Operation op = Operation.Plus;
		ComplexFunction cf = new ComplexFunction(p1,op,p2);
		ComplexFunction cf1 = new ComplexFunction(p3,op,cf);
		
		ComplexFunction cf2 = new ComplexFunction(cf,op);
		ComplexFunction cf3 = new ComplexFunction(cf1,op);
		
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(java.lang.String)}.
	 */
	@Test
	public void test_ComplexFunction_String() {
		
		System.out.println("ComplexFunction(String s): ");
		
		ComplexFunction cf1 = new ComplexFunction("comp(+1.0x -2.0,+1.0x +3.0)");
		ComplexFunction cf2 = new ComplexFunction("plus(div(+1.0x+1.0,mul(mul(-1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		ComplexFunction cf3 = new ComplexFunction("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
}

	/**
	 * Test method for {@link myMath.ComplexFunction#ComplexFunction(myMath.function)}.
	 */
	@Test
	public void test_ComplexFunction_Function() {
		
		System.out.println("ComplexFunction(Function): ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		
		
		ComplexFunction cf2 = new ComplexFunction(cf1);
		ComplexFunction cf3 = new ComplexFunction(cf2);
		System.out.println(cf2);
		System.out.println(cf3);
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#plus(myMath.function)}.
	 */
	@Test
	public void testPlus() {
		
		System.out.println("plus: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.plus(cf2);
		cf2.plus(m);
		cf3.plus(p2);
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#mul(myMath.function)}.
	 */
	@Test
	public void testMul() {
		
		System.out.println("mul: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.mul(cf2);
		cf2.mul(m);
		cf3.mul(p2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#div(myMath.function)}.
	 */
	@Test
	public void testDiv() {
		
		System.out.println("Div: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.div(cf2);
		cf2.div(m);
		cf3.div(p2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#max(myMath.function)}.
	 */
	@Test
	public void testMax() {
		
		System.out.println("Max: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.max(cf2);
		cf2.max(m);
		cf3.max(p2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#min(myMath.function)}.
	 */
	@Test
	public void testMin() {
		
		System.out.println("Min: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.min(cf2);
		cf2.min(m);
		cf3.min(p2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#comp(myMath.function)}.
	 */
	@Test
	public void testComp() {
		
		System.out.println("comp: ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		ComplexFunction cf3 = new ComplexFunction(p1);
		
		cf1.comp(cf2);
		cf2.comp(m);
		cf3.comp(p2);
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#left()}.
	 */
	@Test
	public void testLeft() {
		
		System.out.println("Left: ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("10x^3+2x+19");
		Operation op = Operation.Plus;
		ComplexFunction cf = new ComplexFunction(p2,op,p1);
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(cf,op,cf1);
		
		ComplexFunction cf3 = new ComplexFunction(cf2.left);
		String s = cf3.toString();
		
		ComplexFunction cf4 = new ComplexFunction(s);
		System.out.println(cf4.toString());
		
		System.out.println();
	}

	
	/**
	 * Test method for {@link myMath.ComplexFunction#right()}.
	 */
	@Test
	public void testRight() {
		
		System.out.println("Right: ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("10x^3+2x+19");
		Operation op = Operation.Plus;
		ComplexFunction cf = new ComplexFunction(p2,op,p1);
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(cf,op,cf1);
		
		ComplexFunction cf3 = new ComplexFunction(cf2.right);
		String s = cf3.toString();
		
		ComplexFunction cf4 = new ComplexFunction(s);
		System.out.println(cf4.toString());
		
		System.out.println();
	}

	
	/**
	 * Test method for {@link myMath.ComplexFunction#getOp()}.
	 */
	@Test
	public void test_getOp() {
		
		System.out.println("get_op: ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("10x^3+2x+19");
		
		Operation op1 = Operation.Plus;
		Operation op2 = Operation.Times;
		Operation op3 = Operation.Divid;
		Operation op4 = Operation.Comp;
		
		ComplexFunction cf = new ComplexFunction(p2,op2,p1);
		ComplexFunction cf1 = new ComplexFunction(p1,op2,p2);
		ComplexFunction cf2 = new ComplexFunction(cf,op3,cf1);
		ComplexFunction cf3 = new ComplexFunction(cf2,op4,cf);
		ComplexFunction cf4 = new ComplexFunction(cf1,op1,cf1);
		ComplexFunction cf5 = new ComplexFunction(cf2,op2,cf);
		
		Operation op5 = cf2.getOp();
		Operation op6 = cf3.getOp();
		Operation op7 = cf4.getOp();
		Operation op8 = cf5.getOp();
		
		System.out.println(op5);
		System.out.println(op6);
		System.out.println(op7);
		System.out.println(op8);
		
		System.out.println();
		
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#copy()}.
	 */
	@Test
	public void testCopy() {
		
		System.out.println("copy(): ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Operation op = Operation.Plus;
		
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(cf1.copy());
		System.out.println("The function that we want to copy: "+cf1.toString());
		System.out.println("After copy : "+cf2.toString());

		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#f(double)}.
	 */
	@Test
	public void test_Fx() {
		
		System.out.println("f(x): ");
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op1 = Operation.Plus;
		Operation op2 = Operation.Times;
		Operation op3 = Operation.Divid;
		
		ComplexFunction cf1 = new ComplexFunction(p1,op1,p2);
		ComplexFunction cf2 = new ComplexFunction(p3,op2,m);
		ComplexFunction cf3 = new ComplexFunction(p1,op3,cf2);
		ComplexFunction cf4 = new ComplexFunction(p1);
		
		System.out.println(cf1.f(3));
		System.out.println(cf2.f(3));
		System.out.println(cf3.f(3));
		System.out.println(cf4.f(3));
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#initFromString(java.lang.String)}.
	 */
	@Test
	public void testInitFromString() {
		
		System.out.println("InitFromString: ");
		
		ComplexFunction cf1 = new ComplexFunction();
		ComplexFunction cf2 = new ComplexFunction();
		ComplexFunction cf3 = new ComplexFunction();
		ComplexFunction cf4 = new ComplexFunction();
		
		cf1.initFromString("comp(+1.0x -2.0,+1.0x +3.0)");
		cf2.initFromString("plus(div(+1.0x+1.0,mul(mul(-1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		cf3.initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
		cf4.initFromString("x^3+2x^2+x+3");
		
		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		System.out.println(cf4.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#toString()}.
	 */
	@Test
	public void testToString() {

		System.out.println("toString: ");
		
		Monom m1 = new Monom("2x");
		Monom m2 = new Monom("x^5");
		Monom m3 = new Monom("7x^3");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		
		Operation op1 = Operation.Plus;
		Operation op2 = Operation.Times;
		Operation op3 = Operation.Divid;
		Operation op4 = Operation.Comp;
		
		ComplexFunction cf1 = new ComplexFunction(m1,op1,m2);
		ComplexFunction cf2 = new ComplexFunction(m3,op2,p1);
		ComplexFunction cf3 = new ComplexFunction(p2,op3,p3);
		ComplexFunction cf4 = new ComplexFunction(cf1,op4,cf2);
		ComplexFunction cf5 = new ComplexFunction("comp(+1.0x -2.0,+1.0x +3.0)");
		ComplexFunction cf6 = new ComplexFunction();
		cf6.initFromString("x^3+2x^2+x+3");

		System.out.println(cf1.toString());
		System.out.println(cf2.toString());
		System.out.println(cf3.toString());
		System.out.println(cf4.toString());
		System.out.println(cf5.toString());
		System.out.println(cf6.toString());
		
		System.out.println();
	}

	/**
	 * Test method for {@link myMath.ComplexFunction#equals(java.lang.Object)}.
	 */
	@Test
	public void Equals() {
		
		System.out.println("Equals: ");
		
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		
		ComplexFunction cf1 = new ComplexFunction("plus",p1,p2);
	    ComplexFunction cf2 = new ComplexFunction("plus",p2,p1);
	    ComplexFunction cf3 = new ComplexFunction("plus",p2,p3);
	    
	    boolean ans1 = cf1.equals(cf2); //should be true
	    boolean ans2 = cf1.equals(cf3); //should be false
	    
	    System.out.println(ans1);
	    System.out.println(ans2);
	    
	    System.out.println();
	}



}
