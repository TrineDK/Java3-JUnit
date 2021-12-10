package Module1JUnit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomList<E> implements List<E> {
	Object[] elementData = new Object[10];
	int arraySize = 0; // will increase by one as objects are added
	int arrayCapacity = 10; // used in resize() to increase array capacity


	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {

		return arraySize;
	}

	/**
		 * Returns <tt>true</tt> if this list contains no elements.
		 *
		 * @return <tt>true</tt> if this list contains no elements
		 */
	// @Override
	@Override
	public boolean isEmpty() {

		int i = 0;

		for (i = 0; i < arraySize; i++) {
			if (elementData[i] != null) { // loops through array and returns false if any index is not empty
				return false;
			}
		}
		return true;
		}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at least
	 * one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {

		int i = 0;

		for (i = 0; i < arraySize; i++) {
			if (elementData[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	/**
		 * Appends the specified element to the end of this list.
		 *
		 * @param e element to be appended to this list
		 * @return <tt>true</tt> (as specified by {@link Collection#add})
		 */
		@Override
		public boolean add(E e) {

			if (arraySize > arrayCapacity - 5) {
				resize();
			}

			elementData[arraySize] = e;
			arraySize++;
			return true;
		}



	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If the list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if
	 * such an element exists). Returns <tt>true</tt> if this list contained the
	 * specified element (or equivalently, if this list changed as a result of the
	 * call).
	 *
	 * @param o element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {

		if (indexOf(o) < arraySize - 1) {
			elementData[indexOf(o)] = null;
			return true;
		}
		return false;
	}


	/**
	 * Removes all of the elements from this list. The list will be empty after this
	 * call returns.
	 */
	@Override
	public void clear() {

		Arrays.fill(elementData, null);

	}

	/**
		 * Returns the element at the specified position in this list.
		 *
		 * @param index index of the element to return
		 * @return the element at the specified position in this list
		 * @throws IndexOutOfBoundsException {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E get(int index) {

			if (index > arraySize || index < 0) {
				throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
			}

			return (E) elementData[index];
		}


	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 *
	 * @param index   index of the element to replace
	 * @param element element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {

		if (index > arraySize || index < 0) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
		}

		E discardedElement = (E) elementData[index];

		elementData[index] = element;

		return discardedElement;

	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts
	 * the element currently at that position (if any) and any subsequent elements
	 * to the right (adds one to their indices).
	 *
	 * @param index   index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) {

		if (index > arraySize || index < 0) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
		}

		if (arraySize > arrayCapacity - 5) { // tests array capacity as we're adding an element
			resize();
		}

		for (int i = arraySize - 1; i >= index; i--) { // loops through array and moves elements from index up one space
			elementData[i + 1] = elementData[i];
		}

		elementData[index] = element; // adds new element in the empty index spot

		arraySize++;
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 *
	 * @param index the index of the element to be removed
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	@Override
	public E remove(int index) {

		if (index > arraySize || index < 0) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
		}

		E removedElement = get(index);

		for (int i = index; i < arraySize - 1; i++) { // loops through array and moves elements back one index
			elementData[i] = elementData[i + 1];
		}
		elementData[arraySize - 1] = null; // sets last index value to null
		arraySize--;

		return removedElement;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
	 * -1 if there is no such index.
	 */
	@Override
	public int indexOf(Object o) {

		int i = 0;
		int index = -1;

		for (i = 0; i < arraySize; i++) {
			if (elementData[i] == o) {
				index = i;
				return index;
			}
		}
		return index;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>, or
	 * -1 if there is no such index.
	 */
	@Override
	public int lastIndexOf(Object o) {

		int i = 0;
		int index = -1;

		for (i = 0; i < elementData.length; i++) {
			if (elementData[i] == o) {
				index = i; // index value will be overwritten each time o is encountered, leaving you with
							// the last occurance.
			}
		}
		return index;
	}


	public void resize() {

		Object[] temp = new Object[arrayCapacity * 2];

		for (int i = 0; i < arrayCapacity; i++) {
			temp[i] = elementData[i];
		}
		elementData = temp;
		arrayCapacity = arrayCapacity * 2;
	}


	/**
	 *
	 * You do not need to implement any methods beyond this point. ..But if you're
	 * looking for a challenge, feel free.
	 */

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
}


