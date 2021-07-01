package class_06_28;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Palindrome {
	public static void main(String [] args) {
		String str = "abba";
		System.out.println(isPalindrome(str));
	}

	private static boolean isPalindrome(String str) {
		Stack<Character> s = new Stack<>();
		Queue<Character> q = new Queue<>();
		
		for (int i = 0; i < str.length(); i++) {
			s.push(str.charAt(i));
			q.enqueue(str.charAt(i));
			
		}
		assert s.size() == q.size();
		while (!s.isEmpty()) {
			if (s.pop() != q.dequeue()) {
				return false;
			}
		}
		return true;
	}
}
