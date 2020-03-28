
public interface List<E> {

	/* Interface methods are public by default */
	void add(E e);
	void add(int index, E e) throws IndexOutOfBoundsException;
	boolean remove(int index);
	int removeAll(E e);
	E get(int index) throws IndexOutOfBoundsException;
	E set(int index, E e) throws IndexOutOfBoundsException;
	E first();
	E last();
	int firstIndex(E e);
	int lastIndex(E e);
	int size();
	boolean isEmpty();
	boolean contains(E e);
	void clear();
	E[] toArray();
}