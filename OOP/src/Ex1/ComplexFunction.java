package Ex1;	
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ComplexFunction implements complex_function{
	/**
	 * This class represents a ComplexFunction which is a combination of number of 
	 * Polynoms at the form of : " Operator(Polynom,Polynom) ".
	 * ComplexFunction is able to hold n Polynoms and n/2 Operators at the form of:
	 * Operator(Operator(Polynom,Polynom),Operator(Polynom,Polynom)) for example.
	 * This class supports number of mathematical methods such as:
	 * multiply,divide,plus and more.
	 * The data structure we used to hold the information is at the form of
	 * an array list that holds nodes, each nodes has one or two Polynoms ,
	 * the operator between the two Polynoms and the operator between each node.
	 * Each ComplexFunction has fields named left and right to represent the 
	 * formula inside the parenthesis.
	 * Each ComplexFunction has a field named op that holds the operator between two
	 * ComplexFunction.
	 * @author Lidor_T and Zohar_M
	 */

	public function left,right;
	public Operation op;
	public ArrayList<Node> PolynomList = new ArrayList<>(); 
	private Queue<Integer>DivQ = new ArrayDeque<Integer>();
	private static final long serialVersionUID = 1L;


	/**
	 * This class has an internal class of Nodes that will be chained in an ArrayList to represent each ComplexFunction.
	 * Each Node has a field named left and right to hold two Polynoms.
	 * Each Node has a field named P_op in order to hold the operation between the two Polynoms.
	 * Each Node has a field named G_op in order to hold the operation between two Nodes and chain all into 1
	 */
	class Node {

		private Operation G_op;
		private Operation P_op;
		private Polynom left;
		private Polynom right;

		private Node() {
			this.G_op=Operation.None;
			this.P_op=Operation.None;
			this.left = null;
			this.right=null;
		}

		private Node(Polynom p1,Operation P_op , Polynom p2, Operation G_op) {
			this.left = p1;
			this.right =p2;
			this.P_op = P_op;
			this.G_op= G_op;
		}

		private void setG_op(Operation op) {
			this.G_op = op;
		}

		private Node copy() {
			Node temp = new Node(this.left,this.P_op,this.right,this.G_op);
			return temp;

		}
	}


	/**
	 * Default contractor.
	 */
	public ComplexFunction(){
		this.PolynomList = new ArrayList<Node>();
	}


	/**
	 * This contractor creates ComplexFunction that holds Polynom p 
	 * @param p - represent an Object from type Polynom
	 */
	public ComplexFunction(Polynom p){

		this.PolynomList = new ArrayList<Node>();
		Node temp = new Node(p,Operation.None,null,Operation.None);
		this.PolynomList.add(temp);
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1,Operation op and function f2 
	 * @param f1 - function f1
	 * @param op - Operation op
	 * @param f2 - function f2
	 */
	public ComplexFunction(function f1,Operation op, function f2){

		if(op == Operation.None){
			throw new RuntimeException("Error, operation could not be None between 2 functions.");
		}
		
		if (f1 instanceof Monom && f2 instanceof Monom) {
			
			Polynom p1 = new Polynom(f1.toString());
			Polynom p2 = new Polynom(f2.toString());
			Node temp = new Node(p1,op,p2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f1 instanceof Monom && f2 instanceof Polynom) {
			
			Polynom p1 = new Polynom(f1.toString());
			Node temp = new Node(p1,op,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f1 instanceof Polynom && f2 instanceof Monom) {
			
			Polynom p1 = new Polynom(f2.toString());
			Node temp = new Node((Polynom)f1,op,p1,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,op,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List = ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List = ArrayListCopy((ComplexFunction)f2);

			int counter = f1List.size();
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();


			if (counter == 1) {
				Pointer = iter1.next();
				Pointer.G_op = op;
				this.PolynomList.add(Pointer);
			}
			else{
				while (iter1.hasNext()) {
					Pointer = iter1.next();
					if(counter == 1) {
						Pointer.G_op = op;
					}
					this.PolynomList.add(Pointer);
					counter--;
				}
			}

			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
			}
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,op);
			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
			}
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {


			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);

			int counter=f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			if (counter == 1) {
				Pointer = iter1.next();
				this.PolynomList.add(Pointer);
			}
			else{
				while (iter1.hasNext()) {
					Pointer = iter1.next();
					if(counter == 1) {
						Pointer.G_op = op;
					}
					this.PolynomList.add(Pointer);
					counter--;
				}
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1,Operation op from String operator and function f2 
	 * @param operator - a string that represent an Operation
	 * @param f1 - function f1 
	 * @param f2 - function f2
	 */
	public ComplexFunction(String operator, function f1, function f2) {

		Operation tempOp = get_operator(operator);
		if(tempOp == Operation.None){
			throw new RuntimeException("Error, operation could not be None between 2 functions.");
		}
		
		if (f1 instanceof Monom && f2 instanceof Monom) {
			
			Polynom p1 = new Polynom(f1.toString());
			Polynom p2 = new Polynom(f2.toString());
			Node temp = new Node(p1,tempOp,p2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f1 instanceof Monom && f2 instanceof Polynom) {
			
			Polynom p1 = new Polynom(f1.toString());
			Node temp = new Node(p1,tempOp,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f1 instanceof Polynom && f2 instanceof Monom) {
			
			Polynom p1 = new Polynom(f2.toString());
			Node temp = new Node((Polynom)f1,tempOp,p1,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof Polynom && f2 instanceof Polynom) {
			Node temp = new Node((Polynom)f1,tempOp,(Polynom)f2,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction && f2 instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f1);
			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);

			int counter=f1List.size();
			Node Pointer1 = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			if (counter == 1) {
				Pointer1 = iter1.next();
				Pointer1.G_op = tempOp;
				this.PolynomList.add(Pointer1);
			}
			else{
				while (iter1.hasNext()) {
					Pointer1 = iter1.next();
					if(counter == 1) {
						Pointer1.G_op = tempOp;
					}
					this.PolynomList.add(Pointer1);
					counter--;
				}
			}

			Iterator <Node> iter2 = f2List.iterator();
			Node Pointer2 = new Node();
			while (iter2.hasNext()) {
				Pointer2 = iter2.next();
				this.PolynomList.add(Pointer2);
			}
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}



		if (f1 instanceof Polynom && f2 instanceof ComplexFunction ) {

			Node temp = new Node((Polynom)f1,Operation.None,null,tempOp);

			this.PolynomList.add(temp);

			ArrayList<Node> f2List = new ArrayList<Node>();
			f2List=ArrayListCopy((ComplexFunction)f2);
			Iterator <Node> iter2 = f2List.iterator();
			while (iter2.hasNext()) {
				this.PolynomList.add(iter2.next());
			}
		}

		if (f1 instanceof ComplexFunction && f2 instanceof Polynom ) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List = ArrayListCopy((ComplexFunction)f1);

			int counter = f1List.size();
			Iterator <Node> iter1 = f1List.iterator();
			Node Pointer = new Node();

			if (counter == 1) {
				Pointer = iter1.next();
				this.PolynomList.add(Pointer);
			}
			else{
				while (iter1.hasNext()) {
					Pointer = iter1.next();
					if(counter == 1) {
						Pointer.G_op = op;
					}
					this.PolynomList.add(Pointer);
					counter--;
				}
			}
			Node temp = new Node((Polynom)f2,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
		}
	}


	/**
	 * This contractor creating ComplexFunction that holds function f1 and Operation op.
	 * @param f - function f
	 * @param op - Operation op
	 */
	public ComplexFunction(function f,Operation op){

		if (f instanceof Monom) {

			Polynom p = new Polynom(f.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
		
		if (f instanceof Polynom) {
			Node temp = new Node((Polynom) f,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f instanceof ComplexFunction) {

			ArrayList<Node> f1List = new ArrayList<Node>();
			f1List=ArrayListCopy((ComplexFunction)f);
			Node Pointer = new Node();
			Iterator <Node> iter1 = f1List.iterator();

			while (iter1.hasNext()) {
				Pointer=iter1.next();
				this.PolynomList.add(Pointer);
			}
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
			
		}
	}


	public ComplexFunction(String s) {
		this.initFromString(s);
	}


	public ComplexFunction(function f) {

		if (f instanceof Monom) {

			Polynom p = new Polynom(f.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f instanceof Polynom) {
			Node temp = new Node((Polynom) f,Operation.None,null,Operation.None);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f instanceof ComplexFunction){

			ArrayList<Node> fList = new ArrayList<Node>();
			fList=ArrayListCopy((ComplexFunction)f);
			Node Pointer = new Node();
			Iterator <Node> iter1 = fList.iterator();

			while (iter1.hasNext()) {
				Pointer=iter1.next();
				this.PolynomList.add(Pointer);
			}
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/** 
	 * Add to this complex_function the f1 function.
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	public void plus(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Plus);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}


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


	/** 
	 * Multiply this complex_function with the f1 complex_function.
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
	public void mul(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Times);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

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


	/** 
	 * Divides this complex_function with the f1 complex_function.
	 * @param f1 the complex_function which will be Divide this complex_function.
	 */
	public void div(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Divid);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof Polynom) {

			Node temp = new Node((Polynom) f1,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Divid);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

		if (f1 instanceof ComplexFunction) {

			this.PolynomList=CombineLists(this.PolynomList, Operation.Divid, ((ComplexFunction) f1).PolynomList);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}
	}


	/**
	 * Computes the maximum over this complex_function and the f1 complex_function.
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
	public void max(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Max);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

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


	/** 
	 * Computes the minimum over this complex_function and the f1 complex_function.
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
	public void min(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Min);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

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
	 * This method wrap the f1 complex_function with this function: this.f(f1(x)).
	 * @param f1 complex function
	 */
	public void comp(function f1) {

		if (f1 instanceof Monom) {

			Polynom p = new Polynom(f1.toString());
			Node temp = new Node(p,Operation.None,null,Operation.None);
			this.PolynomList.get(this.PolynomList.size()-1).setG_op(Operation.Comp);
			this.PolynomList.add(temp);
			this.left = this.left();
			this.right = this.right();
			this.op = this.getOp();
		}

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


	/** 
	 * Returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return temp - a ComplexFunction that representing the left side of this complex function.
	 */
	public function left() {

		ComplexFunction temp =  new ComplexFunction();
		int counter=this.PolynomList.size();
		Iterator <Node> iter1 = this.iteretor();
		Node tempN = new Node();

		if (counter == 1) {
			tempN = iter1.next();
			temp.PolynomList.add(tempN);
		}
		else{
			while (iter1.hasNext()) {

				if (counter ==1) {
					break;
				}
				tempN = iter1.next();
				temp.PolynomList.add(tempN);
				counter--;
			}
		}
		return temp;
	}


	/** 
	 * Returns the right side of the complex function - this side might not exists (could be equal to null).
	 * @return temp - a ComplexFunction that representing the left side of this complex function.
	 */
	public function right() {
		ComplexFunction temp =  new ComplexFunction();
		if(this.PolynomList.size()==1) temp = null;
		else {
			temp.PolynomList.add(this.PolynomList.get(this.PolynomList.size()-1));
		}
		return temp;
	}


	/**
	 * The function return an operation: plus, mul, div, max, min, comp
	 * @return Operation - plus, mul, div, max, min, comp.
	 */
	public Operation getOp() {

		if(this.PolynomList.size()==1){
			return this.PolynomList.get(this.PolynomList.size()-1).G_op;
		}
		return this.PolynomList.get(this.PolynomList.size()-2).G_op;
	}


	/**
	 * This function returns a deep copy of this complex function.
	 * @return temp - a ComplexFunction that represent the deep copy of this complex function.
	 */
	public function copy() {

		ComplexFunction temp = new ComplexFunction();
		Iterator <Node> iter1 = this.iteretor();

		while (iter1.hasNext()) {
			Node tempNode = new Node();
			tempNode=iter1.next().copy();
			temp.PolynomList.add(tempNode);
		}
		return temp;
	}


	/**
	 * This function receiving a String s and return a function that represent the giving string.
	 * @param s - a String that should be represent a ComplexFunction.
	 * @return this function witch represent the String s.
	 */
	public function initFromString(String s) {

		s = s.toLowerCase();
		s = clear_spaces(s);

		if (CheckString(s) == false ) {

			try{
				Polynom p = new Polynom(s);
			}
			catch  (Exception e){
				throw new RuntimeException ("You have entered an invaild string input, please fix and try again.");
			}

			Polynom p = new Polynom(s);
			Node answer = new Node(p,Operation.None,null,Operation.None);
			if(this.PolynomList.isEmpty()){
				this.PolynomList.add(answer);
				return this;
			}
			else{
				ComplexFunction cf = new ComplexFunction();
				cf.PolynomList.add(answer);
				return cf;
			}
			
		}

		String temp = s;
		Operation tempOp =Operation.None;
		int witchArray = 0;
		if(!this.PolynomList.isEmpty()) witchArray = 1;
		ComplexFunction cf = new ComplexFunction();

		while(temp.length() != 0) {

			int end = Close_Index(temp);
			int start = Open_Index(end,temp);
			int comma = comma_Index (end,temp);
			int Case = Check_case(start,comma,end,temp);

			if(Case == 1){
				int sss=4;
				tempOp = get_op(start,temp);
				String s1 = temp.substring(start+1,comma);
				String s2 = temp.substring(comma+1,end);
				Polynom p1 = new Polynom(s1);
				Polynom p2 = new Polynom(s2);
				Node tempNodeC = new Node(p1, tempOp, p2, Operation.None);
				if(witchArray == 1)cf.PolynomList.add(tempNodeC);
				if(witchArray == 0)this.PolynomList.add(tempNodeC);
			}	

			
			if(Case == 2){

				tempOp = get_op(start,temp); 
				String s1 = temp.substring(comma+1,end);
				Polynom p = new Polynom(s1);

				if(witchArray == 1)cf.PolynomList.get(cf.PolynomList.size()-1).setG_op(tempOp);
				if(witchArray == 0)this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);
				Node tempNodeC = new Node(null,Operation.None,p,Operation.None);
				if(witchArray == 1)cf.PolynomList.add(tempNodeC);
				if(witchArray == 0)this.PolynomList.add(tempNodeC);
			}

			if(Case == 3){

				tempOp = get_op(start,temp); 
				String s1 = temp.substring(start+1,comma);
				Polynom p = new Polynom(s1);
				
				if(witchArray == 1)cf.PolynomList.get(cf.PolynomList.size()-1).setG_op(tempOp);
				if(witchArray == 0)this.PolynomList.get(this.PolynomList.size()-1).setG_op(tempOp);

				Node tempNodeC = new Node(p,Operation.None,null,Operation.None);
				if(witchArray == 1)cf.PolynomList.add(tempNodeC);
				if(witchArray == 0)this.PolynomList.add(tempNodeC);

				if (tempOp == Operation.Divid) {
					if(witchArray == 1)cf.DivQ.add(this.PolynomList.size()-1);
					if(witchArray == 0)this.DivQ.add(this.PolynomList.size()-1);
					
				}
			}
			
			if(Case == 4){
				tempOp = get_op(start,temp);
				if(witchArray == 1)cf.PolynomList.get(cf.PolynomList.size()-2).setG_op(tempOp);
				if(witchArray == 0)this.PolynomList.get(this.PolynomList.size()-2).setG_op(tempOp);
			}

			int length = get_op_length(start,temp);
			temp = temp.substring(0, start-length) + temp.substring(end+1);

		}
		this.left = this.left();
		this.right = this.right();
		this.op = this.getOp();
		
		if(witchArray == 1)return cf;
		if(witchArray == 0)return this;

		return this;
	}


	/**
	 * This function calculate this ComplexFunction for the requested number x.
	 * @param x - the requested number that we want to calculate ComplexFunction(x).
	 * @return sum - stabilizes the function value at point x.
	 */
	public double f(double x) {

		int counter = 0;
		double sum = 0.0;
		Node temp = new Node();
		Operation tempOp = Operation.None;
		Iterator<Node> iter = this.PolynomList.iterator();
		if(iter.hasNext()) {
			temp=iter.next();
			tempOp = temp.G_op;
			sum = calcF(temp,x);
			counter++;
		}
		if(this.PolynomList.size()>1){
			while(iter.hasNext()) {

				temp=iter.next();
				switch(tempOp) {

				case Plus: sum += calcF(temp,x);
				break;
				case Times: sum *= calcF(temp,x);
				break;
				case  Divid: 
					if(!DivQ.isEmpty()){
						if(DivQ.peek()==counter){
							sum = 1/sum;
							double tempcalc = 1/(calcF(temp,x));
							sum = sum/tempcalc;
							DivQ.poll();
							DivQ.add(counter);
						}
					}	
					else{ 
						sum /= calcF(temp,x);
					}
					break;
				case Min :if(sum > calcF(temp,x)) sum = calcF(temp,x);
				break;
				case Max: if(sum < calcF(temp,x)) sum = calcF(temp,x);
				break;
				case Comp: sum = calcF(temp,sum);
				break;
				case None:break;
				default: op = Operation.Error;

				}
				tempOp = temp.G_op;
			}
		}
		String s = Double.toString(sum);
		s = String.format("%.5g%n",sum);
		sum = Double.parseDouble(s);
		return sum;	
	}


	/**
	 * This function prints a string of the current ComplexFunction.
	 */
	public String toString() {

		if(this.PolynomList.isEmpty())return null;
		String ans = "";

		Iterator<Node> iter = this.iteretor();
		Node temp = new Node();
		temp = iter.next();

		if(temp.right == null && temp.left!=null)
			ans = temp.left.toString();
		if(temp.left == null && temp.right!=null)
			ans = temp.right.toString();
		if(temp.left!=null && temp.right!=null){
			String s = EnumToString(temp.P_op);
			ans = s +"("+ temp.left.toString()+","+temp.right.toString()+")";
		}
		while(iter.hasNext()) {

			String s = EnumToString(temp.G_op);
			temp = iter.next();

			if(temp.right == null)
				ans = s +"("+ temp.left.toString()+","+ans+")";

			if(temp.left == null)
				ans = s +"("+ans+","+ temp.right.toString()+")";

			if(temp.left!=null && temp.right!=null){
				ans = s + "("+ans+","+temp.P_op.toString()+"("+ temp.left.toString()+","
						+temp.right.toString()+")"+")";
			}
		}
		return ans;
	}
	
	public String EnumToString(Operation op){
		
		String tempOp ;

		switch(op) {
		case Plus:tempOp = "plus";
		break;
		case Times:tempOp = "mul";
		break;
		case  Divid:tempOp = "div";
		break;
		case  Min:tempOp = "min";
		break;
		case  Max:tempOp = "max";
		break;
		case  Comp:tempOp = "comp";
		break;
		case  None:tempOp = "none";
		break;
		default: tempOp = "Error";
		}
		return tempOp;
	}


	/**
	 * This is a boolean function that compare between 2 ComplexFunction,
	 * if they are equal the function return true, else she return false. 
	 * @param - obj is an Object
	 */
	public boolean equals(Object obj) {

		boolean match = true;

		if(obj instanceof function) {

			function temp = (function)obj;
			double[] arr = new double[100];

			for(int i=0;i<arr.length;i++){
				double randNumber = ThreadLocalRandom.current().nextDouble(-1, 1);
				arr[i]= (double)Math.round(randNumber * 1000d)/1d;
			}
			int counter = 0;
			while(counter<100 && match) {
				if(this.f(arr[counter]) == temp.f(arr[counter]))counter++;
				else match = false ;	
			}
		}
		else match = false;

		return match;	
	}


	// *************************************************************
	// ****************** Private Methods and Data *****************
	// *************************************************************


	/**
	 * This private function calculating function value at point x for a giving Node temp.
	 * @param temp - requested Node
	 * @param x - double 
	 * @return sum - stabilizes the function value at point x for the specific Node temp.
	 */
	private double calcF(Node temp,double x) {

		double sum = 0;

		if(temp.left!=null && temp.right==null) {
			sum = temp.left.f(x);
		}
		if(temp.left==null && temp.right!=null) {
			sum = temp.right.f(x);
		}
		if(temp.left!=null && temp.right!=null) {

			double temp1 = temp.left.f(x);
			double temp2 = temp.right.f(x);
			switch(temp.P_op) {

			case Plus: sum = temp1 + temp2;
			break;
			case Times: sum = temp1 * temp2;
			break;
			case Divid: sum = temp1 / temp2;
			break;
			case Min :if(temp1 > temp2) sum = temp2;
			else sum = temp1;
			break;
			case Max: if(temp1 > temp2) sum = temp1;
			else sum = temp2;
			break;
			case Comp: sum = temp.right.f(temp1);
			break;
			default: sum = 0.0;

			}		
		}
		return sum;
	}


	/**
	 * This function return an iterator that represent: this.PolynomList.iterator();
	 * @return Iterator
	 */
	private Iterator<ComplexFunction.Node> iteretor() {
		return this.PolynomList.iterator();
	}


	/**
	 * This function gets a string and returns it without spaces 
	 * for example:
	 * input : "3x^2 + 6x^3"
	 * output: "3x^2+6x^3"
	 * @param s - a String that represent a ComplexFunction.
	 * @return s - a String without spaces that represent a ComplexFunction.
	 */
	private String clear_spaces (String s) {
		s=s.replaceAll(" ","");
		return s;
	}


	/**
	 * This function returns an index of the first character ')'
	 * @param s - a String that represent a ComplexFunction.
	 * @return index of the first character ')'
	 */
	private int Close_Index (String s){
		return s.indexOf(')');
	}


	/**
	 * This function returns an index of the open character '(' 
	 * that belong to the close character ')' in index "index" from the input.
	 * @param index - the index of the first close character ')'.
	 * @param str - a String that represent a ComplexFunction.
	 * @return Open_Index - index of the open character '('.
	 */
	private int Open_Index (int index, String str){

		int Open_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)=='('){

				Open_Index=i;
				break;

			}
		}
		return Open_Index;
	}


	/**
	 * This function returns an index of the character ',' 
	 * that belong to the close character ')' in index "index" from the input.
	 * @param index - the index of the first close character ')'.
	 * @param str - a String that represent a ComplexFunction.
	 * @return Open_Index - index of the character ','
	 */
	private int comma_Index (int index, String str){

		int comma_Index=0;
		for (int i=index;i>0;i--){

			if (str.charAt(i)==','){
				comma_Index=i;
				break;
			}
		}

		return comma_Index;
	}


	/**
	 * This function return an operation witch found before the open character '('
	 * in index "index" from the input.
	 * @param Index - index of the open character '(' 
	 * @param str - a String that represent a ComplexFunction.
	 * @return an operation
	 */
	private Operation get_op(int Index, String str){

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
	 * 2: Operator("",function3)
	 * 3: Operator(function4,"")
	 * @param start - the index of '('
	 * @param comma  - the index of ','
	 * @param end - the index of ')'
	 * @param str - a String that represent a ComplexFunction.
	 * @return Case - an Integer that represent a case
	 */
	private int Check_case(int start,int comma,int end, String str){

		String temp = str;
		int Case = 1;

		String s1 = str.substring(start+1, comma);
		String s2 = temp.substring(comma+1, end);

		if(s1.equals("")) Case=2;
		if(s2.equals("")) Case=3;
		if(s1.equals("") && s2.equals("")) Case=4;


		return Case;
	}


	/**
	 * This function send the String s to 2 functions in order
	 * to check if the String is equal.
	 * @param s - a String that represent a ComplexFunction.
	 * @return flag - true, if the string is legal.
	 * 		   flag - false, if the string is illegal.
	 */
	private boolean CheckString (String s){

		boolean flag = true;
		if (Checkcomma(s)!=true) flag = false;
		if (CheckOperators(s)!=true) flag = false;
		return flag;
	}


	/**
	 * This function help us check if the number of the next characters: "("    ","     ")"
	 * is equal in order to determinate if the String is legal.
	 * @param s - a String that represent a ComplexFunction .
	 * @return flag - true, if the string is legal.
	 * 		   flag - false, if the string is illegal.
	 */
	private boolean Checkcomma (String s){

		boolean flag = true;
		int open_counter=0,close_counter=0,comma_counter=0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i)=='(') open_counter++;
			if (s.charAt(i)==')') close_counter++;
			if (s.charAt(i)==',') comma_counter++;
		}
		if (open_counter!=close_counter || open_counter!=comma_counter || close_counter != comma_counter) flag =false;
		if (open_counter==0 && comma_counter==0 && close_counter==0) flag =false;

		return flag;
	}


	/**
	 * This function receiving a String s in order to check
	 * if behind each character "(" there is legal Operation. 
	 * @param s - a String that represent a ComplexFunction 
	 * @return flag - true. if the string is legal
	 * 		   flag - false. if the string is illegal
	 */
	private boolean CheckOperators (String s){

		int index=0;
		Operation Op = Operation.None;
		boolean flag= true;

		for (int i=0; i<s.length() && flag ;i++){
			if (s.charAt(i) == '(') {
				index = i;
				Op = get_op(index, s);
				if (Op == Operation.None){
					flag =false;
					throw new RuntimeException("You have entered an invaild Operation input, please fix Operation None in the string.");
				}
			}
		}
		return flag;
	}


	/**
	 * This function set the operation op from a given string.
	 * @param str - a String that represent a ComplexFunction.
	 */
	private Operation get_operator(String str){ 

		Operation tempOp ;

		switch(str) {
		case "plus": tempOp = Operation.Plus;
		break;
		case "mul": tempOp = Operation.Times;
		break;
		case  "div": tempOp = Operation.Divid;
		break;
		case "min" :tempOp = Operation.Min;
		break;
		case "max":tempOp = Operation.Max;
		break;
		case "comp":tempOp = Operation.Comp;
		break;
		case "none":tempOp = Operation.None;
		break;
		default: tempOp = Operation.Error;
		}
		return tempOp;
	}


	/**
	 * This is a function return the length of a specific Operation.
	 * @param Index - the index that represent the character "("
	 * @param str - The string from InitFromString
	 * @return ans - Integer that represent the operation length
	 */
	private int get_op_length(int Index, String str){

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


	/**
	 * This function return an ArrayList<Node> that contain deep copy of cf.PolynomList.
	 * @param cf - ComplexFunction which we want to copy.
	 * @return copy - PolynomList that contain cf ArrayList<Node>.
	 */
	private ArrayList<Node> ArrayListCopy(ComplexFunction cf){

		ArrayList<Node> copy =  new ArrayList<Node>();
		Node temp = new Node();
		Iterator<Node> iter = cf.iteretor();

		while(iter.hasNext()) {
			temp=iter.next().copy();
			copy.add(temp);
		}
		return copy;
	}


	/**
	 * This function return an ArrayList<Node> that contain 2 ArrayLists,
	 * list1.PolynomList and list2.PolynomList and between them we insert Operation op.
	 * @param list1 - an ArrayList<Node>
	 * @param op - Operation op
	 * @param list2 - an ArrayList<Node>
	 * @return copy - ArrayList<Node> that contain list1.PolynomList and list2.PolynomList.
	 */
	private ArrayList<Node> CombineLists(ArrayList<Node> list1,Operation op, ArrayList<Node> list2){

		ArrayList<Node> ans = new ArrayList<Node>();
		Iterator <Node> iter1 = list1.iterator();
		Node temp = new Node();

		while (iter1.hasNext()) {
			temp = iter1.next();
			ans.add(temp);
		}

		ans.get(ans.size()-1).setG_op(op);
		Iterator <Node> iter2 = list2.iterator();
		temp = new Node();

		while (iter2.hasNext()) {
			temp = iter2.next();
			ans.add(temp);
		}

		return ans;
	}


}