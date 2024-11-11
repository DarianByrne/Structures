@SuppressWarnings("unchecked")

public class LeftJustifiedArray<T> {
	private final T[] arr;
	private final int size;
	private int numOfElements;

	public LeftJustifiedArray(int size) {
		arr = (T[]) new Object[size];
		this.size = size;
		numOfElements = 0;
	}

	public int getSize() {
		return size;
	}

	public int getNumOfElements() {
		return numOfElements;
	}

	public T set(int index, T newElement) {
		if (isFull()) {
			throw new Error("Array is full (" + elementCount() + ")");
		}
		if (!goodIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		T oldElement = arr[index];
		arr[index] = newElement;
		return oldElement;
	}

	public void insert(int index, T newElement) {
		if (isFull()) {
			throw new Error("Array is full (" + elementCount() + ")");
		}
		if (!goodInsertIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		for (int i = numOfElements; i > index; i--) {
			arr[i] = arr[i - 1];
		}
		arr[index] = newElement;
		numOfElements++;
	}

	public T get(int index) {
		if (isEmpty()) {
			throw new Error("Array is empty (" + elementCount() + ")");
		}
		if (!goodIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		return arr[index];
	}

	public T delete(int index) {
		if (isEmpty()) {
			throw new Error("Array is empty (" + elementCount() + ")");
		}
		if (!goodIndex(index)) {
			throw new Error("Invalid index (" + index + ")");
		}
		T deletedElement = arr[index];
		for (int i = index; i < numOfElements - 1; i++) {
			arr[i] = arr[i + 1];
		}
		numOfElements--;
		arr[numOfElements] = null;
		return deletedElement;
	}

	public void clear() {
		while (!isEmpty()) {
			delete(numOfElements - 1);
		}
	}

	public boolean isFull() {
		return numOfElements >= size;
	}

	public boolean isEmpty() {
		return numOfElements <= 0;
	}

	private boolean goodInsertIndex(int index) {
		return index >= 0 && index <= numOfElements;
	}

	private boolean goodIndex(int index) {
		return index >= 0 && index < numOfElements;
	}

	public String elementCount() {
		return numOfElements + "/" + size;
	}

	@Override
	public String toString() {
		String str = elementCount() + " elements";
		for (int i = 0; i < numOfElements; i++) {
			str += ", " + arr[i];
		}
		return str;
	}
}
