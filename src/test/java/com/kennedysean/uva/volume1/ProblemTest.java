package com.kennedysean.uva.volume1;


import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import com.kennedysean.uva.Problem;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class ProblemTest {
    protected CharSource source(String resourceRelativePath) {
        URL resource = Resources.getResource(getClass(), resourceRelativePath);
        return Resources.asCharSource(resource, StandardCharsets.UTF_8);
    }

    protected String read(String resourceRelativePath) throws IOException {
        return source(resourceRelativePath).read();
    }

    protected String solve(CharSource input) throws IOException {
        Problem<?, ?> newProblem = newProblem();
        CharSource solution = newProblem.solve(input);
        return solution.read();
    }

    protected String solve(String resourceRelativePath) throws IOException {
        return solve(source(resourceRelativePath));
    }

    protected abstract Problem<?, ?> newProblem();
}
