package com.company;

import java.util.Arrays;

public class ArrayHash {
    int [][]a;

    public ArrayHash(int[][] a) {
        this.a = a;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(a);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ArrayHash))
            return false;
        return Puz.equal(a,((ArrayHash)obj).a);
    }
}
