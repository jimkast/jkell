package org.jimkast.jkell.source;

import org.jimkast.jkell.target.TrgBool;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;
import org.jimkast.jkell.types.Target;

public final class SrcForEach<T> implements Target<Target<T>> {
    private final Source<T> origin;

    public SrcForEach(Source<T> origin) {
        this.origin = origin;
    }

    @Override
    public void accept(Target<T> target) throws Exception {
        Func<T, Boolean> f = new TrgBool<>(target);
        while (origin.feed(f, false)) ;
    }
}
