package org.jimkast.jkell.func;

import org.jimkast.jkell.source.SrcEmpty;
import org.jimkast.jkell.source.SrcFixed;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class FnCase<X, Y> implements Func<X, Source<Y>> {
    private final Func<X, Boolean> check;
    private final Func<X, Source<Y>> result;

    public FnCase(Func<X, Boolean> check, Y result) {
        this(check, new FnFixed<>(result));
    }

    public FnCase(Func<X, Boolean> check, Func<X, Y> result) {
        this(true, check, new FnMapped<>(result, SrcFixed::new));
    }

    private FnCase(boolean b, Func<X, Boolean> check, Func<X, Source<Y>> result) {
        this.check = check;
        this.result = result;
    }

    @Override
    public Source<Y> apply(X x) throws Exception {
        return check.apply(x) ? result.apply(x) : SrcEmpty.instance();
    }
}
