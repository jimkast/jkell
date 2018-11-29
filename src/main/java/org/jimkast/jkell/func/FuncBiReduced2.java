package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FuncBiReduced2<X, Y, Z> implements Func<X, Z> {
    private final BiFunc<X, Y, Z> bi;
    private final Y y;

    public FuncBiReduced2(BiFunc<X, Y, Z> bi, Y y) {
        this.bi = bi;
        this.y = y;
    }

    @Override
    public Z apply(X x) throws Exception {
        return bi.apply(x, y);
    }
}
