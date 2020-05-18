package hr.fer.zemris.java.custom.collections;

import org.junit.Assert;
import org.junit.Test;

import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;

public class LinkedListIndexedCollectionTest {
	
	
	@Test 
	public void forGettingElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		Object obtainedObject = collection.get(1);
		Assert.assertEquals(i2, obtainedObject);
	}
	
	@Test 
	public void forContainedElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		boolean condition = collection.contains(i2);
		Assert.assertTrue(condition);
	}
	
	@Test 
	public void forNotContainedElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		boolean condition = collection.contains(Integer.valueOf(10));
		Assert.assertTrue(!condition);
	}
	
	@Test
	public void forGettingIndexOfElement() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
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
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
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
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		
		int expectedIndex = 2;
		int actualIndex = collection.indexOf(i2);
		Assert.assertEquals(expectedIndex, actualIndex);
	}
	
	@Test
	public void forSizeOfCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		Integer i4 = Integer.valueOf(-1);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 2);
		collection.add(i4);
		collection.remove(0);
		
		int expected = 3;
		int actual = collection.size();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void forRemovingElementByIndex() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		Integer i4 = Integer.valueOf(11);
		Integer i5 = Integer.valueOf(12);
		Integer i6 = Integer.valueOf(13);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		collection.add(i4);
		collection.insert(i6, 0);
		collection.add(i5);
		collection.remove(0);
		
		boolean condition = collection.contains(i6);
		Assert.assertTrue(!condition);
	}
	
	@Test
	public void forRemovingElementByReference() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		Integer i3 = Integer.valueOf(10);
		Integer i4 = Integer.valueOf(11);
		Integer i5 = Integer.valueOf(12);
		Integer i6 = Integer.valueOf(13);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		collection.insert(i3, 1);
		collection.add(i4);
		collection.insert(i6, 3);
		collection.add(i5);
		collection.remove(i2);
		
		boolean condition = collection.contains(i2);
		Assert.assertTrue(!condition);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void forGettingElementAtInvalidIndex() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);
		
		int invalidIndex = 3;
		collection.get(invalidIndex);
	}
	
	@Test
	public void forRemovingNullElement() {
		Integer i1 = Integer.valueOf(1);
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		boolean isRemoved = collection.remove(null);
		Assert.assertTrue(!isRemoved);
	}
	
	@Test
	public void forAddingElementsFromAnotherCollection() {
		Integer i1 = Integer.valueOf(1);
		Integer i2 = Integer.valueOf(2);
		LinkedListIndexedCollection collection1 = new LinkedListIndexedCollection();
		LinkedListIndexedCollection collection2 = new LinkedListIndexedCollection();
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
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(i1);
		collection.add(i2);	
		
		Object[] array = collection.toArray();
		int arraySize = array.length;
		int collectionSize = collection.size();
		boolean isSizeEquals = arraySize == collectionSize;
		Assert.assertTrue(isSizeEquals);
	}
	
	//////////////////////////////////////////////////////////////////////
	
	@Test
	public void addAndContainsTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();

		Assert.assertEquals(0, collection.size());
		Assert.assertTrue(collection.isEmpty());

		collection.add(5);
		collection.add(4.4);
		collection.add("Koza");
		collection.add('C');

		Assert.assertEquals(4, collection.size());
		Assert.assertTrue(collection.contains(5));
		Assert.assertTrue(collection.contains('C'));
		Assert.assertFalse(collection.contains("Ovca"));
		Assert.assertFalse(collection.contains(null));
	}

	@Test(expected = NullPointerException.class)
	public void addNullTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(null);
	}

	@Test
	public void getTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(1);
		collection.add(-3.14);
		collection.add("Java");
		collection.add(5);
		collection.add(42);

		Assert.assertEquals(1, collection.get(0));
		Assert.assertEquals(-3.14, collection.get(1));
		Assert.assertEquals("Java", collection.get(2));
		Assert.assertEquals(42, collection.get(4));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void illegalGetTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.get(0);
	}

	@Test
	public void clearTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(-1);
		collection.add("Petak");
		collection.add(0.2f);

		Assert.assertEquals(3, collection.size());
		collection.clear();
		Assert.assertEquals(0, collection.size());
	}

	@Test
	public void insertTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(1);
		collection.add(3);
		collection.add(5);

		collection.insert(0, 0);
		collection.insert(2, 2);
		collection.insert(4, 4);
		collection.insert(6, 6);

		Assert.assertEquals(7, collection.size());
		Assert.assertEquals(0, collection.get(0));
		Assert.assertEquals(2, collection.get(2));
		Assert.assertEquals(5, collection.get(5));
		Assert.assertEquals(1, collection.indexOf(1));
		Assert.assertEquals(6, collection.indexOf(6));
		Assert.assertEquals(4, collection.indexOf(4));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void illegalInsertTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.insert(0, -4);
	}

	@Test
	public void indexOfTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(245);

		Assert.assertEquals(0, collection.indexOf(245));
		Assert.assertEquals(-1, collection.indexOf(null));
		Assert.assertEquals(-1, collection.indexOf(-4));
	}

	@Test
	public void removeIndexTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("bombon");
		collection.add('M');
		collection.add(7);
		collection.add(7);

		collection.remove(0);
		collection.remove(1);
		collection.remove(1);

		Assert.assertEquals(1, collection.size());
		Assert.assertEquals('M', collection.get(0));
	}

	@Test
	public void removeObjectTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add("0");
		collection.add(0);
		collection.add(7.0);
		collection.add("James");

		collection.remove("James");
		collection.remove("0");

		Assert.assertEquals(2, collection.size());
		Assert.assertEquals(0, collection.indexOf(0));
		Assert.assertEquals(7.0, collection.get(1));
	}

	@Test
	public void toArrayTest() {
		LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
		collection.add(22);
		collection.add("Ljilja");
		collection.add(5.5);

		Object[] objects = collection.toArray();
		Assert.assertEquals(3, objects.length);
		Assert.assertEquals(22, objects[0]);
		Assert.assertEquals(5.5, objects[2]);
	}
	
	@Test
	public void addAllTest() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(5);
		collection.add(42);
		collection.add("štefica");
		
		LinkedListIndexedCollection collection2 = new LinkedListIndexedCollection();
		collection2.addAll(collection);
		
		Assert.assertEquals(3, collection2.size());
		Assert.assertEquals("štefica", collection2.get(2));
	}
	
	@Test
	public void constructorTest() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		collection.add(5);
		collection.add(42);
		collection.add("štefica");
		
		LinkedListIndexedCollection collection2 = new LinkedListIndexedCollection(collection);
		Assert.assertEquals(3, collection2.size());
		Assert.assertEquals(1, collection2.indexOf(42));
	}

}
