public class CircularDequeue<T> extends CircularQueue<T> {
	private final CircularArray<T> dequeue;

	public CircularDequeue(int size) {
		super(size);
		dequeue = new CircularArray<>(size);
	}

	public int getSize() {
		return dequeue.getSize();
	}

	public int getNumOfElements() {
		return dequeue.getNumOfElements();
	}

	@Override
	public void enqueue(T newElement) {
		enqueueLast(newElement);
	}

	public void enqueueFirst(T newElement) {
		if (isFull()) {
			throw new Error("Queue is full (" + elementCount() + ")");
		}
		dequeue.addHead(newElement);
	}

	public void enqueueLast(T newElement) {
		if (isFull()) {
			throw new Error("Queue is full (" + elementCount() + ")");
		}
		dequeue.addTail(newElement);
	}

	@Override
	public T dequeue() {
		return dequeueFirst();
	}

	public T dequeueFirst() {
		if (isEmpty()) {
			throw new Error("Queue is empty (" + elementCount() + ")");
		}
		return dequeue.deleteHead();
	}

	public T dequeueLast() {
		if (isEmpty()) {
			throw new Error("Queue is empty (" + elementCount() + ")");
		}
		return dequeue.deleteTail();
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	public T peekFirst() {
		return dequeue.getHead();
	}

	public T peekLast() {
		return dequeue.getTail();
	}

	public void clear() {
		dequeue.clear();
	}

	public boolean isFull() {
		return dequeue.isFull();
	}

	public boolean isEmpty() {
		return dequeue.isEmpty();
	}

	public String elementCount() {
		return getNumOfElements() + "/" + getSize();
	}

	@Override
	public String toString() {
		String str = elementCount() + " elements";
		if (!isEmpty()) {
			str += ", head: " + peekFirst().toString()
					+ ", tail: " + peekLast().toString();
		}
		return str;
	}
}
