package org.jimkast.jkell.maybe;

import org.jimkast.jkell.types.Func;

public final class While<T> implements Maybe<T> {
    private final Func<T, Boolean> check;
    private final Maybe<T> origin;

    public While(Func<T, Boolean> check, Maybe<T> origin) {
        this.check = check;
        this.origin = origin;
    }

    @Override
    public <X> Func<X, X> map(Func<T, X> next) throws Exception {

        while (origin.map(check).apply(false)) {

        }
        return null;
    }
}
