package com.company;

import java.util.*;

class Puz {
    int [][]start;
    static int [][]dest = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    PriorityQueue<State> statePriorityQueue;
    Vector<int [][]> arrayWraps;
    HashSet<ArrayHash> arrayHashes;
    static int n=4;
    static int huristic_algorithm=0;

    Puz(int [][]s)
    {
        statePriorityQueue = new PriorityQueue<>();
        arrayWraps = new Vector<>();
        arrayHashes = new HashSet<>();
        if(s==null)
        {
            //int [][]b={{1,2,3,4},{5,6,0,8},{9,10,7,11},{13,14,15,12}};
            //int [][]b={{6,13,7,10},{8,9,11,0},{15,2,12,5},{14,3,1,4}};
            int [][]b={{2,3,4,0},{1,5,7,8},{9,6,10,12},{13,14,11,15}};
            start=b;
        }
        else {
            start = s;
        }
        /*if(!legal(start))
        {
            System.out.println("Invalid start state");
            return;
        }*/

        //int [][] a={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};

    }
    static int H(int [][]A) {
        int h = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (A[r][c] == 0) continue;
                int expect_r = (A[r][c] - 1) / 4;
                int expect_c = (A[r][c] - 1) % 4;
                h += Math.abs(expect_r - r) + Math.abs(expect_c - c);
            }
        }
        return h;
    }
    static int h(int [][]a)
    {
        int cnt=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(a[i][j]!=dest[i][j]&&a[i][j]!=0)
                    cnt++;
            }
        }
        return cnt;
    }
    Vector<int []> getDirection(int i,int j)
    {
        //int [][]a={{i-1,j},{i,j-1},{i+1,j},{i,j+1}};
        Vector<int []> childs = new Vector<>();
        if(i-1>=0)
            childs.add(new int[]{i-1,j,'U'});
        if(j-1>=0)
            childs.add(new int[]{i,j-1,'L'});
        if (i+1<n)
            childs.add(new int[]{i+1,j,'D'});
        if (j+1<n)
            childs.add(new int[]{i,j+1,'R'});


        return childs;
    }
    int [] find0(int [][]a)
    {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(a[i][j]==0)
                {
                    int []b={i,j};
                    return b;
                }
            }
        }
        return null;
    }
    int [][]swap(int [][]a,int []zero, int []b)
    {
        int [][]c = copy(a);
        c[zero[0]][zero[1]]= a[b[0]][b[1]];
        c[b[0]][b[1]] = a[zero[0]][zero[1]];
        return c;
    }
    boolean legal(int [][]a)
    {
        if((CountingInversions.countInversions(a)+find0(a)[0])%2==1)
            return true;
        return false;
    }
    void getChildren(State state)
    {
        int [] zero = find0(state.puz);
        Vector<int[]> directions = getDirection(zero[0],zero[1]);
        for (int[] a :
                directions) {
            int[][] child = swap(state.puz, zero, a);

            if( (!arrayHashes.contains(new ArrayHash(child)))  ) {
                statePriorityQueue.add(new State(child, state.level + 1,state,(char)a[2]));
//                arrayHashes.add(new ArrayHash(child));
            }
        }
    }
    private int [][] copy(int[][] a)
    {
        int [][]b=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j]=a[i][j];
            }
        }
        return b;
    }

    static boolean equal(final int[][] arr1, final int[][] arr2) {

        if (arr1 == null) {
            return (arr2 == null);
        }
        if (arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) {
                return false;
            }
        }
        return true;
    }
}
