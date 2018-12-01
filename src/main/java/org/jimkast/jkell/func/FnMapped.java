package org.jimkast.jkell.func;

import org.jimkast.jkell.types.Func;

public final class FnMapped<X, Y, Z> implements Func<X, Z> {
    private final Func<X, Y> f1;
    private final Func<Y, Z> f2;

    public FnMapped(Func<X, Y> f1, Func<Y, Z> f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public Z apply(X x) throws Exception {
        return f2.apply(f1.apply(x));
    }
}
