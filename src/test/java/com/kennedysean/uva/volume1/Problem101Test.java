package com.kennedysean.uva.volume1;

import com.kennedysean.uva.volume1.problem101.Problem101;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Problem101Test {

    private static final String BASE_PATH = "com/kennedysean/uva/volume1/problem101/";
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    ByteArrayOutputStream output = new ByteArrayOutputStream();;

    @Before
    public void setup() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void quittingWithoutCommandsPrintsBlocks() throws IOException {
        Problem101.parseInputStream(classLoader.getResourceAsStream(BASE_PATH + "quickQuitInput"));

        Assert.assertEquals(
                IOUtils.toString(
                        classLoader.getResourceAsStream(
                                BASE_PATH + "quickQuitOutput"
                        ),
                        "UTF-8"
                ),
                output.toString()
        );
    }

    @Test
    public void sampleInputMatchesSampleOutput() throws IOException {
        Problem101.parseInputStream(classLoader.getResourceAsStream(BASE_PATH + "sampleInput"));

        Assert.assertEquals(
                IOUtils.toString(
                        classLoader.getResourceAsStream(BASE_PATH + "sampleOutput"),
                        "UTF-8"
                ),
                output.toString()
        );
    }
}