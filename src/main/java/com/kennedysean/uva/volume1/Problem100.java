package com.kennedysean.uva.volume1;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Problem 100
 * The 3n + 1 problem
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
 */
public class Problem100 {

    private Splitter splitter = Splitter.on(' ').omitEmptyStrings().trimResults();
    final String INVALID_ARGUMENT_NUMBER = "Invalid number of arguments";

    /**
     * Get string output
     *
     * Retrieves the output string for a given input string
     *
     * @param input                     The input string
     * @return String
     * @throws InvalidArgumentNumber
     * @throws InvalidArgumentTypes
     * @throws InvalidArgumentRange
     * @throws IOException
     */
    public String getOutput(final String input) throws InvalidArgumentNumber, InvalidArgumentTypes, InvalidArgumentRange,
            IOException {

        // Minimum string length for 2 integers separated by a space is 3
        if (input.length() < 3) {
            throw new InvalidArgumentNumber(INVALID_ARGUMENT_NUMBER);
        }

        // Only accept integers, spaces, and newlines
        if (!input.matches("[0-9\\s\\n]+")) {
            throw new InvalidArgumentTypes("Arguments must match format [0-9\\s\\n]+");
        }

        BufferedReader reader = new BufferedReader(new StringReader(input));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        // Read input line by line
        while ((line = reader.readLine()) != null) {

            // Split line by spaces
            Iterable<String> elements = splitter.split(line);

            // Number of integers on the line must be 2
            if (Iterables.size(elements) != 2) {
                throw new InvalidArgumentNumber(INVALID_ARGUMENT_NUMBER);
            }

            // Each subsequent line from the input corresponds to a newline in the output
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append("\n");
            }

            // Retrieve both integers from the iterable
            int start = -1;
            int end = -1;

            for (String element : elements) {
                if (start == -1) {
                    start = parseIntegerInRange(element);

                }
                else if (end == -1) {
                    // Second element should be prefixed with a space
                    stringBuilder.append(" ");
                    end = parseIntegerInRange(element);

                }
                else {
                    throw new InvalidArgumentNumber(INVALID_ARGUMENT_NUMBER);
                }

                stringBuilder.append(element);
            }

            // Get the maximum cycle length given all numbers between start and end
            int maxCycleLength = getMaximumCycleLength(start, end);

            // Suffix the input line with a space and the max cycle length
            stringBuilder.append(" ");
            stringBuilder.append(String.valueOf(maxCycleLength));
        }

        return stringBuilder.toString();
    }

    private int getMaximumCycleLength(final int start, final int end) {
        // Mutable start and end so we can switch them if they weren't ordered correctly
        int rangeStart = start;
        int rangeEnd = end;

        // Swap start and end if start is greater than end
        if (start > end) {
            int temp = rangeStart;
            rangeStart = rangeEnd;
            rangeEnd = temp;
        }

        int maxCycleLength = 0;

        // For each integer between the start and end
        for (int i = rangeStart; i <= rangeEnd; i++) {
            // Get the cycle length
            int cycleLength = getCycleLength(i);

            // Update max cycle length if necessary
            if (cycleLength > maxCycleLength) {
                maxCycleLength = cycleLength;
            }
        }

        return maxCycleLength;
    }

    private int getCycleLength(final int integer) {
        // Mutable integer cursor
        int cursor = integer;
        int cycles = 1;

        // While not 1, apply algorithm for getting next integer and increment cycle count
        while (cursor != 1) {
            cursor = getNextInteger(cursor);
            cycles++;
        }

        return cycles;
    }

    private int getNextInteger(final int integer) {
        // If even, return n/2
        if (integer % 2 == 0) {
            return integer / 2;
        }

        // Otherwise return 3n + 1
        return integer * 3 + 1;
    }

    private int parseIntegerInRange(final String string) throws InvalidArgumentRange {
        int integer = Integer.parseInt(string);

        // Bounds are assumed in the problem, but are asserted here anyway
        if (integer <= 0 || integer >= 1000000) {
            throw new InvalidArgumentRange(string + " is not within the accepted range");
        }

        return integer;
    }

    public class InvalidArgumentNumber extends Exception {
        public InvalidArgumentNumber(String message) {
            super(message);
        }
    }

    public class InvalidArgumentTypes extends Exception {
        public InvalidArgumentTypes(String message) {
            super(message);
        }
    }

    public class InvalidArgumentRange extends Exception {
        public InvalidArgumentRange(String message) {
            super(message);
        }
    }
}