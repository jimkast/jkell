package org.jimkast.jkell.types;

public interface BiFunc<X, Y, Z> {
    Z apply(X x, Y y) throws Exception;


    class Env<X, Y, Z> implements BiFunc<X, Y, Z> {
        private final BiFunc<X, Y, Z> origin;

        public Env(BiFunc<X, Y, Z> origin) {
            this.origin = origin;
        }

        @Override
        public Z apply(X x, Y y) throws Exception {
            return origin.apply(x, y);
        }
    }
}
