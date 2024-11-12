public class ReversePolishNotation {
	private final Stack<Character> history;

	public ReversePolishNotation() {
		this.history = new Stack<>(32);
	}

	public char[] convert(char[] tokens) {
//		TODO
		return new char[10];
	}

	public int evaluate(char[] tokens) {
		history.clear();
		int i = 0;
		while (i < tokens.length) {
			char token = tokens[i];
			if ((token != '*' && token != '/' && token != '-' && token != '+')) {
				history.push(token);
			} else {
				if (history.isEmpty()) {
					throw new Error("Invalid infix");
				}
				if (history.peek() >= '0' && history.peek() <= '9') {
					int secondNum = history.pop() - '0';

					if (history.isEmpty()) {
						throw new Error("Invalid infix");
					}
					if (history.peek() >= '0' && history.peek() <= '9') {
						int firstNum = history.pop() - '0';

						int result;
						switch (token) {
							case '*' -> result = firstNum * secondNum;
							case '/' -> result = firstNum / secondNum;
							case '-' -> result = firstNum - secondNum;
							case '+' -> result = firstNum + secondNum;
							default -> throw new Error("Invalid operator");
						}
						history.push((char) (result + '0'));
					} else {
						throw new Error("Invalid first num");
					}
				} else {
					throw new Error("Invalid second num");
				}
			}
			i++;
		}
		if (history.getNumOfElements() == 1) {
			return history.pop() - '0';
		} else {
			throw new Error("Invalid infix");
		}
	}
}
