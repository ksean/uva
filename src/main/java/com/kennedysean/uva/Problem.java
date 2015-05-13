package com.kennedysean.uva;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import static com.google.common.collect.Iterables.transform;

public abstract class Problem<I, O extends Problem.OutputPrinter> {
    public interface OutputPrinter {
        String print();
    }

    public CharSource solve(CharSource input) {
        try {
            return solveChecked(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private CharSource solveChecked(CharSource input) throws IOException {
        ImmutableList<String> lines = input.readLines();

        ImmutableList<I> inputs = ImmutableList.copyOf(transform(lines, this::parse));

        ImmutableList<O> outputs = solution(inputs);

        String output = Joiner.on("\n").join(transform(outputs, OutputPrinter::print));

        return CharSource.wrap(output);
    }

    protected abstract ImmutableList<O> solution(List<I> input);
    protected abstract I parse(String line);
}
