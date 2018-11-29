package org.jimkast.jkell.source;

import java.util.Iterator;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcOfIterator<T> implements Source<T> {
    private final Iterator<T> iter;

    public SrcOfIterator(Iterator<T> iter) {
        this.iter = iter;
    }

    @Override
    public <X> X feed(Func<T, X> target, X other) throws Exception {
        return iter.hasNext() ? target.apply(iter.next()) : other;
    }
}
