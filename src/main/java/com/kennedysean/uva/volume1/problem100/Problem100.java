package com.kennedysean.uva.volume1.problem100;

import com.google.auto.value.AutoValue;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.kennedysean.uva.Problem;

import java.util.List;

import static autovalue.shaded.com.google.common.common.collect.Iterables.transform;

/**
 * Problem 100
 * The 3n + 1 problem
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
 */
public class Problem100 extends Problem<Problem100.InputLine, Problem100.OutputLine> {

    private static final Splitter splitter = Splitter.on(' ').omitEmptyStrings().trimResults();


    @Override
    protected ImmutableList<OutputLine> solution(List<InputLine> input) {
        return ImmutableList.copyOf(transform(input, this::solution));
    }

    @Override
    protected InputLine parse(String line) {
        List<String> elements = splitter.splitToList(line);

        int start = Integer.parseInt(elements.get(0));
        int end = Integer.parseInt(elements.get(1));

        return InputLine.create(start, end);
    }

    @AutoValue
    static abstract class InputLine implements Problem.OutputPrinter {
        public static InputLine create(int start, int end) {
            return new AutoValue_Problem100_InputLine(start, end);
        }

        public abstract int start();
        public abstract int end();

        @Override
        public String print() {
            return String.format("%s %s", start(), end());
        }
    }

    @AutoValue
    static abstract class OutputLine implements Problem.OutputPrinter {
        public static OutputLine create(InputLine inputLine, int cycles) {
            return new AutoValue_Problem100_OutputLine(inputLine, cycles);
        }

        public abstract InputLine inputLine();
        public abstract int cycles();

        @Override
        public String print() {
            return String.format("%s %s", inputLine().print(), cycles());
        }
    }


    OutputLine solution(InputLine input) {
        int cycles = getMaximumCycleLength(input.start(), input.end());
        return OutputLine.create(input, cycles);
    }

    int getMaximumCycleLength(int start, int end) {
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

    int getCycleLength(int integer) {
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

    int getNextInteger(int integer) {
        // If even, return n/2
        if (integer % 2 == 0) {
            return integer / 2;
        }

        // Otherwise return 3n + 1
        return integer * 3 + 1;
    }
}