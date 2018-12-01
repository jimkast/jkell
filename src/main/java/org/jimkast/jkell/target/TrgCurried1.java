package org.jimkast.jkell.target;

import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Target;

public final class TrgCurried1<X, Y> implements Target<Y> {
    private final X x;
    private final BiTarget<X, Y> target;

    public TrgCurried1(X x, BiTarget<X, Y> target) {
        this.x = x;
        this.target = target;
    }

    @Override
    public void accept(Y y) throws Exception {
        target.accept(x, y);
    }
}
