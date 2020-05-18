package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Program that uses stack for evaluating arithmetic expressions in postfix representation.
 * 
 * @author Ante Gazibarić
 * @version 1.0
 *
 */
public class StackDemo {
	
	/**
	 * Expression that is given to this program must be enclose into quotation marks.
	 * 
	 * @param args expression that is given to the program. Number of arguments must be 1.
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.printf("Number of arguments must be one.%nYou entered: %d.%n", args.length);
			System.exit(1);
		}

		String[] expresion = args[0].split(" ");
		ObjectStack stack = new ObjectStack();

		for (String element : expresion) {
			if (isNumber(element)) {
				stack.push(Integer.valueOf(element));
			} else {
				Integer number1 = (Integer) stack.pop();
				Integer number2 = (Integer) stack.pop();
				Integer result = performOperation(number2, number1, element);
				stack.push(result);
			}
		}

		if (stack.size() == 1) {
			System.out.printf("Expression evaulates to: %d.%n", stack.pop());
		} else {
			System.err.println("Error occured!");
			System.exit(1);
		}

	}

	/**
	 * Method for checking if given string is a integer
	 * 
	 * @param s string that represents integer
	 * @return  <code>true</code> if given string is a integer, otherwise <code>false</code>
	 */
	private static boolean isNumber(String s) {
		try {
			Integer.valueOf(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Method that performs given operation with <code>x</code> and <code>y</code> 
	 * and returns that result.
	 * Supported operations: adding, subtracting, dividing, multiplying, modulo operation.
	 * 
	 * @param x          operand
	 * @param y          operand
	 * @param operation  operation that is performed
	 * @return           result of <code>operation</code> with <code>x</code> and <code>y</code>
	 * @throws           <code>IllegalArgumentException</code> if given operation is unsupported
	 */
	private static int performOperation(int x, int y, String operation) {
		switch (operation) {
		case "+":
			return x + y;
		case "-":
			return x - y;
		case "/":
			return x / y;
		case "*":
			return x * y;
		case "%":
			return x % y;
		default:
			throw new IllegalArgumentException("Operation that is given is not valid. "
					+ "You entered: '" + operation + "'.");
		}
	}

}

