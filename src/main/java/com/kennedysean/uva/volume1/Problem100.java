package com.kennedysean.uva.volume1;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Problem 100
 * The 3n + 1 problem
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
 */
public class Problem100 {

    private static Splitter splitter = Splitter.on(' ').omitEmptyStrings().trimResults();
    private static StringBuilder outputBuilder;

    public static void main(String[] args) throws IOException {
        parseInputStream(System.in);
    }

    public static void parseInputStream(InputStream inputStream) throws IOException {
        outputBuilder = new StringBuilder();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        // Read input line by line
        while ((line = bufferedReader.readLine()) != null) {
            parseLine(line);
        }

        inputStream.close();
        System.out.print(outputBuilder.toString());
    }

    private static void parseLine(final String line) {

        // Split line by spaces
        List<String> elements = splitter.splitToList(line);
        String firstInteger = elements.get(0);
        String secondInteger = elements.get(1);

        // Each subsequent line from the input corresponds to a newline in the output
        if (outputBuilder.length() != 0) {
            outputBuilder.append("\n");
        }

        outputBuilder.append(firstInteger);
        outputBuilder.append(" ");
        outputBuilder.append(secondInteger);

        // Get the maximum cycle length given all numbers between start and end
        int maxCycleLength = getMaximumCycleLength(
                Integer.parseInt(firstInteger),
                Integer.parseInt(secondInteger)
        );

        // Suffix the input line with a space and the max cycle length
        outputBuilder.append(" ");
        outputBuilder.append(String.valueOf(maxCycleLength));
    }

    private static int getMaximumCycleLength(final int start, final int end) {
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

    private static int getCycleLength(final int integer) {
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

    private static int getNextInteger(final int integer) {
        // If even, return n/2
        if (integer % 2 == 0) {
            return integer / 2;
        }

        // Otherwise return 3n + 1
        return integer * 3 + 1;
    }
}