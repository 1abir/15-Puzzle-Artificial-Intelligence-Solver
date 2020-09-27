package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int [][] goal = Main.takeInput(scanner);
        Puz.dest = goal;
        for (int i = 1; i < n ; i++) {
            int [][] startState = Main.takeInput(scanner);
            System.out.println("Case : "+i);
            System.out.println("Misplaced Tiles -");
            Puz.huristic_algorithm = 0;
            Main.solve(startState);
            Puz.huristic_algorithm = 1;
            System.out.println("\n\nManhattan Distance");
            Main.solve(startState);
        }


    }

    private static void solve(int [][]start)
    {
        Puz puzzle = new Puz(start);
        if(!puzzle.legal(start))
        {
            System.out.println("Unsolvable : "+Arrays.deepToString(start));
            return;
        }
        puzzle.statePriorityQueue.add(new State(puzzle.start,0,null,'0'));
        puzzle.arrayHashes.add(new ArrayHash(puzzle.start));
        int n=0;
        while (!puzzle.statePriorityQueue.isEmpty())
        {
            State state = puzzle.statePriorityQueue.poll();
            puzzle.arrayHashes.add(new ArrayHash(state.puz));
            if(Puz.equal(state.puz,Puz.dest))
            {
                State state1 = state;

                Stack<Character> stack = new Stack<>();
                while (state1.parent!=null)
                {
                    stack.push(state1.dir);
                    state1=state1.parent;
                }

                while (!stack.empty())
                    System.out.print(stack.pop());
                System.out.println("\n"+ state.level);
                System.out.println("Number of expanded nodes : "+n);
//                System.out.println(puzzle.statePriorityQueue.size());
                return;
            }
            puzzle.getChildren(state);
            n++;
        }
    }

    private static int[][] takeInput(Scanner scanner) {
        int [][]a = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j]=scanner.nextInt();
            }
        }
        return a;
    }
}