package com.kennedysean.uva.volume1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.IOUtils;

public class Problem100Test {

    private static final String BASE_PATH = "volume1/problem100/";
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    ByteArrayOutputStream output = new ByteArrayOutputStream();;

    @Before
    public void setup() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void acceptSingleLine() throws IOException {
        Problem100.parseInputStream(classLoader.getResourceAsStream(BASE_PATH + "singleLine"));

        Assert.assertEquals("1 10 20", output.toString());
    }

    @Test
    public void acceptMultipleSpacesBetweenArguments() throws IOException {
        Problem100.parseInputStream(classLoader.getResourceAsStream(BASE_PATH + "singleLineManySpaces"));

        Assert.assertEquals("1 10 20", output.toString());
    }

    @Test
    public void sampleInputReturnsSampleOutput() throws IOException {
        Problem100.parseInputStream(classLoader.getResourceAsStream(BASE_PATH + "sampleInput"));

        Assert.assertEquals(
                IOUtils.toString(
                        classLoader.getResourceAsStream(BASE_PATH + "sampleOutput"),
                        "UTF-8"
                ),
                output.toString()
        );
    }
}