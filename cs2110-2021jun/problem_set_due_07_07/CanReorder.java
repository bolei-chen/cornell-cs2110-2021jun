package problem_set_due_07_07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CanReorder {

    public static boolean canReorder(List<Integer> inputOrder, int k) {
        int min = 1;
        int trackNum = 0;
        Stack<Integer> inputTrack = new Stack<>();
        Queue<Integer> outputTrack = new LinkedList<>();
        List<Stack<Integer>> holdingTracks = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            holdingTracks.add(new Stack<>());
        }
        for (int i: inputOrder) {
            inputTrack.push(i);
        }
        int length = inputOrder.size();

        while (outputTrack.size() < length) {
            int top = inputTrack.peek();
            if (top == min) {
                inputTrack.pop();
                outputTrack.add(top);
                min++;
            } else if (top > min) {
                for (int i = 0; i < k; i++) {
                    

                }

                holdingTracks.get(trackNum).push(top);

            }



        }

        
        



        return true;
    }
    public static void main(String[] args) {
        List<Integer> inputOrder = new ArrayList<>();
        int k = 0;
        System.out.println(canReorder(inputOrder, k));
    }

}
