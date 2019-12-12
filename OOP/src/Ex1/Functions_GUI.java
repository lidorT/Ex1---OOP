package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javafx.scene.text.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.util.function.Function;

	public class Functions_GUI implements functions{

	ArrayList<function> GuiList = new ArrayList<function>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};


	public boolean add(function e) {
		if(GuiList.contains(e))
			return false;
		else {
			GuiList.add(e);
			return true;
		}
	}


	public boolean addAll(Collection<? extends function> c) {
		return GuiList.addAll(c);
	}


	public void clear() {
		this.GuiList.clear();
	}


	public boolean contains(Object o) {
		return GuiList.contains(o);
	}


	public boolean containsAll(Collection<?> c) {
		return GuiList.containsAll(c);
	}


	public boolean isEmpty() {
		return GuiList.isEmpty();
	}


	public Iterator<function> iterator() {
		Iterator<function> itr = GuiList.iterator();
		return itr;
	}


	public boolean remove(Object o) {
		return GuiList.remove(o);
	}


	public boolean removeAll(Collection<?> c) {
		return GuiList.removeAll(c);
	}


	public boolean retainAll(Collection<?> c) {
		return GuiList.retainAll(c);
	}


	public int size() {
		return GuiList.size();
	}


	public Object[] toArray() {
		return GuiList.toArray();
	}


	public <T> T[] toArray(T[] a) {
		return GuiList.toArray(a);
	}


	public void initFromFile(String s) throws IOException {
		
		File temp = new File(s);
		BufferedReader buffer = new BufferedReader(new FileReader(temp)); 

		String str; 
		while ((str = buffer.readLine()) != null) {
			try {
				this.add(new Polynom(str));
			}
			catch (Exception e) {
				this.add(new ComplexFunction(str));
			}
		}
	}


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


	public void drawFunctions(int width, int height, Range xRange, Range yRange, int resolution) {
		
		int colorIndex = 0;
		StdDraw.setCanvasSize( width, height);
		StdDraw.setXscale(xRange.get_min(), xRange.get_max());
		StdDraw.setYscale(yRange.get_min(), yRange.get_max());
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
	
	
	private void setGrid(Range xRange, Range yRange) {
		
		for(double i = xRange.get_max();i >= xRange.get_min();i--) {
			
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			
			if(i == 0) {
				
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			
			StdDraw.line(i, yRange.get_min(), i , yRange.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}
		
		for(double i = yRange.get_max();i >= yRange.get_min();i--) {
			
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			
			if(i == 0) {
				
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			
			StdDraw.line(xRange.get_min(),i,xRange.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		
	}
	
	


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
						String var = line.substring(colon+1, comma);
						variable[0] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Height") && variable[1] == -1) {
						String var = line.substring(colon+1, comma);
						variable[1] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Resolution") && variable[2] == -1) {
						String var = line.substring(colon+1, comma);
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

	public ComplexFunction get(int i) {
		return (ComplexFunction) GuiList.get(i);
	}


}



