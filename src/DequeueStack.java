public class DequeueStack<T> extends Dequeue<T> {
	public DequeueStack(int size) {
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
