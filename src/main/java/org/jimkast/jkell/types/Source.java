package org.jimkast.jkell.types;

public interface Source<T> {
    <X> X feed(Func<T, X> target, X other) throws Exception;


    class Env<T> implements Source<T> {
        private final Source<T> origin;

        public Env(Source<T> origin) {
            this.origin = origin;
        }

        @Override
        public final <X> X feed(Func<T, X> target, X other) throws Exception {
            return origin.feed(target, other);
        }
    }
}
