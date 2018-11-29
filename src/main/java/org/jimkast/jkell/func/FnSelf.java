package org.jimkast.jkell.func;

import org.jimkast.jkell.types.Func;

public final class FnSelf<T> implements Func<T, T> {
    private static final Func INSTANCE = new FnSelf();

    @Override
    public T apply(T t) throws Exception {
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <X> Func<X, X> instance() {
        return (Func<X, X>) INSTANCE;
    }
}
