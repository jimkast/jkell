package org.jimkast.jkell.target;

import org.jimkast.jkell.types.Target;

public final class TrgEmpty<T> implements Target<T> {
    private static final Target INSTANCE = new TrgEmpty();

    @Override
    public void accept(T t) throws Exception {

    }

    @SuppressWarnings("unchecked")
    public static <T> Target<T> instance() {
        return INSTANCE;
    }
}
