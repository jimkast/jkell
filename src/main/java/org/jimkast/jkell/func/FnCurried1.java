package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FnCurried1<X, Y, Z> implements Func<Y, Z> {
    private final BiFunc<X, Y, Z> bi;
    private final X x;

    public FnCurried1(X x, BiFunc<X, Y, Z> bi) {
        this.bi = bi;
        this.x = x;
    }

    @Override
    public Z apply(Y y) throws Exception {
        return bi.apply(x, y);
    }
}
