package org.jimkast.jkell.func;

import org.jimkast.jkell.types.Func;

public final class FnFixed<X, Y> implements Func<X, Y> {
    private final Y y;

    public FnFixed(Y y) {
        this.y = y;
    }

    @Override
    public Y apply(X t) throws Exception {
        return y;
    }
}
