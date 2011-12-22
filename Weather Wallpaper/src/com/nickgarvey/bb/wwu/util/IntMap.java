package com.nickgarvey.bb.wwu.util;

import net.rim.device.api.collection.ReadableIntMap;
import net.rim.device.api.collection.WritableIntMap;
import net.rim.device.api.util.Persistable;

/**
 * IntMap is used to act as a map for integer based keys. The memory usages
 * grows linearly on the size of the largest key, so large valued keys should
 * not be used with this map.
 * 
 * @author ngarvey
 * 
 */
public class IntMap implements ReadableIntMap, WritableIntMap, Persistable {
	
	Object[] arrayBackedMap;

	private final double GROWTH_FACTOR = 1.5;
	
	/**
	 * Creates an IntMap with an initial size of <code>initialSize</code>
	 * 
	 * @param initialSize Initial size of the backing array
	 */
	public IntMap(int initialSize) {
		arrayBackedMap = new Object[initialSize];
	}
	
	/**
	 * Creates an IntMap with an initial size of 10
	 */
	public IntMap() {
		this(10);
	}

	public void put(int key, Object value) {		

		if (key > arrayBackedMap.length - 1) {

			Object[] newBackedMap = new Object[(int) (key * GROWTH_FACTOR)];

			System.arraycopy(arrayBackedMap, 0, newBackedMap, 0,
					arrayBackedMap.length);

			arrayBackedMap = newBackedMap;

		}
		
		arrayBackedMap[key] = value;

	}

	public void remove(int key) {

		if (key < arrayBackedMap.length) {
			arrayBackedMap[key] = null;
		}
		
	}

	public void removeAll() {
		arrayBackedMap = new Object[arrayBackedMap.length];
	}

	public boolean contains(int key) {

		return key > arrayBackedMap.length - 1 &&
			arrayBackedMap[key] != null;

	}

	public Object get(int key) {

		if (key > arrayBackedMap.length - 1) {
			return null;
		}
		return arrayBackedMap[key];

	}

	public int getKey(Object element) {

		if (element != null) {
			for (int i = 0; i < arrayBackedMap.length; i++) {
				if (element.equals(arrayBackedMap[i])) {
					return i;
				}
			}
		}

		return -1;
	
	}

	/**
	 * This function is linear with the size of largest key.
	 */
	public int size() {
		
		int size = 0;
		
		for (int i = 0; i < arrayBackedMap.length; i++) {
			if (arrayBackedMap[i] != null) {
				size++;
			}
		}
		
		return size;
		
	}
	
	public boolean equals(Object o) {

		if (o == null || !(o instanceof IntMap)) 
			return false;
		
		IntMap testSubj = (IntMap)o;
		
		if (testSubj.size() == this.size()) {
			
			for (int i = 0; i < arrayBackedMap.length; i++) {
				if (this.get(i) != testSubj.get(i)) {
					return false;
				}
			}
			
			return true;
			
		}
		
		return false;
		
	}

}
