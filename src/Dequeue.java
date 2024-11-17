public class Dequeue<T> extends Queue<T> {
	private final LeftJustifiedArray<T> dequeue;

	public Dequeue(int size) {
		super(size);
		dequeue = new LeftJustifiedArray<>(size);
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
		dequeue.insert(0, newElement);
	}

	public void enqueueLast(T newElement) {
		if (isFull()) {
			throw new Error("Queue is full (" + elementCount() + ")");
		}
		dequeue.insert(getNumOfElements(), newElement);
	}

	@Override
	public T dequeue() {
		return dequeueFirst();
	}

	public T dequeueFirst() {
		if (isEmpty()) {
			throw new Error("Queue is empty (" + elementCount() + ")");
		}
		return dequeue.delete(0);
	}

	public T dequeueLast() {
		if (isEmpty()) {
			throw new Error("Queue is empty (" + elementCount() + ")");
		}
		return dequeue.delete(getNumOfElements() - 1);
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	public T peekFirst() {
		return dequeue.get(0);
	}

	public T peekLast() {
		return dequeue.get(getNumOfElements() - 1);
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
