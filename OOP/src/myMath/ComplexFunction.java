package myMath;	
import java.util.*;

public class ComplexFunction implements complex_function{



	//	/* A binary tree class in order to represents the complex function.
	//	 * 
	//	 */
	//	class BinaryTree {
	//	    BinaryTree left;
	//	    BinaryTree right;
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

	/*
	 * An array list to represent the complex function.
	 * at first we create 2 nodes.
	 * first node hold the left function(f1) and the operator between the two functions
	 * second node will hold the right function (f2)
	 * after that we store the nodes in the complex function array list.
	 */
	private ArrayList<Node> function_list = new ArrayList<>(); 
	private function left,right;
	private Operation op;
	private static final long serialVersionUID = 1L;
	public Queue<Polynom>PolyQ = new ArrayDeque<Polynom>();
	public Queue<Operation>OpQ = new ArrayDeque<Operation>();
	public Queue<Integer>DivQ = new ArrayDeque<Integer>();

	class Node {

		private Operation op;
		private function f;

		public Node() {
			f= new ComplexFunction();
			op = null;
		}

		public Node(String operator ,function f) {
			this.f = f;
			get_operator(operator);
		}

		public Node(Operation op ,function f) {
			this.f = f;
			this.op = op;
		}

		public function getf() {
			return  f;
		}

		public void setP(function f) {
			this.f = f;
		}

		public Operation getop() {
			return op;
		}

		public void setop(Operation op) {
			this.op = op;
		}

		public Node copy() {
			String te = this.op.toString();
			Node temp = new Node(te,f.copy());
			return temp;
		}
	}







	/*
	 * deffult constractor.
	 */
	public ComplexFunction(){

		this.right = new Polynom();
		this.left= new Polynom();
		this.function_list = new ArrayList<Node>();
	}

	public ComplexFunction(function f) {

		this.right = null;
		this.left =  f;
		this.function_list = new ArrayList<Node>();
		this.op = Operation.None;
	}


	public ComplexFunction(function f1,Operation op, function f2){

		this.left =  f1;
		this.right = f2;
		this.op = op;
		this.function_list = new ArrayList<Node>();
		Node temp = new Node(op,f2);
		function_list.add(temp);
	}


	public ComplexFunction(String operator, function f1, function f2) {

		this.left =  f1;
		this.right = f2;
		this.function_list = new ArrayList<Node>();
		Node temp = new Node(op,f2);
		function_list.add(temp);
		this.get_operator(operator);
	}



	public ComplexFunction(function f,Operation op){

		this.right = null;
		this.left =  f;
		this.function_list = new ArrayList<Node>();
		this.op = Operation.None;
	}


	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {

		Node temp = new Node("plus",f1);
		function_list.add(temp);
	}


	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {

		Node temp = new Node("mul" ,f1);
		function_list.add(temp);
	}


	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {

		Node temp = new Node("div" ,f1);
		function_list.add(temp);
	}


	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {

		Node temp = new Node("max" ,f1);
		function_list.add(temp);
	}


	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {

		Node temp = new Node("min" ,f1);
		function_list.add(temp);
	}


	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {

		Node temp = new Node("comp" ,f1);
		function_list.add(temp);
	}	


	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {
		
		return this.left;
	}


	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {
	
		return this.right;
	}


	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp() {

		return this.op;
	}



	public function copy() {

		ComplexFunction cf = new ComplexFunction();

		cf.setLeft(this.left.copy());
		if(this.right!=null) {
			cf.setRight(this.right.copy());
		}else {
			cf.setRight(null);
		}
		cf.setOp(this.op);


		ArrayList<Node> tempList = new ArrayList<Node>();
		Iterator<Node> iter = iteretor();
		while(iter.hasNext()) {
			tempList.add(iter.next());
		}
		cf.setList(tempList);
		return cf;
	}
	//function m = new ComplexFunction(this.op.toString(), this.left,this.right);
	//return m;


//	public double f(double x) {
//		
//		return 0.0;
//		
//	}
	
	
	public double f(double x) {
		
		
		
		Node temp = new Node();
		
		
	
		Iterator<Node> iter = iteretor();
		
		temp=this.function_list.get(0);
		double sum=0.0;
		
		
		
		
		if(temp.f !=null) {
			//sum = temp;
		}
		
		
		while(iter.hasNext()) {
			temp=iter.next();
		
			
		
			
			
			
			
			switch(temp.op) {
			case Plus: op = Operation.Plus;
			break;
			case Times: op = Operation.Times;
			break;
			//case  "div": op = Operation.Divid;
			//break;
			case Min :op = Operation.Min;
			break;
			case Max:op = Operation.Max;
			break;
			case Comp:op = Operation.Comp;
			break;
			case None:op = Operation.None;
			break;
			default: op = Operation.Error;
			}
			
			
			
			
			
			
			iter.next();
			
		}
		
	
		return sum;
	}

	
	
	
	
	

