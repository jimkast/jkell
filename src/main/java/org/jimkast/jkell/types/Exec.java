package org.jimkast.jkell.types;

public interface Exec {
    void run() throws Exception;


    class Env implements Exec {
        private final Exec origin;

        public Env(Exec origin) {
            this.origin = origin;
        }

        public void run() throws Exception {
            origin.run();
        }
    }
}
