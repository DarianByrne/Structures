public class Main {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		LeftJustifiedArray<Integer> myArr = new LeftJustifiedArray<>(10);
		for (int i = 1; i <= 8; i++) {
			myArr.insert(i - 1, i * 10);
		}
		System.out.println("LeftJustifiedArray: " + myArr);
		myArr.delete(3);
		System.out.println("LeftJustifiedArray: " + myArr);

		List<Integer> myList = new List<>(5);
		for (int i = 1; i <= 5; i++) {
			myList.add(i - 1, i * 2);
		}
		System.out.println("List: " + myList);
		System.out.println(myList.indexOf(4));
		System.out.println(myList.indexOf(5));
//		finds the index of the first element that is greater than the target
		System.out.println(myList.indexOf(5, (current, target) -> current > target));

		Stack<Integer> myStack = new Stack<>(10);
		for (int i = 1; i <= 10; i++) {
			myStack.push(i);
			System.out.print(myStack.peek() + ", ");
		}
		System.out.println("Stack: " + myStack);
		while (!myStack.isEmpty()) {
			System.out.print(myStack.pop() + ", ");
		}
		System.out.println("Stack: " + myStack);

		ParenthesisChecker checker = new ParenthesisChecker();
		System.out.print("ParenthesisChecker: " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\"); } }"));
		System.out.print(", " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\"); ) }"));
		System.out.println(", " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\");  }"));

		String postfix1 = ReversePolishNotation.convert("(55.5-2)+3");
		System.out.print("ReversePolishNotation: postfix: " + postfix1);
		String postfix2 = ReversePolishNotation.convert("   (   55.5   -   2   )   +   3");
		System.out.print(", " + postfix2);
		System.out.println(", " + postfix1.equals(postfix2));

		double result1 = ReversePolishNotation.evaluate(postfix1);
		System.out.print("ReversePolishNotation: result: " + result1);
//		compares generated postfix with hardcoded postfix
		double result2 = ReversePolishNotation.evaluate("55.5 2-3+");
		System.out.print(", " + result2);
		System.out.println(", " + (result1 == result2));

		Queue<String> myQueue = new Queue<>(10);
		myQueue.enqueue("Darian");
		myQueue.enqueue("Vikki");
		myQueue.enqueue("Brandon");
		myQueue.enqueue("Oliwier");
		myQueue.enqueue("Milo");
		System.out.println("Queue: " + myQueue);
		while (!myQueue.isEmpty()) {
			String next = myQueue.dequeue();
			System.out.print("next: " + next + ", ");
		}
		System.out.println("Queue: " + myQueue);

//		queue above implemented as a dequeue
		Dequeue<String> myDequeue = new Dequeue<>(10);
		myDequeue.enqueueLast("Darian");
		myDequeue.enqueueLast("Vikki");
		myDequeue.enqueueLast("Brandon");
		myDequeue.enqueueLast("Oliwier");
		myDequeue.enqueueLast("Milo");
		System.out.println("Dequeue: " + myDequeue);
		while (!myDequeue.isEmpty()) {
			String next = myDequeue.dequeueFirst();
			System.out.print("next: " + next + ", ");
		}
		System.out.println("Dequeue: " + myDequeue);

//		browsing history example
		DequeueStack<Integer> history = new DequeueStack<>(3);
//		the user visits 5 pages
		for (int i = 0; i < 5; i++) {
			System.out.print("History: " + history);
//			when they visit more pages than the history can hold,
			if (history.isFull()) {
//				dequeue the first (oldest) entry
				System.out.print(", delete oldest: " + history.dequeueFirst());
			}
//			add the page to the front
			System.out.println(", add newest: " + i);
			history.push(i);
		}
		System.out.print("History: " + history);
		System.out.print((", go back a page to: " + history.pop()));
		System.out.println((", go back a page to: " + history.pop()));
//		although we visited more than 3 pages, only 1 page is left in the history now
		System.out.println("History: " + history);

		CircularArray<Integer> myArr2 = new CircularArray<>(10);
		System.out.println("CircularArray: " + myArr2);
		for (int i = 1; i <= 8; i++) {
//			adds the elements in a flip-flop style order
			if (i % 2 == 0) {
				myArr2.addTail(i);
			} else {
				myArr2.addHead(i);
			}
		}
		myArr2.addHead(9);
		System.out.println("CircularArray: " + myArr2);
		int count = myArr2.getNumOfElements();
		for (int i = 0; i < count; i++) {
//			reverses the flip-flop from before
			if (i % 2 == 0) {
				System.out.print(myArr2.deleteHead() + ", ");
			} else {
				System.out.print(myArr2.deleteTail() + ", ");
			}
		}
		System.out.println("CircularArray: " + myArr2);
	}
}
