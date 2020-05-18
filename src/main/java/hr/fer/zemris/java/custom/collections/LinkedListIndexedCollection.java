package hr.fer.zemris.java.custom.collections;

/**
 * Class represents collection that uses double-linked list to store elements.
 * Duplicate elements are allowed.
 * Storage of <code>null</code> references is not allowed.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 */
public class LinkedListIndexedCollection extends Collection {
	
	/**
	 * number of elements stored in collection
	 */
	private int size;
	/**
	 * reference to the first node of the linked list
	 */
	private ListNode first;
	/**
	 * reference to the last node of the linked list
	 */
	private ListNode last;
	
	/**
	 * Constructor used for creating new <code>LinkedListIndexedCollection</code>
	 */
	public LinkedListIndexedCollection() {
		first = last = null;
		size = 0;
	}
	
	/**
	 * Constructor used for creating new <code>LinkedListIndexedCollection</code>
	 * that will have all elements of given <code>collection</code>
	 * 
	 * @param collection collection whose elements are stored in newly created collection
	 */
	public LinkedListIndexedCollection(Collection collection) {
		this();
		addAll(collection);
	}
	
	/**
	 * Class represents node that can store elements.
	 * It is used for implementing double-linked list with two references to <code>ListNode</code>.
	 * It uses <code>value</code> to store <code>Object</code> elements
	 * 
	 * @author Ante Gazibarić
	 *
	 */
	private static class ListNode {
		private ListNode previous;
		private ListNode next;
		private Object value;
		
		/**
		 * Constructor for creating new object of type <code>ListNode</code>
		 * @param previous reference to <code>ListNode</code>
		 * @param next     reference to <code>ListNode</code>
		 * @param value    it is used for storing object
		 */
		public ListNode(ListNode previous, ListNode next, Object value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
		}
	}
	
	/**
	 * Method checks if collection is empty
	 * 
	 * @return returns <code>true</code> only if the collection contains given value, otherwise returns <code>false</code>
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This method returns size of collection with complexity O(1).
	 * 
	 * @return returns the number of currently stored objects in this collection
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds the given object into this collection at the end of collection.
	 * Newly added element becomes the element at the biggest index.
	 * Complexity of this method is O(1).
	 * 
	 * @param value object that is being added to this collection
	 * @throws      <code>NullPointerException</code> if given value is <code>null</code>
	 */
	public void add(Object value) {
		if(value == null)
			throw new NullPointerException("Value must not be null");
		
		ListNode newNode = new ListNode(last, null, value);
		if(first == null)
			first = newNode;
		if(last != null) 
			last.next = newNode;
		last = newNode;
		size++;
	}

	/**
	 * Method inserts the given <code>value</code> at the given <code>position</code> in linked-list.
	 * Complexity of this method is never greater then n/2 + 1.
	 * 
	 * @param value    object that is added to this collection
	 * @param position index at which the object is added
	 * @throws 		   <code>NullPointerException</code> if the given <code>value</code> is <code>null</code>
	 * @throws 		   <code>IndexOutOfBoundsException</code> if position is not valid
	 */
	public void insert(Object value, int position) {
		if (value == null)
			throw new NullPointerException("Value must not be null");
		if (position < 0 || position > size)
			throw new IndexOutOfBoundsException("You entered: " + position);

		if (position == size) {
			add(value);
		} else if (position == 0) {
			addAtBeginning(value);
		} else {
			ListNode iterator = getListNodeAtIndex(position);
			ListNode newNode = new ListNode(iterator.previous, iterator, value);
			if (iterator.previous != null)
				iterator.previous.next = newNode;
			iterator.previous = newNode;
			size++;
		}
	}
	
	/**
	 * Add value at the beginning of collection
	 * @param value value that is added
	 */
	private void addAtBeginning(Object value) {
		ListNode newNode = new ListNode(null, first, value);
		first = newNode;
		size++;
	}
	
	/**
	 * Returns the <code>Object</code> element that is stored in linked list at position <code>index</code>.
	 * Complexity of this method is never greater then n/2 + 1.
	 * 
	 * @param index index at which object is placed inside list
	 * @return      <code>Object</code> in list at given <code>index</code>
	 * @throws      <code>IndexOutOfBoundsException</code> for invalid <code>index</code>
	 */
	public Object get(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("You entered: " + index);
		
		ListNode iterator = getListNodeAtIndex(index);
		return iterator.value;
	}
	
