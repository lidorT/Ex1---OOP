package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;

	/**
	 * This class implements functions interface, this class main goal is to create a class that will be able to create a plot of the Compelex
	 * Functions,Polynoms and Monoms.
	 * This plot could be initialized from a Code or from a text file.
	 * @author Lidor_t and Zohar_m
	 */
	public class Functions_GUI implements functions{
		
	/**
	 * ArrayList of functions.
	 */
	ArrayList<function> GuiList = new ArrayList<function>(); 
	
	/**
	 * Array of Colors
	 */
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	/**
	 * This functions simply checks if a given function is already
	 * exist in the array list if it so return false else add her to the array list and return true.
	 */
	public boolean add(function e) {
		if(GuiList.contains(e))
			return false;
		else {
			GuiList.add(e);
			return true;
		}
	}

	/**
	 * Simply adds all the collection functions into the array list using the add all java utill command.
	 */
	public boolean addAll(Collection<? extends function> c) {
		return GuiList.addAll(c);
	}

	/**
	 * Simply do the .clear command which will erase all the data inside the array list.
	 */
	public void clear() {
		this.GuiList.clear();
	}

	/**
	 * simply return the arrayList.contains(O) (java utill command ) 
	 * in order to check if the array list contains the Object O or not , return true if O is in the list else false.
	 */
	public boolean contains(Object o) {
		return GuiList.contains(o);
	}

	/**
	 * Same as the above function (contains) the only different is that here we checks about a full collection of functions.
	 */
	public boolean containsAll(Collection<?> c) {
		return GuiList.containsAll(c);
	}

	/**
	 * check if the array list is empty with the isEmpty of java utill command ture if it empty else false.
	 */
	public boolean isEmpty() {
		return GuiList.isEmpty();
	}

	/**
	 * Creates an iterator for the Array List.
	 */
	public Iterator<function> iterator() {
		Iterator<function> itr = GuiList.iterator();
		return itr;
	}

	/**
	 * Remove an object from the arrayList.
	 */
	public boolean remove(Object o) {
		return GuiList.remove(o);
	}

	/**
	 * removes all objects inside the collection from the array List.
	 */
	public boolean removeAll(Collection<?> c) {
		return GuiList.removeAll(c);
	}

	/**
	 * retains only the elements in this list that are contained in the specified collection.
	 * In other words, removes from this list all of its elements that are not contained 
	 * in the specified collection.
	 */
	public boolean retainAll(Collection<?> c) {
		return GuiList.retainAll(c);
	}

	/**
	 * Returns this Arrray list size. 
	 */
	public int size() {
		return GuiList.size();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence (from first to last element). 
	 * The returned array will be "safe" in that no references to it are maintained by this list. (In other words,
	 * this method must a locate a new array). The caller is thus free to modify the returned array.
	 */
	public Object[] toArray() {
		return GuiList.toArray();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence (from first to last element) 
	 * The run time type of the returned array is that of the specified array. 
	 * If the list fits in the specified array, it is returned therein. 
	 * Otherwise, a new array is a located with the run time type of the specified array and the size of this list. 
	 * If the list fits in the specified array with room to spare(i.e., the array has more elements than the list),
	 * the element in the array immediately following the end of the collection is set to null. 
	 */
	public <T> T[] toArray(T[] a) {
		return GuiList.toArray(a);
	}

	/**
	 * This function receives an Integer i and return ComplexFunction that represent this.GuiList.get(i)
	 * @param i - an Integer that represent an index of Node in GuiList 
	 * @return - the function return ComplexFunction that represent GuiList.get(i)
	 */
	public ComplexFunction get(int i) {
		return (ComplexFunction) this.GuiList.get(i);
	}
	
	
	/**
	 * This function is saving the string values into a file, in order to do so we created a StringBuilder named strBuilder, 
	 * after that we created a function iterator and running on all the functions each time we find a function we area appending
	 * the functions.toString after that we are using append to "\n" to get a line between each functions we used try and catch
	 * and there we created a new PrintWriter and there we try to write using the string builder we made if we succeed then all
	 * good if catch appear we print stack trace.
	 */
	public void saveToFile(String file) throws IOException {
		
		StringBuilder stringB = new StringBuilder();
		Iterator<function> iter = this.GuiList.iterator();
		
		
		while(iter.hasNext()) {
			
			function f = iter.next();
			stringB.append(f.toString());
			if(iter.hasNext()) {
				stringB.append("\n");
			}	
		}
		
		try{
			
			PrintWriter printW = new PrintWriter(new File(file));
			printW.write(stringB.toString());
			printW.close();
		} 
		
		catch (FileNotFoundException e){
			
			e.printStackTrace();
			return;
		}
		
	}
	
	
	/**
	 * this functions creates a text file and saves the functions within "this" array list into the text file. 
	 * First we create an iterator to run all over the array then we create a StringBuilder that checks each 
	 * object in the array list convert it to string and writes it inside the file if it can't find the file itself 
	 * we returns it trace in order to locate him.
	 */
	public void initFromFile(String s) throws IOException {
		
		File temp = new File(s);
		BufferedReader buffer = new BufferedReader(new FileReader(temp)); 
		String str; 
		
		while ((str = buffer.readLine()) != null) {
			try {
				this.add(new Polynom(str));
			}
			catch (Exception e) {this.add(new ComplexFunction(str));}
		}
	}


	/**
	 * This function draw the functions on the plot we get as input the width height the x and y lines ranges and the resolution. 
	 * First set the canvas size with the "setCanvasSize" command of java and place as inputs there the width and height we got. 
	 * After that we set x and y scales with the use of the command setCanvasSize and our inputs are the xRangeand yRange ranges ,
	 * we insert xRange/yRange.
	 * get_Min and xRange/yRange.get Max functions, after that we set the Grid with xRange and yRange. 
	 * After that we set the pen Radius to 0.004 we set a double parameter named step to be xRange.get_max()-xRange.get_min())/resolution 
	 * this step goal is to create dots each time in order to get a graph the iterator will run after that and pull the color from the array 
	 * of the colors and set the pen color command after that comes a loop in order to draw each line, save the jpg and we are done.
	 */
	public void drawFunctions(int width,int height,Range xRange,Range yRange,int resolution) {
		
		int colorIndex = 0;
		StdDraw.setCanvasSize( width, height);
		StdDraw.setXscale(xRange.get_min(),xRange.get_max());
		StdDraw.setYscale(yRange.get_min(),yRange.get_max());
		setGrid(xRange, yRange);
		StdDraw.setPenRadius(0.005);
		double step = (xRange.get_max()-xRange.get_min())/resolution;
		Iterator<function> itr = GuiList.iterator();
		
		while(itr.hasNext()) {	
			
			function func = itr.next();
			if(colorIndex == Colors.length)colorIndex = 0;
			
			StdDraw.setPenColor(Colors[colorIndex]);
			colorIndex++;
			
			for(double index = xRange.get_min()+step;index<xRange.get_max();index += step) {
				
				double x0 = index-step;
				double y0 = func.f(x0);
				double x1 = index;
				double y1 = func.f(x1);
				StdDraw.line(x0, y0, x1, y1);
			}
		}
		StdDraw.save("Functions_GUI.jpg");
	}
	
	
	/**
	 * This function goal is to set the grids of the plot. 
	 * We gets as an input the x range and the y ranges. 
	 * First we create a loop to run on the y axis therefore we start the loop at yRange.Max  till we get to yRange.Max.Min , 
	 * each run i will be -- we set the pen color and radius after that we do the same for the x axis.
	 * @param xRange
	 * @param yRange
	 */
	private void setGrid(Range xRange, Range yRange) {
		
		for(double i = xRange.get_max();i >= xRange.get_min();i--) {
			
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.001);
			
			if(i == 0) {
				
				StdDraw.setPenColor(Color.gray);
				StdDraw.setPenRadius(0.003);
			}
			
			StdDraw.line(i,yRange.get_min(),i,yRange.get_max());
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}
		
		for(double i = yRange.get_max();i >= yRange.get_min();i--) {
			
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.001);
			
			if(i == 0) {
				
				StdDraw.setPenColor(Color.gray);
				StdDraw.setPenRadius(0.003);
			}
			
			StdDraw.line(xRange.get_min(),i,xRange.get_max(), i);
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		
	}
	
	

	/**
	 * This function goal is to is to read from the json file the require parameters in order to send drawFunctions , 
	 * we set parameters for column comma '[' and ']' we search inside the file for strings such as "width" "height" "Resolution" 
	 * and ranges of x and y after having this set the right parameters to the values from the file and sends it back to drawFunctions function
	 */
	public void drawFunctions(String json_file) {

		int i = 0;
		int[] variable = {-1,-1,-1,-1,-1,-1,-1};
		String line = "";

		try {
			
			BufferedReader buffer = new BufferedReader(new FileReader(json_file));
			
			while((line = buffer.readLine()) != null && i <= 5) {
				
				if(line.indexOf('{') == -1 && line.indexOf('}') == -1) {
					
					int openRange = line.indexOf('[');
					int closeRange = line.indexOf(']');
					int colon = line.indexOf(':');
					int comma = line.indexOf(',');
					
					if(line.contains("Width") && variable[0] == -1) {
						String var = line.substring(colon+1,comma);
						variable[0] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Height") && variable[1] == -1) {
						String var = line.substring(colon+1,comma);
						variable[1] = Integer.parseInt(var);
						i++;
					}
				
					
					else if(line.contains("Resolution") && variable[2] == -1) {
						String var = line.substring(colon+1,comma);
						variable[2] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Range_X")) {
						String varLeft = line.substring(openRange+1,comma);
						variable[3] = Integer.parseInt(varLeft);
						i++;
						String varRight = line.substring(comma+1,closeRange);
						variable[4] = Integer.parseInt(varRight);
						i++;
					}
					
					else if( line.contains("Range_Y")) {
						String varLeft = line.substring(openRange+1,comma);
						variable[5] = Integer.parseInt(varLeft);
						i++;
						String varRight = line.substring(comma+1,closeRange);
						variable[6] = Integer.parseInt(varRight);
						i++;
					}
					
					else {
						throw new IllegalArgumentException("one of the lines in the json file is different from the given lines,please fix it.");
					}
				}
			}
		}	
		 catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
		}
		
		Range rx = new Range(variable[3], variable[4]);
		Range ry = new Range(variable[5], variable[6]);
		drawFunctions(variable[0], variable[1], rx, ry, variable[2]);
	}


}



