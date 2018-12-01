package org.jimkast.jkell.source;

import java.util.ArrayList;
import java.util.List;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcFlattened<T> implements Source<T> {
    private final Source<Source<T>> all;
    private final List<Source<T>> store = new ArrayList<>(1);

    public SrcFlattened(Source<Source<T>> all) {
        this.all = all;
    }

    @Override
    public <X> X feed(Func<T, X> target, X other) throws Exception {
        Func<Source<T>, Boolean> st = key -> {
            store.add(key);
            return true;
        };
        while (store.size() == 0 && all.feed(st, false)) {
            return store.get(0).feed(target, other);
        }
        return other;
    }
}
