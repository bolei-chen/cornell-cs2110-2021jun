package probelm_set_2;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    private Quick() {
    }

    public static void sort(Comparable<Integer>[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);

    }

    private static void sort(Comparable<Integer>[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);

    }

    private static int partition(Comparable<Integer>[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable<Integer> v = a[lo];
        while (true) { 
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    public static Comparable<Integer> select(Comparable<Integer>[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k)
                hi = i - 1;
            else if (i < k)
                lo = i + 1;
            else
                return a[i];
        }
        return a[lo];
    }

    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
        
    private static void exchange(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}