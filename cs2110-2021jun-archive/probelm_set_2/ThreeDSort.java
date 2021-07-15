package probelm_set_2;

import java.util.Collections;
import java.util.List;

public class ThreeDSort {

    public static void sort(List<Point> points) {
        Collections.shuffle(points);
        sort(points, 0, points.size() - 1);
    }

    private static void sort(List<Point> points, int low, int high) {
        if (high <= low) return;
        int j = partition(points, low, high);
        sort(points, low, j - 1);
        sort(points, j + 1, high);

    }

    public static int partition(List<Point> points, int low, int high) {
        int i = low;
        int j = high + 1;
        Comparable<Point> pointV = points.get(low);
        while (true) {
            while (pointV.compareTo(points.get(++i)) == 1) {
                if (i == high) {
                    break;
                }
            }
            while (pointV.compareTo(points.get(--i)) == 1) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exchange(points, i, j);
        }
        exchange(points, low, j);
        return j;
    }

    private static void exchange(List<Point> points, int i, int j) {
        Point swap = points.get(i);
        points.set(i, points.get(j));
        points.set(j, swap);
    }
}
