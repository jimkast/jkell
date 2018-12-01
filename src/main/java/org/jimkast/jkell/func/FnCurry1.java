package org.jimkast.jkell.func;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;

public final class FnCurry1<X, Y, Z> implements Func<X, Func<Y, Z>> {
    private final BiFunc<X, Y, Z> bi;

    public FnCurry1(BiFunc<X, Y, Z> bi) {
        this.bi = bi;
    }

    @Override
    public Func<Y, Z> apply(X x) throws Exception {
        return new FnCurried1<>(bi, x);
    }
}
