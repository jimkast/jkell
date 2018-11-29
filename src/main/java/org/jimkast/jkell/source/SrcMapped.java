package org.jimkast.jkell.source;

import org.jimkast.jkell.func.FuncMapped;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcMapped<A, B> implements Source<B> {
    private final Func<A, B> mapper;
    private final Source<A> origin;

    public SrcMapped(Func<A, B> mapper, Source<A> origin) {
        this.mapper = mapper;
        this.origin = origin;
    }

    @Override
    public <X> X feed(Func<B, X> target, X other) throws Exception {
        return origin.feed(new FuncMapped<>(mapper, target), other);
    }
}
