package org.jimkast.jkell.target;

import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgCurry2<X, Y> implements Func<Y, Target<X>> {
    private final BiTarget<X, Y> target;

    public TrgCurry2(BiTarget<X, Y> target) {
        this.target = target;
    }

    @Override
    public Target<X> apply(Y y) throws Exception {
        return new TrgCurried2<>(y, target);
    }
}
