package org.jimkast.jkell.func;

import java.util.Arrays;
import org.jimkast.jkell.source.SrcOfIterator;
import org.jimkast.jkell.source.SrcMapped;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class FnAll<X, Y> implements Func<X, Source<Y>> {
    private final Iterable<Func<X, Y>> all;

    @SafeVarargs
    public FnAll(Func<X, Y>... all) {
        this(Arrays.asList(all));
    }

    public FnAll(Iterable<Func<X, Y>> all) {
        this.all = all;
    }

    @Override
    public Source<Y> apply(X x) {
        return new SrcMapped<>(o -> o.apply(x), new SrcOfIterator<>(all.iterator()));
    }
}
