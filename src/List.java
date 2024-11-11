import java.util.Objects;

//	https://www.baeldung.com/java-passing-method-parameter#using-lambda-expressions
@FunctionalInterface
interface CompareFunction<T> {
	boolean compare(T current, T target);
}

public class List<T> {
	private final LeftJustifiedArray<T> list;

	public List(int size) {
		list = new LeftJustifiedArray<>(size);
	}

	public int getSize() {
		return list.getSize();
	}

	public int getNumOfElements() {
		return list.getNumOfElements();
	}

	public T set(int index, T newElement) {
		return list.set(index, newElement);
	}

	public T get(int index) {
		if (isEmpty()) {
			throw new Error("List is empty (" + elementCount() + ")");
		}
		return list.get(index);
	}

	public void add(int index, T newElement) {
		if (isFull()) {
			throw new Error("List is full (" + elementCount() + ")");
		}
		list.insert(index, newElement);
	}

	public T remove(int index) {
		if (isEmpty()) {
			throw new Error("List is empty (" + elementCount() + ")");
		}
		return list.delete(index);
	}

	/**
	 * @param target the element to find
	 * @return -1 if target isn't in the list, otherwise the index of target
	 */
	public int indexOf(T target) {
		return indexOf(target, Objects::equals);
	}

	/**
	 * @param target the element to compare to
	 * @param f      a lambda which compares each value in the list to the target
	 * @return -1 if f never returns true, otherwise the index when f returned true
	 */
	public int indexOf(T target, CompareFunction<T> f) {
		if (isEmpty()) {
			throw new Error("List is empty (" + elementCount() + ")");
		}
		int i = 0;
//		tries the comparison against every element in the list, continues if successful
		while (i < getNumOfElements() && !f.compare(get(i), target)) {
			i++;
		}
		if (i < getNumOfElements()) {
			return i;
		} else {
			return -1;
		}
	}

	public void clear() {
		list.clear();
	}

	public boolean isFull() {
		return list.isFull();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public String elementCount() {
		return getNumOfElements() + "/" + getSize();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
