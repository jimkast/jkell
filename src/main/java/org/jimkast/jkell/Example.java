package org.jimkast.jkell;

import org.jimkast.jkell.types.Target;

public final class Example {

    public static void main(String... args) throws Exception {
//        Exec app =
        Target<Integer> trg = Composer.<Integer, Integer>wrap(i -> i * 3)
            .and(i -> i + 1)
            .and(i -> i * 2)
            .fork(i -> i < 30, 30)
            .fork(i -> i < 50, 50)
            .orelse(100)
            .bi((i, result) -> i + ": " + result)
            .reduce(System.out::println);


        for (int i = 0; i < 30; i++) {
            trg.accept(i);
        }
    }
}
