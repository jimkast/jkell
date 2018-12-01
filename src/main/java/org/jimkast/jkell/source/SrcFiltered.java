package org.jimkast.jkell.source;

import java.util.ArrayList;
import java.util.List;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class SrcFiltered<T> implements Source<T> {
    private final Func<T, Boolean> check;
    private final Source<T> origin;

    public SrcFiltered(Func<T, Boolean> check, Source<T> origin) {
        this.check = check;
        this.origin = origin;
    }

    @Override
    public <X> X feed(Func<T, X> target, X other) throws Exception {
        List<X> store = new ArrayList<>(1);
        Func<T, Boolean> st = t -> {
            if (check.apply(t)) {
                store.add(target.apply(t));
                return false;
            }
            return true;
        };
        while (origin.feed(st, false)) ;
        return store.isEmpty() ? other : store.get(0);
    }
}
