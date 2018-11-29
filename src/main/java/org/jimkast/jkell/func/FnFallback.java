package org.jimkast.jkell.func;

import java.util.Arrays;
import org.jimkast.jkell.source.SrcEmpty;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;

public final class FnFallback<K, V> implements Func<K, Source<V>> {
    private final Iterable<Func<K, Source<V>>> all;

    @SafeVarargs
    public FnFallback(Func<K, Source<V>>... all) {
        this(Arrays.asList(all));
    }

    public FnFallback(Iterable<Func<K, Source<V>>> all) {
        this.all = all;
    }

    @Override
    public Source<V> apply(K key) throws Exception {
        Func<V, Boolean> True = new FnFixed<>(true);
        for (Func<K, Source<V>> mapping : all) {
            if (mapping.apply(key).feed(True, false)) {
                return mapping.apply(key);
            }
        }
        return SrcEmpty.instance();
    }
}
