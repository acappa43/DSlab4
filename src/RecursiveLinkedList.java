

public class RecursiveLinkedList<E> implements List<E> {

	private Node<E> head; // References first data node
	private int currentSize;
	
	@SuppressWarnings("unused")
	private static class Node<E> {
		private E value;
		private Node<E> next;
		
		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
		
		public Node(E value) {
			this(value, null); // Delegate to other constructor
		}
		
		public Node() {
			this(null, null); // Delegate to other constructor
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		public void clear() {
			value = null;
			next = null;
		}				
	} // End of Node class

	public RecursiveLinkedList() {
		head = null;
		currentSize = 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		while (!isEmpty())
			remove(0);
	}

	@Override
	public boolean contains(E e) {
		return firstIndex(e) >= 0;
	}

	@Override
	public int removeAll(E e) {
		if (isEmpty())
			return 0;
		int counter = 0;
		Node<E> curNode = head;
		Node<E> nextNode = curNode.getNext();
		
		// Traverse the entire list
		while (nextNode != null) { 
			if (nextNode.getValue().equals(e)) { // Remove it!
				curNode.setNext(nextNode.getNext());
				nextNode.clear();
				currentSize--;
				counter++;
				/* Node that was pointed to by nextNode no longer exists
				   so reset it such that it's still the node after curNode */
				nextNode = curNode.getNext();
			}
			else {
				curNode = nextNode;
				nextNode = nextNode.getNext();
			}
		}
		return counter;
	}

	@Override
	public E first() {
		if (isEmpty())
			return null;
		return head.getValue();
	}

	@Override
	public E last() {
		if (isEmpty())
			return null;
		Node<E> curNode; // Define here to retain access after the loop
		for (curNode = head; curNode.getNext() != null; curNode = curNode.getNext());
		return curNode.getValue();
	}

	@Override
	public int firstIndex(E e) {
		int i = 0;
		for (Node<E> curNode = head; curNode.getNext() != null; curNode = curNode.getNext(), i++)
			if (curNode.getValue().equals(e))
				return i;
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		int i = 0, pos = -1;
		for (Node<E> curNode = head; curNode.getNext() != null; curNode = curNode.getNext(), i++)
			if (curNode.getValue().equals(e))
				pos = i;
		return pos;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) 
			throw new IndexOutOfBoundsException(
					"RecursiveLinkedList.get: invalid index = " + index); 
		// index is valid
		return recGet(head, index); 
	}

	@Override
	public void add(E e) {
		add(size(), e); // Add at the end of the list
	}

	@Override
	public void add(int index, E e) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) 
			throw new IndexOutOfBoundsException(
					"RecursiveLinkedList.add: invalid index = " + index); 
		// index is valid for the add operation
		head = recAdd(head, index, e); 
		currentSize++;
	}

	@Override
	public boolean remove(int index) {
		if (index < 0 || index >= size()) 
			return false;

		// index is valid for remove operation

		/* TODO ADD CODE HERE TO PROPERLY INVOKE THE recRemove AUXILIARY METHOD */

		currentSize--; 
		return true;
	}

	@Override
	public E set(int index, E e) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) 
			throw new IndexOutOfBoundsException(
					"RecursiveLinkedList.set: invalid index = " + index); 

		// index is valid for set operation
		return recSet(head, index, e);  
	}

	/*******************************/
	/* Auxiliary recursive methods */
	/*******************************/

	/**
	 * Returns the value in the node corresponding to the index value i in
	 * the linked list whose first node is being referenced by f. On any such
	 * list the first node is the one associated to index 0, the second node
	 * is the one associated with index 1, and so on. It presumes that the
	 * list whose first node is f has at least i+1 nodes. 
	 * 
	 * @param f		First node of linked list to traverse
	 * @param i		Index value of node whose value should be returned
	 * @return		Value within node at index i
	 */
	private static <E> E recGet(Node<E> f, int i) {
		if (i == 0)
			return f.getValue(); 
		else 
			return recGet(f.getNext(), i-1); 
	} 

	@SuppressWarnings("unused")
	private static <E> Node<E> recRemove(Node<E> f, int i) {
		if (i == 0) { 
		    Node<E> ntd = f; 
		    f = f.getNext(); 
		    ntd.clear(); 
		}
		else
		    f.setNext(recRemove(f.getNext(), i-1)); 

		return f; 
	} 

	/**
	 * Inserts a new node in the linked list whose first node is being
	 * referenced by f so that the new node contains the data element e and it
	 * ends up occupying the position with index value i. Finally, it returns
	 * the reference to the first node of the list that results after the
	 * insertion is completed. It presumes that the list whose first node is
	 * f has at least i nodes.
	 * 
	 * @param f		First node of linked list where node must be inserted
	 * @param i		Index value of where new node must be inserted
	 * @param e		Value or element that must be contained within the new node
	 */
	private static <E> Node<E> recAdd(Node<E> f, int i, E e) { 
		/* TODO */
		return null; // Remove this line
	} 

	private static <E> E recSet(Node<E> f, int index, E e) { 
		/* TODO */
		return null; // Remove this line
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] theArray = (E[]) new Object[size()];
		int i = 0;
		for (Node<E> curNode = head; curNode != null; curNode = curNode.getNext())
			theArray[i++] = curNode.getValue();
		return theArray;
	} 

}