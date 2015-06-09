package com.kennedysean.uva.common;

import java.util.function.Predicate;
import java.util.stream.Stream;


public class CountingFilter<T> implements Predicate<T> {
    public static <T> int countUntil(Stream<T> stream, Predicate<T> condition) {
        CountingFilter<T> stopAt = new CountingFilter<>(condition);
        stream.filter(stopAt).findFirst();
        return stopAt.count();
    }


    private final Predicate<T> delegate;
    private int count;

    public CountingFilter(Predicate<T> delegate) {
        this.delegate = delegate;
    }

    public int count() {
        return count;
    }

    @Override
    public boolean test(T t) {
        count++;
        return delegate.test(t);
    }
}
