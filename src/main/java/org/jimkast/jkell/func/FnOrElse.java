package org.jimkast.jkell.func;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class FnOrElse<X, Y> implements Func<X, Y> {
    private final Y other;
    private final Func<X, Source<Y>> maybe;

    public FnOrElse(Y other, Func<X, Source<Y>> maybe) {
        this.other = other;
        this.maybe = maybe;
    }

    @Override
    public Y apply(X x) throws Exception {
        return maybe.apply(x).feed(FnSelf.instance(), other);
    }
}
