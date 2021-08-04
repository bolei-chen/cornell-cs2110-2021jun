package problem_set_3;

import java.util.Random;

public class Experiment {
    static BST B = new BST();
    static Random rand = new Random();

    public static void experimentA() {
        // print the dependent variable 'h' every time after a new node is inserted.
        // control the number of iterations each time.
        // the default range of variation is set to be 50.
        for (int numOfIterations = 10; numOfIterations < 2000; numOfIterations += 10) {
            for (int j = 0; j < numOfIterations; j++) {
                int key = rand.nextInt(4000);
                B.put(key, "");
            }
            // print the height and clean the tree,\.
            System.out.println(B.height());
            B.clear(rand.nextInt(4000), "", 1);
            


        }

    }

    public static void experimentB() {
        // control the time of insertions.
        for (int range = 10; range < 2001; range += 10) {
            // the default number of iterations is 1000
            for (int i = 0; i < 1000; i++) {
                int key = rand.nextInt(range);
                B.put(key, "");
            }
            System.out.println(B.height());
            B.clear(rand.nextInt(2000), "", 1);

        }

    }
    public static void main(String[] args) {
        experimentA();
    }
}
