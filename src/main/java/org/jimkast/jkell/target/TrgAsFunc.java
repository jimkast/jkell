package org.jimkast.jkell.target;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgAsFunc<X, Y> implements Func<X, Target<X>> {
    private final Target<X> target;

    public TrgAsFunc(Target<X> target) {
        this.target = target;
    }

    @Override
    public Target<X> apply(X x) throws Exception {
        target.accept(x);
        return target;
    }
}
