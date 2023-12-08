package com.anthonyrouss.adventofcode.year2023;

import com.anthonyrouss.adventofcode.common.DaySolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day01Solver implements DaySolver {

    private static String FILE_PATH = "year2023/day01/data.txt";
    private int sum, firstDigitPointer, lastDigitPointer;

    @Override
    public int solvePartOne() {
        sum = 0;

        try {
            // Fetch the lines from the data text file
            List<String> lines = fetchLines();

            // Loop through each line
            lines.forEach(line -> {

                firstDigitPointer = -1;
                lastDigitPointer = -1;

                // Loop through each char from left to right to find the first digit
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        firstDigitPointer = i;
                        break;
                    }
                }

                if (firstDigitPointer == -1) {
                    throw new RuntimeException(String.format("Line '%s' does not contain any digits.", line));
                }

                // Loop through each char from right to left to find the last digit
                for (int j = line.length() - 1; j >= firstDigitPointer; j--) {
                    if (Character.isDigit(line.charAt(j))) {
                        lastDigitPointer = j;
                        break;
                    }
                }

                // Combine the two digits to form a two-digit number
                int twoDigitNum = Integer.parseInt(String.valueOf(line.charAt(firstDigitPointer)) + line.charAt(lastDigitPointer));

                // Add the two-digit number to the sum
                sum += twoDigitNum;

            });


        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return sum;
        }
    }

    @Override
    public int solvePartTwo() {
        return 0;
    }

    private List<String> fetchLines() throws URISyntaxException, IOException {
        return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(FILE_PATH).toURI()));
    }

}
