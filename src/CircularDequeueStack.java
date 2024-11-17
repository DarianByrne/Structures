public class CircularDequeueStack<T> extends CircularDequeue<T> {
	public CircularDequeueStack(int size) {
		super(size);
	}

	public void push(T newElement) {
		enqueueLast(newElement);
	}

	public T pop() {
		return dequeueLast();
	}

	@Override
	public T peek() {
		return peekLast();
	}

	@Override
	public String toString() {
		String str = elementCount() + " elements";
		if (!isEmpty()) {
			str += ", top: " + peek().toString();
		}
		return str;
	}
}
