package org.jimkast.jkell;

public final class Example {

    public static void main(String... args) throws Exception {
        Composer<Integer, Integer> app = Composer.<Integer, Integer>wrap(i -> i * 3)
            .and(i -> i + 1)
            .and(i -> i * 2)
            .fork(i -> i < 30, 30)
            .fork(i -> i < 50, 50)
            .orelse(100);


        for (int i = 0; i < 30; i++) {
            System.out.println(i + ": " + app.apply(i));
        }
    }
}
