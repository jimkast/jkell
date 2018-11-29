package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FuncBiReduced1<X, Y, Z> implements Func<Y, Z> {
    private final BiFunc<X, Y, Z> bi;
    private final X x;

    public FuncBiReduced1(BiFunc<X, Y, Z> bi, X x) {
        this.bi = bi;
        this.x = x;
    }

    @Override
    public Z apply(Y y) throws Exception {
        return bi.apply(x, y);
    }
}
