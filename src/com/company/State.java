package com.company;

class State implements Comparable<State>
{
    int [][]puz;
    int level;
    int heuristic;
    State parent;
    char dir;

    State(int[][] puz, int level,State parent,char dir) {
        this.puz = puz;
        this.level = level;
        this.parent = parent;
        this.dir = dir;
        if(Puz.huristic_algorithm==0)
            heuristic = level + Puz.h(puz);
        else if(Puz.huristic_algorithm==1)
            heuristic = level + Puz.H(puz);
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(heuristic,o.heuristic);
    }
}