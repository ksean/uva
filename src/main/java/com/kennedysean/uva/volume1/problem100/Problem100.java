package com.kennedysean.uva.volume1.problem100;

import com.google.auto.value.AutoValue;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.kennedysean.uva.Problem;

import java.util.List;

import static autovalue.shaded.com.google.common.common.collect.Iterables.transform;
import static com.kennedysean.uva.common.CountingFilter.countUntil;
import static java.util.stream.Stream.iterate;

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

        int i = Integer.parseInt(elements.get(0));
        int j = Integer.parseInt(elements.get(1));

        return InputLine.create(i, j);
    }

    @AutoValue
    static abstract class InputLine implements Problem.OutputPrinter {
        public static InputLine create(int i, int j) {
            return new AutoValue_Problem100_InputLine(i, j);
        }

        public abstract int i();
        public abstract int j();

        public Range<Integer> range() {
            int rangeStart = Integer.min(i(), j());
            int rangeEnd = Integer.max(i(), j());
            return Range.closed(rangeStart, rangeEnd);
        }

        @Override
        public String print() {
            return String.format("%s %s", i(), j());
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
        int output = maxCycle(input.range());
        return OutputLine.create(input, output);
    }

    int maxCycle(Range<Integer> range) {
        return ContiguousSet.create(range, DiscreteDomain.integers())
                .stream()
                .map(initial -> countUntil(iterate(
                    (long) initial,
                    next -> (next % 2 == 0)
                            ? next / 2
                            : next * 3 + 1),
                    last -> last == 1))
                .max(Ordering.natural())
                .orElse(0);
    }
}