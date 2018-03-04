package com.javacodegeeks.examples.concurrenthashmap.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plane implements Vehicle{
	private int plates;
	private boolean inprogress;

	public Plane() {
		Integer[] a = new Integer[] {1, 2, 3};
		List<Integer> r = Arrays.asList(a);
		List<Integer> t = Plane.asList(a);
		List<Integer> l = new ArrayList<Integer>(a);
	}
	
	
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);
    }

	public Plane(int plates) {
		this.plates = plates;
	}

	public int getPlates() {
		return plates;
	}

	public void setPlates(int plates) {
		this.plates = plates;
	}

	public boolean isInprogress() {
		return inprogress;
	}

	public void setInprogress(boolean inprogress) {
		this.inprogress = inprogress;
	}

	// It is VERY IMPORTANT to implement hasCode() and equals() on classes
	// that will be "stored" in a HashMap
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + plates;
		result = prime * result + (inprogress ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		if (plates != other.plates)
			return false;
		if (inprogress != other.inprogress)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Truck [plates=" + plates + "]";
	}
}
