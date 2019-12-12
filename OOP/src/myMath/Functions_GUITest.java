package myMath;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Monom;
import myMath.Operation;
import myMath.Polynom;
import myMath.Range;
import myMath.function;
import myMath.functions;


/** 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
 * @author Lidor_t and Zohar_m
 */

	public class Functions_GUITest {
	
	public static void main(String[] a) throws IOException {
		
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
	
		
		data.saveToFile(file);
		Functions_GUI data2 = new Functions_GUI();
		data2.initFromFile(file);
		data2.saveToFile(file2);
		data2.drawFunctions(w,h,rx,ry,res);
		try {
			//data.saveToFile(file);
			//Functions_GUI data2 = new Functions_GUI();
			//data2.initFromFile(file);
			//data2.saveToFile(file2);
			//data2.drawFunctions(w,h,rx,ry,res);
			//data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		//data.drawFunctions(JSON_param_file);
	}
		
		
	private functions _data = null;
	
	@Before
	public void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	@Test
	public void Functions_GUI() {
		
		Functions_GUI fg = new Functions_GUI();
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		
		fg.add(new Polynom("x^2+34-2"));
		fg.add(m);
		fg.add(p1);
		fg.add(p2);
		fg.add(cf1);
		fg.add(cf2);
	}

	@Test
	public void InitFromFile() throws IOException {
		
		Functions_GUI fg1 = new Functions_GUI();
		Functions_GUI fg2 = new Functions_GUI();
		try {
			fg1.initFromFile("initfromfile.txt");
			fg2.initFromFile("initfromfile.txt");
			assertTrue(fg1.containsAll(fg2));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void SaveToFile()throws IOException {
		
		Functions_GUI fg1 = new Functions_GUI();
		
		fg1.add(new Polynom("3x^2+5x+9"));
		fg1.add(new Monom("8x"));
		fg1.add(new ComplexFunction("plus(6x^2+4x+2,4x)"));
		
		fg1.saveToFile("SaveToFile.txt");
	}
	
	
	//@Test
	public void SaveToAndInitFromFile()throws IOException {
		
		Functions_GUI fg1 = new Functions_GUI();
		
		Monom m = new Monom("2x");
		Polynom p1 = new Polynom("2x^2+6");
		Polynom p2 = new Polynom("x^3+2x+19");
		Polynom p3 = new Polynom("x^3+2x^2+x+3");
		Operation op = Operation.Plus;
		ComplexFunction cf1 = new ComplexFunction(p1,op,p2);
		ComplexFunction cf2 = new ComplexFunction(p3);
		
		fg1.add(m);
		fg1.add(p1);
		fg1.add(p2);
		fg1.add(cf1);
		fg1.add(cf2);
		
		fg1.saveToFile("SaveToAndInitFromFile.txt");
		Functions_GUI copyFromFile = new Functions_GUI();
		copyFromFile.initFromFile("SaveToAndInitFromFile.txt");
		assertTrue(copyFromFile.equals(fg1));
		
		Functions_GUI fg2 = new Functions_GUI();
		fg2.initFromFile("initfromfile2.txt");
		assertEquals(fg1.toString(), fg2.toString());
	}
	
	
	//@Test
	public void Equals() {
		
		functions fg = FunctionsFactory();
		assertTrue(_data.equals(fg));
		fg.add(new Polynom("2x^2+6"));
		assertFalse(_data.equals(fg));
	}

	//@Test
	public void DrawFunctions() {
		
		Range rx = new Range(-10, 10);
		Range ry = new Range(-10, 10);
		//_data.drawFunctions(1000, 600, rx, ry, 200);
	}

	//@Test
	public void DrawFunctionsJSON() {
		//_data.drawFunctions("GUI_params.txt");
	}
	
	
	
	public static functions FunctionsFactory(){
		
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		Polynom p4 = new Polynom("x^3+2x+19");
		Polynom p5 = new Polynom("x^3+2x^2+x+3");
		ComplexFunction cf2 = new ComplexFunction(p4,Operation.Max,p5);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ans.add(cf2);
		ComplexFunction cf = new ComplexFunction(p1,Operation.Plus,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2x"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);
		
		System.out.println(ans.toString());
		return ans;
	}
	
	
}
