package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FnBoth<A, B, C, D> implements Func<A, D> {
    private final Func<A, B> f1;
    private final Func<A, C> f2;
    private final BiFunc<B, C, D> reducer;

    public FnBoth(Func<A, B> f1, Func<A, C> f2, BiFunc<B, C, D> reducer) {
        this.f1 = f1;
        this.f2 = f2;
        this.reducer = reducer;
    }

    @Override
    public D apply(A x) throws Exception {
        return reducer.apply(f1.apply(x), f2.apply(x));
    }
}
