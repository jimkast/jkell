package org.jimkast.jkell.target;

import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgCurry1<X, Y> implements Func<X, Target<Y>> {
    private final BiTarget<X, Y> target;

    public TrgCurry1(BiTarget<X, Y> target) {
        this.target = target;
    }

    @Override
    public Target<Y> apply(X x) throws Exception {
        return new TrgCurried1<>(x, target);
    }


}
