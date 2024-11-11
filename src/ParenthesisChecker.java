public class ParenthesisChecker {
	private final Stack<Character> history;

	public ParenthesisChecker() {
		this.history = new Stack<>(32);
	}

	public boolean evaluate(String str) {
		history.clear();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(' || c == '{' || c == '[') {
				history.push(c);
			} else if (c == ')' || c == '}' || c == ']') {
				try {
					char popped = history.pop();
					if ((popped != '(' || c != ')') && (popped != '{' || c != '}') && (popped != '[' || c != ']')) {
//						parentheses don't match
						return false;
					}
				} catch (Error e) {
//					not enough opening parentheses
					return false;
				}
			}
		}
//		if true 	every opening parenthesis has a closing match
//		if false	not enough closing parentheses
		return history.isEmpty();
	}
}
