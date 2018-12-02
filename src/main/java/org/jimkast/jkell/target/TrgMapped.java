package org.jimkast.jkell.target;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgMapped<X, Y> implements Target<X> {
    private final Func<X, Y> mapper;
    private final Target<Y> origin;

    public TrgMapped(Func<X, Y> mapper, Target<Y> origin) {
        this.mapper = mapper;
        this.origin = origin;
    }

    @Override
    public void accept(X x) throws Exception {
        origin.accept(mapper.apply(x));
    }
}
