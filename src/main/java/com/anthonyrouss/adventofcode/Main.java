package com.anthonyrouss.adventofcode;

import com.anthonyrouss.adventofcode.common.DaySolver;
import com.anthonyrouss.adventofcode.year2023.Day01Solver;

public class Main {
    private static DaySolver currentDaySolver = new Day01Solver();
    public static void main(String[] args) {
        System.out.println(currentDaySolver.solvePartOne());
    }
}
