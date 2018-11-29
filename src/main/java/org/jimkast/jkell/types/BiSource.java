package org.jimkast.jkell.types;

public interface BiSource<X, Y> {
    <Z> Z feed(BiFunc<X, Y, Z> target, Z other) throws Exception;


    class Env<X, Y> implements BiSource<X, Y> {
        private final BiSource<X, Y> origin;

        public Env(BiSource<X, Y> origin) {
            this.origin = origin;
        }

        @Override
        public <Z> Z feed(BiFunc<X, Y, Z> target, Z other) throws Exception {
            return origin.feed(target, other);
        }
    }
}