	public function initFromString(String s) {

		s=s.toLowerCase();
		s=clear_spaces(s);
		int counter=0;

		if (CheckString(s)==false ) {

			try{
				Polynom p = new Polynom(s);
			}
			catch  (Exception e){
				throw new RuntimeException ("You have entered an invaild string input, please fix and try again.");
			}

			Polynom p = new Polynom(s);
			ComplexFunction cf = new ComplexFunction(p);
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

				Operation tempOp = get_op(start,s);

				String s1 = temp.substring(start+1,column);
				String s2 = temp.substring(column+1,end);
				Polynom p1 = new Polynom(s1);
				Polynom p2 = new Polynom(s2);

				PolyQ.add(p1);
				PolyQ.add(p2);
				OpQ.add(tempOp);
				
				
				cf = new ComplexFunction(p1,tempOp,p2);
				
				Node lastNode = new Node(Operation.None,cf);
				if(ans.function_list.size()>0){
					ans.setOp(tempOp);
					pointer = ans.function_list.get(ans.function_list.size() - 1);
				}
				else{
					pointer.setop(tempOp);
				}
				
			
				
				if (counter>0) {
				ans.left=ans.copy();
				ans.function_list.add(lastNode);
			
				ComplexFunction Right= new ComplexFunction();
				Right.function_list.add(lastNode);
				ans.right=Right;
				}
				
				else {
					
					
					ans.left=ans.copy();
					ans.function_list.add(lastNode);
					
					
					
					
				}
				
				
				if(ans.op == Operation.None){
					int openIndex = Open_Index(start,temp);
					pointer.op = get_op(openIndex, temp);
				}
			}

			if(Case == 2){

				Operation tempOp = get_op(start,s);
				ans.setOp(tempOp);
				String s1 = temp.substring(column+1,end);
				Polynom p = new Polynom(s1);
				PolyQ.add(p);
				OpQ.add(tempOp);
				cf = new ComplexFunction(p);
				Node lastNode = new Node(Operation.None,cf);
				pointer = ans.function_list.get(ans.function_list.size()-1);
				pointer.setop(tempOp);	
				ans.left=ans.copy();
				ans.function_list.add(lastNode);
				ComplexFunction Right= new ComplexFunction();
				Right.function_list.add(lastNode);
				ans.right=Right;
		
			}


			if(Case == 3){

				Operation tempOp = get_op(start,s);
				ans.setOp(tempOp);
				String s1 = temp.substring(start+1,column);
				Polynom p = new Polynom(s1);
				PolyQ.add(p);
				OpQ.add(tempOp);
				cf = new ComplexFunction(p);
				Node lastNode = new Node(Operation.None,cf);
				pointer = ans.function_list.get(ans.function_list.size()-1);
				pointer.setop(tempOp);
				ans.left=ans.copy();
				ans.function_list.add(lastNode);
				ComplexFunction Right= new ComplexFunction();
				Right.function_list.add(lastNode);
				ans.right=Right;
				if (tempOp == Operation.Divid) {
					ans.DivQ.add(ans.function_list.size()-1);
					System.out.println("Entered to Queue:" +ans.DivQ.peek());
				}
			}
			int length = get_op_length(start,temp);
			temp = temp.substring(0, start-length) + temp.substring(end+1);
			counter++;
		}
	
		return ans;
	}




	////////// Private Methods: //////////////

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
	public void get_operator(String str){

		switch(str) {
		case "plus": op = Operation.Plus;
		break;
		case "mul": op = Operation.Times;
		break;
		case  "div": op = Operation.Divid;
		break;
		case "min" :op = Operation.Min;
		break;
		case "max":op = Operation.Max;
		break;
		case "comp":op = Operation.Comp;
		break;
		case "none":op = Operation.None;
		break;
		default: op = Operation.Error;
		}
	}



	public static Operation get_op(int Index, String str){

		String temp=str.substring(Index-3,Index);

		if (temp.equals("mul"))	return Operation.Times;
		if (temp.equals("div")) return Operation.Divid;
		if (temp.equals("min")) return Operation.Min;
		if (temp.equals("max")) return Operation.Max;
		
		temp=str.substring(Index-4,Index);
		if (temp.equals("plus")) return Operation.Plus;
		if (temp.equals("comp")) return Operation.Comp;

		return Operation.None;
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

		System.out.println(str);
		System.out.println();

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
		return this.left().toString();
	}


	public void setLeft(function left) {
		this.left = left;
	}
	
	public void setRight(function right) {
		this.right = right;
	}
	
	public void setOp(Operation op) {
		this.op = op;
	}
	
	public ArrayList<Node> getList() {
		return (ArrayList<Node>) this.function_list;
	}
	
	public void setList(ArrayList<Node> function_list) {
		this.function_list = function_list;
	}


	public static int get_op_length(int Index, String str){

		int ans=0;
		
		String temp=str.substring(Index-3,Index);
		if (temp.equals("mul") || temp.equals("div") || temp.equals("min") || temp.equals("max") ) {
			 ans = 3;
			 return ans;
		}

		temp=str.substring(Index-4,Index);
		if (temp.equals("plus") || temp.equals("comp")) ans = 4;

		return ans;
	}



	public ArrayList<Node> ArrayListCopy(ArrayList list){
		
		ArrayList<Node> copy =  new ArrayList();
		
		
		
		
		return function_list;
		
		
		
		
		
	}
	
	

}