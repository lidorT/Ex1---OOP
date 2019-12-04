package myMath;	
import java.util.*;

import org.omg.SendingContext.RunTime;

public class ComplexFunction implements complex_function{



	//	/* A binary tree class in order to represents the complex function.
	//	 * 
	//	 */
	//	class BinaryTree {
	//	    BinaryTree left;
	//	    BinaryTree right;
	//	  
	//	}
	/*This class represents a Node that will hold each leave on the binary tree
	 * Operation op created in order to hold the ENUM arithmetic symbol.
	 *Function created in order to hold the function itself.
	 *for example : comlexfunction1*complexfunction2 will be represented as the next tree:	
	 *
	 *											*
	 *										  /	   \
	 *					      (left)x^3+2x  	 	    (right) - x^5+x^2
	 */
	class Node {
		Operation op=Operation.None;
		function f;
	}

	/*
	 * An array list to represent the complex function.
	 * at first we create 2 nodes.
	 * first node hold the left function(f1) and the operator between the two functions
	 * second node will hold the right function (f2)
	 * after that we store the nodes in the complex function array list.
	 */
	private ArrayList<Node> function_list = new ArrayList<>(); 
	public String function_by_string;


	/*
	 * deffult constractor.
	 */
	public ComplexFunction(){
		//		
		//		Node n= new Node();
		//		this.function_list.add(n);

	}


	public ComplexFunction(function f1,Operation op, function f2){

		Node n1 = new Node();
		n1.f=f1;
		n1.op=op;
		Node n2= new Node();
		n2.f=f2;
		n2.op=Operation.None;

		this.function_list.add(n1);
		this.function_list.add(n2);

	}



	public ComplexFunction(function f1,Operation op){

		Node n1 = new Node();
		n1.f=f1;
		n1.op=op;

		this.function_list.add(n1);
	}


	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {

		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Plus;
		this.function_list.add(n);
	}


	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {

		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Times;
		this.function_list.add(n);



	}


	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {

		Node n = new Node();
		Node Pointer = new Node();
		n.f = f1;
		Iterator<Node> functionIter = iteretor();
		while (this.function_list.iterator().hasNext()){
			Pointer=functionIter .next();
		}
		Pointer.op=Operation.Divid;
		this.function_list.add(n);

	}


	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {




	}


	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {
	}


	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {
	}	


	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {

		Node Pointer = new Node();
		Pointer.f=this.function_list.get(0).f;
		Pointer.op=this.function_list.get(0).op;
		Iterator<Node> functionIter = iteretor();
		ComplexFunction cf = new ComplexFunction();

		while (iteretor().next().op != Operation.None){
			Node temp = new Node();
			temp.f = Pointer.f;
			temp.op = Pointer.op;
			cf.function_list.add(temp);
			Pointer = functionIter.next();
		}
		return cf;
	}


	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {
		Node Pointer = new Node();
		Iterator<Node> functionIter = iteretor();
		while (iteretor().hasNext()){
			Pointer=functionIter.next();
		}

		ComplexFunction cf = new ComplexFunction();

		Node temp = new Node();
		temp.f=Pointer.f;
		temp.op=Pointer.op;

		cf.function_list.add(temp);

		return cf;
	}


	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp() {
		Node Pointer = new Node();

		Iterator<Node> functionIter = iteretor();


		while (iteretor().next().op != Operation.None){

			Pointer=functionIter.next();


		}

		return Pointer.op;

	}



	public function copy() {
		Node Pointer = new Node();
		Pointer.f=this.function_list.get(0).f;
		Pointer.op=this.function_list.get(0).op;
		ComplexFunction cf = new ComplexFunction();
		Iterator<Node> functionIter = iteretor();


		while (iteretor().hasNext()){
			Node temp = new Node();
			temp.f=Pointer.f;
			temp.op=Pointer.op;
			cf.function_list.add(temp);
			Pointer=functionIter.next();


		}

		return cf;
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}





