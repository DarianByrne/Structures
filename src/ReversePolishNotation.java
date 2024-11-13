public class ReversePolishNotation {
	public static String convert(String infix) {
		if (!(new ParenthesisChecker().evaluate(infix))) throw new Error("Parentheses don't match");

		Stack<Character> workingSpace = new Stack<>(infix.length());
		String postfix = "";
		String currentOperand = "";
		char[] tokens = infix.toCharArray();
		for (int i = 0; i < tokens.length; i++) {
			char token = tokens[i];
			if (isDouble(token)) {
//				stores the operand one char at a time
				currentOperand += token;
//				looks ahead to see if this is the last char in the expression or the double
				if ((i == tokens.length - 1
						|| (i + 1 < tokens.length && !isDouble(tokens[i + 1])))) {
					try {
						postfix += Double.parseDouble(currentOperand) + " ";
						currentOperand = "";
					} catch (NumberFormatException e) {
						throw new Error("Invalid operand");
					}
				}
			} else {
				switch (token) {
					case ' ' -> {
					}
					case '(' -> workingSpace.push(token);
					case ')' -> {
						postfix = popUntil(workingSpace, postfix);
//						pop the '(' that was found
						popOpening(workingSpace);
					}
					case '+', '-' -> {
						postfix = popUntil(workingSpace, postfix);
						workingSpace.push(token);
					}
					case '%', '*', '/' -> {
						while (!workingSpace.isEmpty()
								&& workingSpace.peek() != '('
								&& workingSpace.peek() != '+'
								&& workingSpace.peek() != '-') {
							postfix += workingSpace.pop() + " ";
						}
						workingSpace.push(token);
					}
					default -> throw new Error("Unexpected token (" + token + ")");
				}
			}
		}
		postfix = popUntil(workingSpace, postfix);
//		pop the '(' that was found
		popOpening(workingSpace);
		return postfix;
	}

	public static double evaluate(String postfix) {
		Stack<Double> workingSpace = new Stack<>(postfix.length());
		String currentOperand = "";
		char[] tokens = postfix.toCharArray();
		for (int i = 0; i < tokens.length; i++) {
			char token = tokens[i];
//			although spaces are necessary for separating numbers, they are skipped
			if (token == ' ') continue;

			if (isDouble(token)) {
//				stores the operand one char at a time
				currentOperand += token;
//				looks ahead to see if this is the last char in the expression or the double
				if ((i == tokens.length - 1
						|| (i + 1 < tokens.length && !isDouble(tokens[i + 1])))) {
					try {
						workingSpace.push(Double.parseDouble(currentOperand));
						currentOperand = "";
					} catch (NumberFormatException e) {
						throw new Error("Invalid operand");
					}
				}
			} else {
				double firstOperand;
				double secondOperand;
				try {
//					second operand must be popped first
					secondOperand = workingSpace.pop();
					firstOperand = workingSpace.pop();
				} catch (Error e) {
					throw new Error("Missing operand(s)");
				}

//				applies the operator to the two operands
				double result;
				switch (token) {
					case '%' -> result = firstOperand % secondOperand;
					case '*' -> result = firstOperand * secondOperand;
					case '/' -> result = firstOperand / secondOperand;
					case '-' -> result = firstOperand - secondOperand;
					case '+' -> result = firstOperand + secondOperand;
					default -> throw new Error("Invalid operator");
				}
				workingSpace.push(result);
			}
		}
		if (workingSpace.getNumOfElements() == 1 && currentOperand.isEmpty()) {
//			only the result is in the working space
//			and there is no operand that hasn't been operated on
			return workingSpace.pop();
		} else {
			throw new Error("Missing operator(s)");
		}
	}

	private static boolean isDouble(char c) {
		return (c >= '0' && c <= '9') || c == '.';
	}

	private static void popOpening(Stack<Character> workingSpace) {
		if (!workingSpace.isEmpty() && workingSpace.peek() == '(') {
			workingSpace.pop();
		}
	}

	private static String popUntil(Stack<Character> workingSpace, String postfix) {
		while (!workingSpace.isEmpty() && workingSpace.peek() != '(') {
			postfix += workingSpace.pop() + " ";
		}
		return postfix;
	}
}
