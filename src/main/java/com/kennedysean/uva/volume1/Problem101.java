package com.kennedysean.uva.volume1;

import com.google.common.base.Splitter;
import com.kennedysean.uva.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Problem 101
 * The blocks problem
 * http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=37
 */
public class Problem101 extends Problem {

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
    
    }
}