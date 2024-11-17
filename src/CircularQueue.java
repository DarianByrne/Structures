public class CircularQueue<T> {
	private final CircularArray<T> queue;

	public CircularQueue(int size) {
		queue = new CircularArray<>(size);
	}

	public int getSize() {
		return queue.getSize();
	}

	public int getNumOfElements() {
		return queue.getNumOfElements();
	}

	public void enqueue(T newElement) {
		if (isFull()) {
			throw new Error("Queue is full (" + elementCount() + ")");
		}
		queue.addTail(newElement);
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new Error("Queue is empty (" + elementCount() + ")");
		}
		return queue.deleteHead();
	}

	public T peek() {
		return queue.getHead();
	}

	public void clear() {
		queue.clear();
	}

	public boolean isFull() {
		return queue.isFull();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public String elementCount() {
		return getNumOfElements() + "/" + getSize();
	}

	@Override
	public String toString() {
		String str = elementCount() + " elements";
		if (!isEmpty()) {
			str += ", head: " + peek().toString();
		}
		return str;
	}
}
