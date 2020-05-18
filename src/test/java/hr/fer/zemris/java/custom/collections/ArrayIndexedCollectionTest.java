package hr.fer.zemris.java.custom.collections;

import org.junit.Assert;
import org.junit.Test;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;

public class ArrayIndexedCollectionTest {
	
	@Test 
	public void forGettingElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		Object obtainedObject = collection.get(1);
		Assert.assertEquals(i2, obtainedObject);
	}
	
	@Test 
	public void forContainedElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		boolean condition = collection.contains(i2);
		Assert.assertTrue(condition);
	}
	
	@Test 
	public void forNotContainedElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		boolean condition = collection.contains(Integer.valueOf(10));
		Assert.assertTrue(!condition);
	}
	
	@Test
	public void forGettingIndexOfElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		int expectedIndex = 1;
		int actualIndex = collection.indexOf(i2);
		Assert.assertEquals(expectedIndex, actualIndex);
	}
	
	@Test
	public void forGettingIndexOfNotContainedElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		int expectedIndex = -1;
		int actualIndex = collection.indexOf(Integer.valueOf(20));
		Assert.assertEquals(expectedIndex, actualIndex);
	}
	
	@Test 
	public void forClearingCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		collection.clear();
		Assert.assertTrue(collection.isEmpty());
	}
	
	@Test
	public void forInsertingElements() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		
		int expectedIndex = 1;
		int actualIndex = collection.indexOf(i3);
		Assert.assertEquals(expectedIndex, actualIndex);
	}
	
	@Test
	public void forSizeOfCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		Integer i4 = Integer.valueOf(-1);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		collection.add(i4);
		collection.remove(0);
		
		int expected = 3;
		int actual = collection.size();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void forRemovingElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		collection.remove(1);
		
		boolean condition = collection.contains(i3);
		Assert.assertTrue(!condition);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void forGettingElementAtInvalidIndex() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		int invalidIndex = 3;
		collection.get(invalidIndex);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void forRemovingNullElement() {
		Integer i1 = Integer.valueOf(1);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.remove(null);
	}
	
	@Test
	public void forAddingElementsFromAnotherCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection1 = new ArrayIndexedCollection();
		ArrayIndexedCollection collection2 = new ArrayIndexedCollection();
		collection1.add(i1);
		collection1.add(i2);
		collection2.addAll(collection1);
		
		boolean condition = collection2.contains(i2);
		Assert.assertTrue(condition);
	}
	
	@Test
	public void forGettingArrayfromCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(i1);
		collection.add(i2);	
		
		Object[] array = collection.toArray();
		int arraySize = array.length;
		int collectionSize = collection.size();
		boolean isSizeEquals = arraySize == collectionSize;
		Assert.assertTrue(isSizeEquals);
	}
	
}
