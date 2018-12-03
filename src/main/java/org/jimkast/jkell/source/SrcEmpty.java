package org.jimkast.jkell.source;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcEmpty<T> implements Source<T> {
    private static final Source INSTANCE = new SrcEmpty();

    @Override
    public <X> X feed(Func<T, X> target, X other) {
        return other;
    }

    @SuppressWarnings("unchecked")
    public static <T> Source<T> instance() {
        return INSTANCE;
    }
}