	public function initFromString(String s) {

		s=s.toLowerCase();
		s=clear_spaces(s);
		
	
	if (CheckString(s)==false ) {
		
		try{
			Polynom p = new Polynom(s);
		}
		catch  (Exception e){
			throw new RuntimeException ("You have entered an invaild string input, please fix and try again.");
		}
		
		Polynom p = new Polynom(s);
		ComplexFunction cf = new ComplexFunction(p,Operation.None);
		return cf;
	}
		
		String temp = s;
		ComplexFunction ans = new ComplexFunction();

		Node pointer = new Node();

		while(temp.length() != 0) {

			int end = Close_Index(temp);
			int start = Open_Index(end,temp);
			int column = Column_Index (end,temp);

			int Case = Check_case(start,column,end,temp);
			ComplexFunction cf = new ComplexFunction();


			if(Case == 1){

				cf = initHelper(start,column,end,temp);
				Node n = new Node();
				n.f = cf;
				n.op = Operation.None;

				ans.function_list.add(n);
				pointer = n;

			}

			if(Case == 2){

				pointer.op =  get_op(start, temp);
				String s3 = temp.substring(column+1,end);
				cf = StringToComplex(s3,Operation.None);

				Node n = new Node();
				n.f = cf;
				n.op = Operation.None;
				pointer = n;

				ans.function_list.add(n);

			}

			if(Case == 3){

				pointer.op =  get_op(start, temp);
				String s3 = temp.substring(start+1,column);
				cf = StringToComplex(s3,Operation.None);

				Node n = new Node();
				n.f = cf;
				
				if(pointer.op == Operation.Divid){
					
					ComplexFunction tempans = new ComplexFunction();
					n.op = Operation.None;
					tempans.function_list.add(n);
					
					Node tempNode = new Node();
					tempNode.f = ans;
					tempNode.op = Operation.None;
					
					ans.function_list.clear();
					Node tempNode2 = new Node();
					tempNode2.f = tempans;
					tempNode2.op = Operation.Divid;
					
					ans.function_list.add(tempNode2);
					ans.function_list.add(tempNode);
					
					pointer = tempNode;
					
				}
				
				else{
				n.op = Operation.None;
				pointer = n;
				}
				ans.function_list.add(n);

			}

			int length = get_op_length(start,temp);
			temp = temp.substring(0, start-length) + temp.substring(end+1);

		}

		return ans;
	}




	private ComplexFunction initHelper (int start,int column,int end,String s){

		Operation op = get_op(start,s); 

		String s1=s.substring(start+1,column);
		String s2=s.substring(column+1,end);

		ComplexFunction cf = new ComplexFunction();
		cf = StringToComplex(s1, op, s2);

		return cf;
	}








	//// Private Methods:


	private static int[] flip_arr_values (int[] arr) {

		int[] temp = new int[arr.length];
		int j=0;
		for (int i=arr.length-1;i>-1;i--) {


			temp[j]=arr[i];
			j++;


		}

		return temp;

	}


	private Iterator<ComplexFunction.Node> iteretor() {
		return this.function_list.iterator();
	}

	/*
	 * This function gets a string and returns it without spaces 
	 * for ex:
	 * input : "3x^2 + 6x^3"
	 * output: "3x^2+6x^3"
	 */
	private String clear_spaces (String s) {

		s=s.replaceAll(" ","");

		return s;


	}




	private void Clean(String s){



		if (s.charAt(0)=='(' && s.charAt(s.length())==')'){

			s=s.substring(1,s.length()-1);

		}


	}

	/*
	 * this function returns index of the first ')'
	 */

	public static int Close_Index (String s){




		return s.indexOf(')');

	}

