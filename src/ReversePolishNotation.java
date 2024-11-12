public class ReversePolishNotation {
	public static String convert(String infix) {
		Stack<Character> workingSpace = new Stack<>(infix.length());
		String postfix = "";
		for (char token : infix.toCharArray()) {
			switch (token) {
				case '(':
					workingSpace.push(token);
					break;

				case ')':
//					TODO error detect empty working space
					while (workingSpace.peek() != '(') {
						postfix += workingSpace.pop();
					}
//					pop the '(' that was found
					workingSpace.pop();
					break;

				case '+':
				case '-':
					while (!workingSpace.isEmpty() && workingSpace.peek() != '(') {
						postfix += workingSpace.pop();
					}
					workingSpace.push(token);
					break;

				case '%':
				case '*':
				case '/':
					while (!workingSpace.isEmpty() &&
							(workingSpace.peek() != '+' && workingSpace.peek() != '-' && workingSpace.peek() != '(')) {
						postfix += workingSpace.pop();
					}
					workingSpace.push(token);
					break;

				default:
					if (isDouble(token) || token == ' ') {
//						TODO handle multi digit doubles
						postfix += token;
					} else {
						throw new IllegalStateException("Unexpected value: " + token);
					}
			}
		}
		while (!workingSpace.isEmpty()) {
			postfix += workingSpace.pop();
		}
		return postfix;
	}

	public static double evaluate(String postfix) {
		Stack<Double> workingSpace = new Stack<>(postfix.length());
		String currentOperand = "";
		for (char token : postfix.toCharArray()) {
			if (isDouble(token)) {
//				stores the operand one char at a time
				currentOperand += token;
			} else if (token == ' ') {
//				when a space is encountered, parse the stored operand
				if (!currentOperand.isEmpty()) {
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

	private static int getPriority(char token) {
		if (token == '*' || token == '/' || token == '%') return 3;
		else if (token == '+' || token == '-') return 2;
		else if (token == '(' || token == ')') return 1;
		else throw new Error("Invalid operator");
	}

	private static boolean isInt(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean isDouble(char c) {
		return isInt(c) || c == '.';
	}
}
