package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FnCurry2<X, Y, Z> implements Func<Y, Func<X, Z>> {
    private final BiFunc<X, Y, Z> bi;

    public FnCurry2(BiFunc<X, Y, Z> bi) {
        this.bi = bi;
    }

    @Override
    public Func<X, Z> apply(Y y) throws Exception {
        return new FnCurried2<>(y, bi);
    }
}
