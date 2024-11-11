public class Stack<T> {
	private final LeftJustifiedArray<T> stack;

	public Stack(int size) {
		stack = new LeftJustifiedArray<>(size);
	}

	public int getSize() {
		return stack.getSize();
	}

	public int getNumOfElements() {
		return stack.getNumOfElements();
	}

	public void push(T newElement) {
		if (isFull()) {
			throw new Error("Stack is full (" + elementCount() + ")");
		}
		stack.insert(getNumOfElements(), newElement);
	}

	public T pop() {
		if (isEmpty()) {
			throw new Error("Stack is empty (" + elementCount() + ")");
		}
		return stack.delete(getNumOfElements() - 1);
	}

	public T peek() {
		return stack.get(getNumOfElements() - 1);
	}

	public void clear() {
		stack.clear();
	}

	public boolean isFull() {
		return stack.isFull();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public String elementCount() {
		return getNumOfElements() + "/" + getSize();
	}

	@Override
	public String toString() {
		return stack.toString();
	}
}
