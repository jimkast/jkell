package org.jimkast.jkell.target;

import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class TrgBool<T> implements Func<T, Boolean> {
    private final Target<T> target;

    public TrgBool(Target<T> target) {
        this.target = target;
    }

    @Override
    public Boolean apply(T t) throws Exception {
        target.accept(t);
        return true;
    }
}
