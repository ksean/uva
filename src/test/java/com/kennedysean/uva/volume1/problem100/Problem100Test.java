package com.kennedysean.uva.volume1.problem100;

import com.kennedysean.uva.Problem;
import com.kennedysean.uva.volume1.ProblemTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem100Test extends ProblemTest {
    @Override
    protected Problem<?, ?> newProblem() {
        return new Problem100();
    }

    @Test
    public void singleLine() throws IOException {
        assertThat(solve("singleLine"))
                .isEqualTo("1 10 20");
    }

    @Test
    public void singleLineManySpaces() throws IOException {
        assertThat(solve("singleLineManySpaces"))
                .isEqualTo("1 10 20");
    }

    @Test
    public void sampleInput() throws IOException {
        assertThat(solve("sampleInput"))
                .isEqualTo(read("sampleOutput"));
    }
}