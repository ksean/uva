package com.kennedysean.uva.volume1;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Problem100Test {

    Problem100 problem100 = new Problem100();

    @Test (expected = Problem100.InvalidArgumentTypes.class)
    public void rejectCharactersInInput() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        problem100.getOutput("1 a");
    }

    @Test (expected = Problem100.InvalidArgumentTypes.class)
    public void rejectFloatInInput() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        problem100.getOutput("1.0 1");
    }

    @Test (expected = Problem100.InvalidArgumentNumber.class)
    public void rejectTooFewArguments() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        problem100.getOutput("1");
    }

    @Test (expected = Problem100.InvalidArgumentNumber.class)
    public void rejectTooManyArguments() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        problem100.getOutput("1 1 1");
    }

    @Test (expected = Problem100.InvalidArgumentRange.class)
    public void rejectArgumentOutOfRange() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        problem100.getOutput("0 1");
    }

    @Test
    public void acceptSingleLine() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        String output = problem100.getOutput("1 10");

        Assert.assertEquals("1 10 20", output);
    }

    @Test
    public void acceptMultipleSpacesBetweenArguments() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        String output = problem100.getOutput("1     10");

        Assert.assertEquals("1 10 20", output);
    }

    @Test
    public void acceptMultipleLines() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        String output = problem100.getOutput("1 10\n900 1000     ");

        Assert.assertEquals("1 10 20\n900 1000 174", output);
    }

    @Test
    public void sampleInputReturnsSampleOutput() throws Problem100.InvalidArgumentNumber, Problem100.InvalidArgumentTypes,
            Problem100.InvalidArgumentRange, IOException {

        String output = problem100.getOutput("1 10\n100 200\n201 210\n900 1000");

        Assert.assertEquals("1 10 20\n100 200 125\n201 210 89\n900 1000 174", output);
    }
}
