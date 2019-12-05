package myMath;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Functions_GUI implements functions{

	ArrayList<function> GuiList = new ArrayList<function>();




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
		//if(o instanceof function) {
		return GuiList.contains(o);
		//}
		//else return false;
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


	public void initFromFile(String file) throws IOException {

	}


	public void saveToFile(String file) throws IOException {
		
	}


	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		
	}
	
	
	private void setGrid(Range rx, Range ry) {
	

	}
	
	


	public void drawFunctions(String json_file) {


	}


	public ComplexFunction get(int i) {
		return (ComplexFunction) GuiList.get(i);
	}

}



