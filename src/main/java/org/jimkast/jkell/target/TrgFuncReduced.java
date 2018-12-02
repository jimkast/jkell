package org.jimkast.jkell.target;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgFuncReduced<X, Y> implements Target<X> {
    private final Func<X, Y> fn;
    private final Target<Y> target;

    public TrgFuncReduced(Func<X, Y> fn, Target<Y> target) {
        this.fn = fn;
        this.target = target;
    }

    @Override
    public void accept(X x) throws Exception {
        target.accept(fn.apply(x));
    }
}
