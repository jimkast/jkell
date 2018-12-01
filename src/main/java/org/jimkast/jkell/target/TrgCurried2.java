package org.jimkast.jkell.target;

import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Target;

public final class TrgCurried2<X, Y> implements Target<X> {
    private final Y y;
    private final BiTarget<X, Y> target;

    public TrgCurried2(Y y, BiTarget<X, Y> target) {
        this.y = y;
        this.target = target;
    }

    @Override
    public void accept(X x) throws Exception {
        target.accept(x, y);
    }
}
