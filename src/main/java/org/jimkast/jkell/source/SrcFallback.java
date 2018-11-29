package org.jimkast.jkell.source;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcFallback<T> implements Source<T> {
    private final Source<T> main;
    private final Source<T> fallback;

    public SrcFallback(Source<T> main, Source<T> fallback) {
        this.main = main;
        this.fallback = fallback;
    }

    @Override
    public <X> X feed(Func<T, X> target, X other) throws Exception {
        return main.feed(SrcFixed::new, fallback).feed(target, other);
    }
}
