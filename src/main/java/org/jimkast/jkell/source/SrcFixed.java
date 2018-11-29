package org.jimkast.jkell.source;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcFixed<T> implements Source<T> {
    private final T t;

    public SrcFixed(T t) {
        this.t = t;
    }

    @Override
    public <X> X feed(Func<T, X> target, X other) throws Exception {
        return target.apply(t);
    }
}
