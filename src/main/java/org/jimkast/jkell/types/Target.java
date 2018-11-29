package org.jimkast.jkell.types;

public interface Target<X> {
    void accept(X x) throws Exception;


    class Env<X> implements Target<X> {
        private final Target<X> origin;

        public Env(Target<X> origin) {
            this.origin = origin;
        }

        @Override
        public void accept(X x) throws Exception {
            origin.accept(x);
        }
    }
}
