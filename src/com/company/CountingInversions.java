package com.company;


public final class CountingInversions {

    private CountingInversions() {
    }
    public static int countInversions(int[][] b) {
        int[] a = new int[(Puz.n * Puz.n)-1];
        int cnt=0;
        for (int i = 0; i < Puz.n; i++) {
            for (int j = 0; j < Puz.n; j++) {
                if(b[i][j]!=0)
                    a[cnt++] = b[i][j];
            }
        }
        return mergeSort(a, 0, a.length);
    }

    private static int mergeSort(int[] a, int low, int high) {
        if (low == high - 1) return 0;

        int mid = (low + high) / 2;

        return mergeSort(a, low, mid) + mergeSort(a, mid, high) + merge(a, low, mid, high);
    }

    private static int merge(int[] a, int low, int mid, int high) {
        int count = 0;
        int[] temp = new int[a.length];

        for (int i = low, lb = low, hb = mid; i < high; i++) {

            if (hb >= high || lb < mid && a[lb] <= a[hb]) {
                temp[i] = a[lb++];
            } else {
                count = count + (mid - lb);
                temp[i] = a[hb++];
            }
        }

        System.arraycopy(temp, low, a, low, high - low);

        return count;
    }

}
