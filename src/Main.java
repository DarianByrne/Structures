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
		}
		System.out.println("Stack: " + myStack);
		while (!myStack.isEmpty()) {
			System.out.println(myStack.pop());
		}
		System.out.println("Stack: " + myStack);

		ParenthesisChecker checker = new ParenthesisChecker();
		System.out.println("ParenthesisChecker: " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\"); } }"));
		System.out.println("ParenthesisChecker: " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\"); ) }"));
		System.out.println("ParenthesisChecker: " + checker.evaluate("public class Main { public static void main(String[] args) { System.out.println(\"Hello world!\");  }"));

		ReversePolishNotation RPN = new ReversePolishNotation();
//		( 5 - 2 ) + 3
		int result = RPN.evaluate(new char[]{'5', '2', '-', '3', '+'});
		System.out.println("ReversePolishNotation: " + result);
	}
}