	/**
	 * Method checks if the collection contains the <code>value</code>.
	 * Average complexity of this method is O(n):
	 * 
	 * @param value the object whose presence in the collection is checked
	 * @return <code>true</code> only if the collection contains given <code>value</code>, otherwise <code>false</code>
	 */
	public boolean contains(Object value) {
		for (ListNode iterator = first; iterator != null; iterator = iterator.next) {
			if (iterator.value.equals(value))
				return true;
		}
		return false;
	}
	
	/**
	 * Method removes the <code>value</code> from the collection if the collection contains that <code>value</code>.
	 * 
	 * @param value the object which is removed from collection
	 * @return <code>true</code> only if the collection contains given <code>value</code>, otherwise <code>false</code>
	 */
	public boolean remove(Object value) {
		if(value == null)
			return false;
		
		int index = indexOf(value);
		if(index != -1) {
			remove(index);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes element at specified <code>index</code> from the collection.
	 * Complexity of this method is never greater then n/2 + 1.
	 * 
	 * @param index index at which object is removed
	 * @throws      <code>IndexOutOfBoundsException</code> if index is not valid
	 */
	public void remove(int index) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("You entered: " + index);
		
		if (index == 0) {
			removeFromBeginning();
		} else {
		ListNode iterator = getListNodeAtIndex(index);
		iterator.previous.next = iterator.next;
		if(iterator.next != null)
			iterator.next.previous = iterator.previous;
		else 
			last = iterator.previous;
		size--;
		}
	}
	
	/**
	 * Removes element from beginning
	 */
	private void removeFromBeginning() {
		if(first.next != null) {
			first.next.previous = null;
			first = first.next;
		}
		size--;
	}
	
	/**
	 * Searches the collection and returns index of the first occurrence of the given <code>value</code>
	 * or -1 if the <code>value</code> is not found. If the given <code>value</code> is <code>null</code> it returns -1.
	 * 
	 * @param value object that is searched for in this collection
	 * @return      index of the given <code>value<code> or -1
	 * 				if the collection does not contain given <code>value</code>
	 */
	public int indexOf(Object value) {
		if(value == null)
			return -1;
		
		int index = 0;
		for (ListNode iterator = first; iterator != null; iterator = iterator.next) {
			if (iterator.value.equals(value)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	/**
	 * Allocates new array with size equals to the size of this collection. 
	 * The order in which elements will be sent is undefined in this class.
	 * 
	 * @return array of objects that this collection contains
	 */
	public Object[] toArray() {
		Object[] elements = new Object[size];
		ListNode iterator = first;
		for (int i = 0; iterator != null; iterator = iterator.next, i++) {
			elements[i] = iterator.value;
		}
		return elements;
	}
	
	/**
	 * Method calls <code>processor.process()<code> for each element of this collection.
	 * The order in which elements will be sent is undefined in this class.
	 * @param processor
	 */
	public void forEach(Processor processor) {
		for (ListNode iterator = first; iterator != null; iterator = iterator.next) {
			processor.process(iterator.value);
		}
	}
	
	/**
	 * Method adds into the current collection all elements from the given collection.
	 * This other collection remains unchanged.
	 * 
	 * @param other the collection whose elements are added to this collection
	 */
	public void addAll(Collection other) {
		
		class addElementsProcessor extends Processor {	
			public void process(Object value) {
				add(value);
			}
		}
		other.forEach(new addElementsProcessor());
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	public void clear() {
		first = last = null;
		size = 0;
	}
	
	/**
	 * Method for getting node of list at given <code>index</code>.
	 * Complexity of this method is never greater then n/2 + 1.
	 * 
	 * @param index index at which node is returned
	 * @return <code>ListNode<code> at given <code>index</code>
	 * @throws <code>IndexOutOfBoundsException</code> if given <code>index</code> is not valid
	 */
	private ListNode getListNodeAtIndex(int index) {
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("You entered: " + index);
		
		ListNode iterator;
		//If index is closer to the beginning of the list start from the first node, otherwise start from the last node
		//Positioning the iterator at the right node
		if(index < size / 2 + 1) {
			for (iterator = first; index > 0; iterator = iterator.next, index--);
		} else {
			index = size - index - 1;
			for (iterator = last; index > 0; iterator = iterator.previous, index--);
		}
		return iterator;
	}
	
}
