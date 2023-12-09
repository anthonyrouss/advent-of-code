package com.anthonyrouss.adventofcode.year2023;

import com.anthonyrouss.adventofcode.common.DaySolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01Solver implements DaySolver {

    private static final String FILE_PATH = "year2023/day01/data.txt";
    private int sum;

    private class Line {

        private final String text;
        private int calibrationValue, firstDigitPointer, lastDigitPointer;

        protected Line(String text) {
            this.firstDigitPointer = -1;
            this.lastDigitPointer = -1;
            this.text = text;
        }

        protected void calibratePartOne() {

            // Loop through each char from left to right to find the first digit
            for (int i = 0; i < text.length(); i++) {
                if (Character.isDigit(text.charAt(i))) {
                    firstDigitPointer = i;
                    break;
                }
            }

            if (firstDigitPointer == -1) {
                throw new RuntimeException(String.format("Line '%s' does not contain any digits.", text));
            }

            // Loop through each char from right to left to find the last digit
            for (int j = text.length() - 1; j >= firstDigitPointer; j--) {
                if (Character.isDigit(text.charAt(j))) {
                    lastDigitPointer = j;
                    break;
                }
            }

            // Combine the two digits to form a two-digit number
            calibrationValue = Integer.parseInt(String.valueOf(text.charAt(firstDigitPointer)) + text.charAt(lastDigitPointer));

        }

        protected int getCalibrationValue() {
            return calibrationValue;
        }
    }

    @Override
    public int solvePartOne() {
        sum = 0;

        try {
            // Fetch the text of the lines from the data text file
            List<String> linesText = fetchLinesText();

            // Loop through each line
            linesText.forEach(lineText -> {

                Line line = new Line(lineText);

                // Calibrate the line to find the two-digit calibration value
                line.calibratePartOne();

                // Add the calibration value to the sum
                sum += line.getCalibrationValue();

            });

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }

    @Override
    public int solvePartTwo() {
        return 0;
    }

    private List<String> fetchLinesText() throws URISyntaxException, IOException {
        return Files.readAllLines(Paths.get(getClass().getClassLoader().getResource(FILE_PATH).toURI()));
    }

}
