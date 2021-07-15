package problem_set_due_07_07;

import java.util.Stack;


public class IsValid {

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        char bracket;
        for (int i = 0; i < str.length(); i++) {
            bracket = str.charAt(i);

            if (bracket == '{' || bracket == '(' || bracket == '[' || bracket == '<') {
                stack.push(bracket);
            }

            if (bracket == '}') {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '{') {
                    stack.pop();
                }
            }

            if (bracket == '>') {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '<') {
                    stack.pop();
                }
            }

            if (bracket == ')') {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '(') {
                    stack.pop();
                }
            }

            if (bracket == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '[') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
    	
    	System.out.println(isBalanced("<>"));
    	System.out.println(isBalanced("<>()"));
    	System.out.println(isBalanced("{<>()}"));
    	System.out.println(isBalanced("[{(<>)}][{(<>)}]"));
    	System.out.println(isBalanced("[()]{}{[()()]()}"));
    	System.out.println(isBalanced("<><><>(<><{}{<>}><>)<><>"));
    	System.out.println(isBalanced("[(])"));
        System.out.println(isBalanced("]"));
        System.out.println(isBalanced("<><>(<><><><><>"));
        System.out.println(isBalanced("<><><>(<><><)><><>"));
        System.out.println(isBalanced("<><><><><>)<><><>(<><>"));
    }
}
