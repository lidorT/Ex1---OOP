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
	private function left,right;
	private Operation op;
	public ArrayList<Node> PolynomList = new ArrayList<>(); 
	public Queue<Integer>DivQ = new ArrayDeque<Integer>();


	private static final long serialVersionUID = 1L;

	class Node {

		private Operation G_op;
		private Operation P_op;
		private Polynom left;
		private Polynom right;

		public Node() {
			this.G_op=Operation.None;
			this.P_op=Operation.None;
			this.left = null;
			this.right=null;
		}


		public Node(Polynom p1,Operation P_op , Polynom p2, Operation G_op) {
			this.left = p1;
			this.right =p2;
			this.P_op = P_op;
			this.G_op= G_op;
		}


		public void setG_op(Operation op) {
			this.G_op = op;
		}
		public void setP_op(Operation op) {
			this.P_op = op;
		}
		public Node copy() {
			Node temp = new Node(this.left,this.P_op,this.right,this.G_op);
			return temp;

		}
	}

	/*
	 * deffult constractor.
	 */
	public ComplexFunction(){

		this.PolynomList = new ArrayList<Node>();
	}



	public ComplexFunction(function f1,Operation op, function f2){

		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,op,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());

				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}

			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter1.next();

			}
		}



		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,op);
			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter2.next();
			}
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {


			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);

			int counter=f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());
				if(counter==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
		}
	}


	public ComplexFunction(String operator, function f1, function f2) {

		int z = operator.length();
		Operation tempOp = get_op(z-1, operator);

		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,tempOp,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);

			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());

				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=tempOp;
				}
				iter1.next();
				counter--;
			}

			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter1.next();
			}
		}



		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,tempOp);

			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
				iter2.next();
			}
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);

			int counter=f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			while (iter1.hasNext()) {
				this.PolynomList.add(iter1.next());
				if(counter==1) {
					Pointer=iter1.next();
					Pointer.G_op=tempOp;
				}
				iter1.next();
				counter--;
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
		}
	}



	public ComplexFunction(function f,Operation op){


		if (f instanceof Polynom) {

			Node temp = new Node((Polynom) f,Operation.None,null,op);
			this.PolynomList.add(temp);
		}

		if (f instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f);

			int counter=f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {

				this.PolynomList.add(iter1.next());
				if (counter ==1) {
					Pointer=iter1.next();
					Pointer.G_op=op;
				}
				iter1.next();
				counter--;
			}
		}
	}


	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Plus);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Plus, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Times);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Times, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}

	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
	public void div(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Divid);
			this.PolynomList.add(temp);
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Divid, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Max);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList = CombineLists(this.PolynomList, Operation.Max, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}

	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Min);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Min, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
	public void comp(function f1) {
		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Comp);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Comp, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function left() {

		ComplexFunction temp =  new ComplexFunction();
		int counter=this.PolynomList.size();
		Iterator <Node> iter1 = this.iteretor();

		while (iter1.hasNext()) {

			if (counter ==1) {
				break;
			}
			temp.PolynomList.add(iter1.next());
			iter1.next();
			counter--;
		}
		return temp;
	}


	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	public function right() {

		ComplexFunction temp =  new ComplexFunction();
		temp.PolynomList.add(this.PolynomList.get(this.PolynomList.size()-1));

		return temp;
	}


	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
	public Operation getOp() {

		return this.PolynomList.get(this.PolynomList.size()-2).G_op;
	}



	public function copy() {

		ComplexFunction temp = new ComplexFunction();
		Iterator <Node> iter1 = this.iteretor();

		while (iter1.hasNext()) {
			Node tempNode = new Node();
			tempNode=iter1.next().copy();
			temp.PolynomList.add(tempNode);
			iter1.next();
		}
		return temp;
	}

	
	
	

	public double f(double x) {
		double sum = 0.0;
		Node temp = new Node();
		
		Iterator<Node> iter = this.PolynomList.iterator();
		if(iter.hasNext()) {
			sum = calcF(iter.next(),x);
		iter.next();
		}
		
		while(iter.hasNext()) {
			
			temp=iter.next();
			switch(temp.G_op) {
			
			case Plus: sum += calcF(temp,x);
			break;
			case Times: sum *= calcF(temp,x);
			break;
			case  Divid: sum /= calcF(temp,x);
			break;
			case Min :	;
			break;
			case Max: ;
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
	
	
	public double calcF(Node temp,double x) {
		
		double sum = 0;
		
		if(temp.right == null) {
			sum = temp.left.f(x);
		}
		else {
			
			double temp1 = temp.left.f(x);
			double temp2;
			switch(temp.P_op) {
			
			case Plus: sum = temp1 + temp.right.f(x);
			break;
			case Times: sum = temp1 * temp.right.f(x);
			break;
			case  Divid: 
				
				sum = temp1 / temp.right.f(x);
				//////////////////////////////////////////////////////////////
				//// take care in case of Operation.Divid && DivQ
				
			break;
			case Min : temp2 = temp.right.f(x);
				if(temp1 > temp2)sum = temp2;
				else sum = temp2;
			break;
			case Max: temp2 = temp.right.f(x);
				if(temp1 > temp2)sum = temp2;
				else sum = temp1;
			break;
			case Comp: sum = temp.right.f(temp1);
			break;
			case None: sum = temp1;
			break;
			
			default: sum = 0.0;
			
			}		
			
			
		}
		
		return sum;
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
			ComplexFunction cf = new ComplexFunction();
			Node answer = new Node();
			answer.left=p;
			cf.PolynomList.add(answer);
			return cf;
		}

		String temp = s;
		//ComplexFunction ans = new ComplexFunction();
		Operation tempOp =Operation.None;

		while(temp.length() != 0) {

			int end = Close_Index(temp);
			int start = Open_Index(end,temp);
			int column = Column_Index (end,temp);
			int Case = Check_case(start,column,end,temp);

			if(Case == 1){

				tempOp = get_op(start,s);
				String s1 = temp.substring(start+1,column);
				String s2 = temp.substring(column+1,end);
				Polynom p1 = new Polynom(s1);
				Polynom p2 = new Polynom(s2);

				Node tempNodeC = new Node(p1, tempOp, p2, Operation.None);
				this.PolynomList.add(tempNodeC);

			}	

			if(Case == 2){

				tempOp = get_op(start,s); 
				String s1 = temp.substring(column+1,end);
				Polynom p = new Polynom(s1);

				this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);
				Node tempNodeC = new Node(p,Operation.None,null,Operation.None);
				this.PolynomList.add(tempNodeC);
			}

			if(Case == 3){

				tempOp = get_op(start,s); 
				String s1 = temp.substring(start+1,column);
				Polynom p = new Polynom(s1);
				this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);

				Node tempNodeC = new Node(p,Operation.None,null,Operation.None);
				this.PolynomList.add(tempNodeC);

				if (tempOp == Operation.Divid) {
					this.DivQ.add(this.PolynomList.size()-1);
				}
			}
			int length = get_op_length(start,temp);
			temp = temp.substring(0, start-length) + temp.substring(end+1);

		}
		this.left = this.left();
		this.right = this.right();
		this.op = this.getOp();

		
		
		return this;
	}




	////////// Private Methods: //////////////

	private Iterator<ComplexFunction.Node> iteretor() {
		return this.PolynomList.iterator();
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
		return (ArrayList<Node>) this.PolynomList;
	}

	public void setList(ArrayList<Node> function_list) {
		this.PolynomList = function_list;
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



	public ArrayList<Node> ArrayListCopy(ComplexFunction cf){

		ArrayList<Node> copy =  new ArrayList();

		Iterator<Node> iter = cf.iteretor();
		while(iter.hasNext()) {
			Node temp = new Node();
			temp=iter.next().copy();
			copy.add(iter.next());
		}



		return copy;

	}


	public ArrayList<Node> CombineLists(ArrayList<Node> list1,Operation op, ArrayList<Node> list2){

		ArrayList<Node> ans = new ArrayList<Node>();
		Iterator <Node> iter1 = list1.iterator();

		while (iter1.hasNext()) {
			ans.add(iter1.next());
			iter1.next();
		}

		ans.get(this.PolynomList.size()-1).setG_op(op);
		Iterator <Node> iter2 = list2.iterator();

		while (iter2.hasNext()) {
			ans.add(iter2.next());
			iter2.next();
		}
		
		return ans;
	}
	
	
	

}