package co.com.techandsolve.lazy.collector;

import java.util.ArrayList;
import java.util.List;

public class Creator {
	
	private boolean firstElement;
	private Integer numberOfElements;
	private List<List<Integer>> elements;
	private List<Integer> subelements;
	
	public Creator(){
		elements = new ArrayList<List<Integer>>();
		reset();
	}
	
	public void init(){
		elements.add(new ArrayList<Integer>(subelements));
		reset();
	}

	private void reset() {
		firstElement = true;
		subelements = new ArrayList<Integer>();
		numberOfElements = 0;
	}

	public boolean isFirstElement() {
		return firstElement;
	}

	public void setFirstElement(boolean firstElement) {
		this.firstElement = firstElement;
	}

	public void setNumberOfElements(Integer item) {
		numberOfElements = item;
	}
	
	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void add(Integer item) {
		subelements.add(item);
	}

	public Integer size() {
		return subelements.size();
	}

	public List<List<Integer>> getElements() {
		return elements;
	}
	
}
