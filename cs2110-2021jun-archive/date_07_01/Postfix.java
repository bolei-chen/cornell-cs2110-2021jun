package date_07_01;

import edu.princeton.cs.algs4.Stack;


public class Postfix {
	
	static boolean isPlus(String s) {
		return s.equals("+");
	}
	
	static boolean isMinus(String s) {
		return s.equals("-");
	}
	
	static boolean isTimes(String s) {
		return s.equals("*");
	}
	
	static boolean isDivide(String s) {
		return s.equals("/");
	}
	
	
	public static double evaluate(String expr) {
		String[] tokens = expr.split(" ");
		Stack<Double> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			if (isPlus(tokens[i])) {
				stack.push(stack.pop() + stack.pop());
				
			} else if (isMinus(tokens[i])) {
				double v1 = stack.pop();
				double v2 = stack.pop();
				stack.push(v2 - v1);
				
			} else if (isTimes(tokens[i])) {
				
				stack.push(stack.pop() * stack.pop());
				
			} else if (isDivide(tokens[i])) {
				
				stack.push(stack.pop() / stack.pop());
			} else {
				double num = Double.parseDouble(tokens[i]);
				stack.push(num);
			}
		}
		return stack.pop();
	}
	

	
	public static void main(String[] args) {
		System.out.println(evaluate("2 5 +"));

	}

}
