package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FnReduced<X, Y, Z> implements Func<X, Z> {
    private final Func<X, Y> f1;
    private final BiFunc<X, Y, Z> f2;

    public FnReduced(Func<X, Y> f1, BiFunc<X, Y, Z> f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public Z apply(X x) throws Exception {
        return f2.apply(x, f1.apply(x));
    }

}
