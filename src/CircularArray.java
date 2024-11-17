import java.util.Arrays;

@SuppressWarnings("unchecked")

public class CircularArray<T> {
	private final T[] arr;
	private final int size;
	private int numOfElements;
	private int head;
	private int tail;

	public CircularArray(int size) {
		arr = (T[]) new Object[size];
		this.size = size;
		numOfElements = 0;
		head = 0;
		tail = size - 1;
	}

	public int getSize() {
		return size;
	}

	public int getNumOfElements() {
		return numOfElements;
	}

//	index starts at 0 (head) and goes to numOfElements (tail)
	public T set(int index, T newElement) {
		if (newElement == null) {
			throw new Error("Element cannot be null");
		}
		if (isFull()) {
			throw new Error("Array is full (" + elementCount() + ")");
		}
		if (!goodExternalIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		index = zeroToCircular(index);
		T oldElement = arr[index];
		arr[index] = newElement;
		return oldElement;
	}

	private void add(T newElement, int index) {
		if (newElement == null) {
			throw new Error("Element cannot be null");
		}
		if (isFull()) {
			throw new Error("Array is full (" + elementCount() + ")");
		}
		arr[index] = newElement;
		numOfElements++;
	}

	public void addHead(T newElement) {
		head = cycleIndex(head - 1);
		add(newElement, head);
	}

	public void addTail(T newElement) {
		tail = cycleIndex(tail + 1);
		add(newElement, tail);
	}

	private T getHelper(int index) {
		if (isEmpty()) {
			throw new Error("Array is empty (" + elementCount() + ")");
		}
		return arr[index];
	}

	public T getHead() {
		return getHelper(head);
	}

	public T getTail() {
		return getHelper(tail);
	}

	public T get(int index) {
		if (isEmpty()) {
			throw new Error("Array is empty (" + elementCount() + ")");
		}
		if (!goodExternalIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		return arr[zeroToCircular(index)];
	}

	private T deleteHelper(int index) {
		if (isEmpty()) {
			throw new Error("Array is empty (" + elementCount() + ")");
		}
		T deletedElement = arr[index];
		arr[index] = null;
		numOfElements--;
		return deletedElement;
	}

	public T deleteHead() {
		T deletedElement = deleteHelper(head);
		head = cycleIndex(head + 1);
		return deletedElement;
	}

	public T deleteTail() {
		T deletedElement = deleteHelper(tail);
		tail = cycleIndex(tail - 1);
		return deletedElement;
	}

	public void clear() {
		while (!isEmpty()) {
			deleteTail();
		}
	}

	public boolean isFull() {
		return numOfElements >= size;
	}

	public boolean isEmpty() {
		return numOfElements <= 0;
	}

	private int zeroToCircular(int index) {
		if (!goodExternalIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		return cycleIndex(index + head);
	}

	private boolean goodExternalIndex(int index) {
		return index >= 0 && index < numOfElements;
	}

//	https://stackoverflow.com/a/4412200
	private int cycleIndex(int index) {
//		(a % b + b) % b
		return (index % size + size) % size;
	}

	public String elementCount() {
		return numOfElements + "/" + size;
	}

	@Override
	public String toString() {
		String str = elementCount() + " elements";
		for (int i = 0; i < numOfElements; i++) {
			str += ", " + arr[zeroToCircular(i)];
		}
		return str;
	}
}
