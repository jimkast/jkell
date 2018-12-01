package org.jimkast.jkell.types;

public interface BiTarget<X, Y> {
    void accept(X x, Y y) throws Exception;


    class Env<X, Y> implements BiTarget<X, Y> {
        private final BiTarget<X, Y> origin;

        public Env(BiTarget<X, Y> origin) {
            this.origin = origin;
        }

        @Override
        public void accept(X x, Y y) throws Exception {
            origin.accept(x, y);
        }
    }
}
