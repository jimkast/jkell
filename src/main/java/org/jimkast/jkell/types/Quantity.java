package org.jimkast.jkell.types;

public interface Quantity {
    int length() throws Exception;


    class Env implements Quantity {
        private final Quantity origin;

        public Env(Quantity origin) {
            this.origin = origin;
        }

        @Override
        public final int length() throws Exception {
            return origin.length();
        }
    }
}
