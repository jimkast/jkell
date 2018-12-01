package org.jimkast.jkell;

import org.jimkast.jkell.func.FnBoth;
import org.jimkast.jkell.func.FnCase;
import org.jimkast.jkell.func.FnCurried1;
import org.jimkast.jkell.func.FnMapped;
import org.jimkast.jkell.func.FnReduced;
import org.jimkast.jkell.func.FnSelf;
import org.jimkast.jkell.source.SrcFallback;
import org.jimkast.jkell.source.SrcMapped;
import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;
import org.jimkast.jkell.types.Target;

public final class Composer<X, Y> implements Func<X, Y> {
    private final Func<X, Y> origin;

    public Composer(Func<X, Y> origin) {
        this.origin = origin;
    }

    public <Z> Composer<X, Z> and(Func<Y, Z> next) {
        return new Composer<>(new FnMapped<>(origin, next));
    }

    public <Z> Composer<X, Z> bi(BiFunc<X, Y, Z> next) {
        return new Composer<>(new FnReduced<>(origin, next));
    }

    public Target<X> reduce(Target<Y> next) {
        return x -> next.accept(origin.apply(x));
    }

    public Target<X> reduce2(BiTarget<X, Y> next) {
        return x -> next.accept(x, origin.apply(x));
    }

    public <Z> PsComposer<X, Z> possible(Func<Y, Source<Z>> next) {
        return new PsComposer<>(new FnMapped<>(origin, next));
    }

    public <Z> PsComposer<X, Z> fork(Func<Y, Source<Z>> next) {
        return new PsComposer<>(new FnMapped<>(origin, next));
    }

    public <Z> PsComposer<X, Z> fork(Func<Y, Boolean> check, Func<Y, Z> result) {
        return fork(new FnCase<>(check, result));
    }

    public <Z> PsComposer<X, Z> fork(Func<Y, Boolean> check, Z result) {
        return fork(new FnCase<>(check, result));
    }

    @Override
    public Y apply(X x) throws Exception {
        return origin.apply(x);
    }


    public static final class PsComposer<X, Y> implements Func<X, Source<Y>> {
        private final Func<X, Source<Y>> origin;

        public PsComposer(Func<X, Source<Y>> origin) {
            this.origin = origin;
        }


        public <Z> PsComposer<X, Z> map(Func<Y, Z> next) {
            return new PsComposer<>(new FnMapped<>(origin, new FnCurried1<>(SrcMapped::new, next)));
        }

//        public PsComposer<X, Y> reduce(Func<Y, Y> reducer) {
//            return new PsComposer<>(
//                x -> origin.apply()
//            );
//        }
//
//        public Composer<X, Target<Y>> foreach(Target<Y> target) {
//            return new Composer<>(
//                x -> origin.apply(x).feed()
//            );
//        }

        public Composer<X, Y> orelse(Y other) {
            return new Composer<>(
                x -> origin.apply(x).feed(FnSelf.instance(), other)
            );
        }

        public PsComposer<X, Y> fork(Func<X, Source<Y>> next) {
            return new PsComposer<>(new FnBoth<>(origin, next, SrcFallback::new));
        }

        public PsComposer<X, Y> fork(Func<X, Boolean> check, Func<X, Y> result) {
            return fork(new FnCase<>(check, result));
        }

        public PsComposer<X, Y> fork(Func<X, Boolean> check, Y result) {
            return fork(new FnCase<>(check, result));
        }

        @Override
        public Source<Y> apply(X x) throws Exception {
            return origin.apply(x);
        }
    }


    public static <X, Y> Composer<X, Y> wrap(Func<X, Y> start) {
        return new Composer<>(start);
    }

    public static <X, Y> PsComposer<X, Y> maybe(Func<X, Source<Y>> start) {
        return new PsComposer<>(start);
    }
}
