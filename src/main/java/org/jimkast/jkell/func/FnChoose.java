package org.jimkast.jkell.func;

import java.util.Arrays;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class FnChoose<X, Y> implements Func<X, Y> {
    private final Func<X, Y> def;
    private final Func<X, Source<Y>> cases;

    @SafeVarargs
    public FnChoose(Y def, Func<X, Source<Y>>... cases) {
        this(new FnFixed<>(def), cases);
    }

    @SafeVarargs
    public FnChoose(Func<X, Y> def, Func<X, Source<Y>>... cases) {
        this(def, Arrays.asList(cases));
    }

    public FnChoose(Func<X, Y> def, Iterable<Func<X, Source<Y>>> cases) {
        this.def = def;
        this.cases = new FnAny<>(cases);
    }

    @Override
    public Y apply(X x) throws Exception {
        return cases.apply(x).<Func<X, Y>>feed(FnFixed::new, def).apply(x);
    }
}