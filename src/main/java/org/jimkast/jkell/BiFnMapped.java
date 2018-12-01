package org.jimkast.jkell;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class BiFnMapped<A, B, C, D> implements BiFunc<A, B, D> {
    private final BiFunc<A, B, C> f1;
    private final Func<C, D> f2;

    public BiFnMapped(BiFunc<A, B, C> f1, Func<C, D> f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public D apply(A a, B b) throws Exception {
        return f2.apply(f1.apply(a, b));
    }
}
