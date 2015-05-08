package com.kennedysean.uva;

public class Problem {

    protected class IllegalArgumentNumber extends IllegalArgumentException {
        public IllegalArgumentNumber(String message) {
            super(message);
        }
    }

    protected class IllegalArgumentType extends IllegalArgumentException {
        public IllegalArgumentType(String message) {
            super(message);
        }
    }

    protected class IllegalArgumentRange extends IllegalArgumentException {
        public IllegalArgumentRange(String message) {
            super(message);
        }
    }
}