	/*
	 * this function returns index of the last '('
	 */
	public static int Open_Index (int index, String str){

		int Open_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)=='('){

				Open_Index=i;
				break;

			}

		}
		return Open_Index;

	}
	/*
	 * this function returns index of the first ','
	 */
	public static int Column_Index (int index, String str){

		int Column_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)==','){
				Column_Index=i;
				break;
			}
		}
		return Column_Index;

	}
	/*
	 * This function returns the operation of a given string.
	 */

	public static Operation get_op(int Index, String str){

		String temp=str.substring(Index-3,Index);

		Operation OpTemp = Operation.None;

		if (temp.equals("mul")) OpTemp = Operation.Times;
		if (temp.equals("div")) OpTemp = Operation.Divid;
		if (temp.equals("min")) OpTemp = Operation.Min;
		if (temp.equals("max")) OpTemp = Operation.Max;

		temp=str.substring(Index-4,Index);

		if (temp.equals("plus")) OpTemp = Operation.Plus;
		if (temp.equals("comp")) OpTemp = Operation.Comp;

		return OpTemp;
	}


	public static int get_op_length(int Index, String str){

		int ans=0;
		String temp=str.substring(Index-3,Index);

		if (temp.equals("mul") || temp.equals("div") || temp.equals("min") || temp.equals("max") ) ans = 3;

		temp=str.substring(Index-4,Index);

		if (temp.equals("plus") || temp.equals("comp")) ans = 4;

		return ans;
	}

	/**
	 * This function return an Integer that represent us one of the following cases :
	 * 1: Operator(function1,function2)
	 * 2: Operator(Operator(function1,function2),Operator(function3,function4))
	 * 3: Operator(Operator(function1,function2),function3)
	 * 4: Operator(function1,Operator(function2,function3))
	 * @param  
	  start - the index of '('
	  column - the index of ','
	  end - the index of ')'
	  str - string
	 */
	public static int Check_case(int start,int column,int end, String str){

		String temp = str;
		int Case = 1;

		String s1 = str.substring(start+1, column);
		String s2 = temp.substring(column+1, end);

		if(s1.equals("")) Case=2;
		if(s2.equals("")) Case=3;

		return Case;
	}



	public static ComplexFunction StringToComplex (String s1,Operation op ,String s2){

		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);

		ComplexFunction cf = new ComplexFunction(p1,op,p2);


		return cf;

	}


	public static ComplexFunction StringToComplex (String s1,Operation op){
		Polynom p1 = new Polynom(s1);
		ComplexFunction cf = new ComplexFunction(p1,op);

		return cf;
	}

	public static boolean CheckString (String s){

		boolean flag = true;

		if (CheckColumn(s)!=true) flag = false;
		if (CheckOperators(s)!=true) flag = false;
		return flag;
	}

	public static boolean CheckColumn (String s){

		boolean flag = true;
		int open_counter=0,close_counter=0,column_counter=0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)=='(') open_counter++;
			if (s.charAt(i)==')') close_counter++;
			if (s.charAt(i)==',') column_counter++;
		}
		if (open_counter!=close_counter || open_counter!=column_counter || close_counter != column_counter) flag =false;
		if (open_counter==0 && column_counter==0 && close_counter==0) flag =false;
		
		return flag;
	}


	public static boolean CheckOperators (String s){

		int index=0;
		Operation Op = Operation.None;
		boolean flag= true;
		for (int i=0; i<s.length();i++){
			if (s.charAt(i)=='(') {
				index =i;
				Op = get_op(index, s);
				if (Op == Operation.None){
					flag =false;
					return flag;
				}
			}
		}
		return flag;
	}
	
	
	
	
	
	public String toString() {
		
		String ans = "";
		
		Iterator<Node> IterComplex = this.function_list.listIterator();
		
		while (IterComplex.hasNext()){

			Node temp = (Node) IterComplex;
			ComplexFunction maybe  = (ComplexFunction) temp.f;
			Iterator<Node> Iter = maybe.function_list.iterator();
			while (Iter.hasNext()){
				Iter.toString();
				Iter.next();
			}
			IterComplex.next();
			
		}
		
		return ans;
	}
	
	
	
	
	
	
	
}