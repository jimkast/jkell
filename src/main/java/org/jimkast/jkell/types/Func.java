package org.jimkast.jkell.types;

public interface Func<X, Y> {
    Y apply(X x) throws Exception;


    class Env<X, Y> implements Func<X, Y> {
        private final Func<X, Y> origin;

        public Env(Func<X, Y> origin) {
            this.origin = origin;
        }

        @Override
        public Y apply(X x) throws Exception {
            return origin.apply(x);
        }
    }
}
