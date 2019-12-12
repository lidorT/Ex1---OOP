package myMath;

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


	public void initFromFile(String str) throws IOException {
		
		File file = new File(str);
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String s; 
		while ((s = br.readLine()) != null) {
			try {
				this.add(new Polynom(s));
			}
			catch (Exception e) {
				this.add(new ComplexFunction(s));
			}
		}
	}


	public void saveToFile(String file) throws IOException {
		
		Iterator<function> iter = this.GuiList.iterator();
		StringBuilder s_builder = new StringBuilder();
		
		while(iter.hasNext()) {
			function f = iter.next();
			s_builder.append(f.toString());
			if(iter.hasNext()) {
				s_builder.append("\n");
			}	
		}
		try{
			PrintWriter pw = new PrintWriter(new File(file));
			pw.write(s_builder.toString());
			pw.close();
		} 
		catch (FileNotFoundException e){
			e.printStackTrace();
			return;
		}
		
	}


	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		
		int indexColor = 0;
		StdDraw.setCanvasSize( width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		setGrid(rx, ry);
		StdDraw.setPenRadius(0.005);
		double step = (rx.get_max()-rx.get_min())/resolution;
		Iterator<function> itr = GuiList.iterator();
		while(itr.hasNext()) {	
			function func = itr.next();
			if(indexColor==Colors.length)
				indexColor = 0;
			StdDraw.setPenColor(Colors[indexColor]);
			indexColor++;
			for(double index = rx.get_min()+step; index<rx.get_max(); index+=step) {
				double x1 = index-step;
				double y1 = func.f(x1);
				double x2 = index;
				double y2 = func.f(x2);
				StdDraw.line(x1, y1, x2, y2);
			}
		}
		StdDraw.save("Functions_GUI.jpg");
	}
	
	
	private void setGrid(Range rx, Range ry) {
		
		for(double i = ry.get_max(); i>=ry.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(-0.3, i, (int)i+"");
		}
		for(double i = rx.get_max(); i>=rx.get_min(); i--) {
			StdDraw.setPenColor(Color.gray);
			StdDraw.setPenRadius(0.001);
			if(i==0) {
				StdDraw.setPenColor(Color.black);
				StdDraw.setPenRadius(0.003);
			}
			StdDraw.line(i, ry.get_min(), i , ry.get_max());
			StdDraw.setPenColor(Color.black);
			StdDraw.setPenRadius(0.005);
			StdDraw.text(i, -0.35, (int)i+"");
		}
	}
	
	


	public void drawFunctions(String json_file) {

		String line="";
		int i=0;
		int[] variable = {-1,-1,-1,-1,-1,-1,-1};
		
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(json_file));
			
			while((line = reader.readLine())!= null && i<=5) {
				
				if(line.indexOf('{')==-1 && line.indexOf('}')==-1) {
					int indexOfColon = line.indexOf(':');
					int indexOfCumma = line.indexOf(',');
					int indexOfRange = line.indexOf('[');
					int indexOfRangeEnd = line.indexOf(']');
					
					if(line.contains("Width") && variable[0]==-1) {
						String var = line.substring(indexOfColon+1, indexOfCumma);
						variable[0] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Height") && variable[1]==-1) {
						String var = line.substring(indexOfColon+1, indexOfCumma);
						variable[1] = Integer.parseInt(var);
						i++;
					}
					
					else if(line.contains("Resolution") && variable[2]==-1) {
						String var = line.substring(indexOfColon+1, indexOfCumma);
						variable[2] = Integer.parseInt(var);
						i++;
					}
				
					else if(line.contains("Range_X")) {
						String varLeft = line.substring(indexOfRange+1, indexOfCumma);
						variable[3] = Integer.parseInt(varLeft);
						i++;
						String varRight = line.substring(indexOfCumma+1, indexOfRangeEnd);
						variable[4] = Integer.parseInt(varRight);
						i++;
					}
					
					else if( line.contains("Range_Y")) {
						String varLeft = line.substring(indexOfRange+1, indexOfCumma);
						variable[5] = Integer.parseInt(varLeft);
						i++;
						String varRight = line.substring(indexOfCumma+1, indexOfRangeEnd);
						variable[6] = Integer.parseInt(varRight);
						i++;
					}
					
					else {
						throw new IllegalArgumentException("one of the lines in the json is not like in the given format");
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



