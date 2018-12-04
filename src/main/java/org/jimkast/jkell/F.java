package org.jimkast.jkell;

import org.jimkast.jkell.func.FnBoth;
import org.jimkast.jkell.func.FnCase;
import org.jimkast.jkell.func.FnCurried1;
import org.jimkast.jkell.func.FnFixed;
import org.jimkast.jkell.func.FnMapped;
import org.jimkast.jkell.func.FnOrElse;
import org.jimkast.jkell.func.FnReduced;
import org.jimkast.jkell.func.FnSelf;
import org.jimkast.jkell.source.SrcEmpty;
import org.jimkast.jkell.source.SrcFallback;
import org.jimkast.jkell.source.SrcForEach;
import org.jimkast.jkell.source.SrcMapped;
import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.BiTarget;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;
import org.jimkast.jkell.types.Target;

public final class F<X, Y> implements Func<X, Y> {
    private final Func<X, Y> origin;

    public F(Func<X, Y> origin) {
        this.origin = origin;
    }

    public <Z> F<X, Z> then(Func<Y, Z> next) {
        return new F<>(new FnMapped<>(origin, next));
    }

    public <Z> F<X, Z> bi(BiFunc<X, Y, Z> next) {
        return new F<>(new FnReduced<>(origin, next));
    }

    public Target<X> reduce(Target<Y> next) {
        return x -> next.accept(origin.apply(x));
    }

    public Target<X> reduce2(Target<Y> next) {
        return x -> next.accept(origin.apply(x));
    }

    public Target<X> reduce2(BiTarget<X, Y> next) {
        return x -> next.accept(x, origin.apply(x));
    }

    public <Z> PsComposer<X, Y, Z> fork(Func<Y, Source<Z>> next) {
        return new PsComposer<>(origin, next);
    }

    public <Z> PsComposer<X, Y, Z> fork(Func<Y, Boolean> check, Func<Y, Z> result) {
        return fork(new FnCase<>(check, result));
    }

    public <Z> PsComposer<X, Y, Z> fork(Func<Y, Boolean> check, Z result) {
        return fork(new FnCase<>(check, result));
    }

    @Override
    public Y apply(X x) throws Exception {
        return origin.apply(x);
    }


    public static final class PsComposer<A, X, Y> implements Func<A, Source<Y>> {
        private final Func<A, X> before;
        private final Func<X, Source<Y>> origin;

        public PsComposer(Func<A, X> before, Func<X, Source<Y>> origin) {
            this.before = before;
            this.origin = origin;
        }

        public <Z> PsComposer<A, X, Z> then(Func<Y, Z> next) {
            return new PsComposer<>(before, new FnMapped<>(origin, new FnCurried1<>(SrcMapped::new, next)));
        }

        public <Z> PsComposer<A, X, Z> pipeNested(Func<Y, Source<Z>> next) {
            return new PsComposer<>(before, new FnMapped<>(origin, src -> src.feed(next, SrcEmpty.instance())));
        }

        //        public PsComposer<X, Y> reduce(Func<Y, Y> reducer) {
//            return new PsComposer<>(
//                x -> origin.apply()
//            );
//        }
//
        public Target<A> foreach(Target<Y> target) {
            Func<A, Target<Target<Y>>> fn = new FnMapped<>(this, SrcForEach::new);
            return a -> fn.apply(a).accept(target);
        }

        public F<A, Y> orelse(Func<A, Y> other) {
            return new F<>(new FnOrElse<>(other, this));
        }

        public F<A, Y> orelse(Y other) {
            return orelse(new FnFixed<>(other));
        }

        public PsComposer<A, X, Y> fork(Func<X, Source<Y>> next) {
            return new PsComposer<>(before, new FnBoth<>(origin, next, SrcFallback::new));
        }


        public PsComposer<A, X, Y> fork(Func<X, Boolean> check, Func<X, Y> result) {
            return fork(new FnCase<>(check, result));
        }

        public PsComposer<A, X, Y> fork(Func<X, Boolean> check, Y result) {
            return fork(new FnCase<>(check, result));
        }

        @Override
        public Source<Y> apply(A a) throws Exception {
            return origin.apply(before.apply(a));
        }
    }


    public static <X, Y> F<X, Y> wrap(Func<X, Y> start) {
        return new F<>(start);
    }

    public static <X, Y> PsComposer<X, X, Y> ifelse(Func<X, Boolean> check, Y result) {
        return new PsComposer<>(FnSelf.instance(), new FnCase<>(check, result));
    }

    public static <X, Y> PsComposer<X, X, Y> ifelse(Func<X, Boolean> check, Func<X, Y> result) {
        return new PsComposer<>(FnSelf.instance(), new FnCase<>(check, result));
    }

    public static <X, Y> PsComposer<X, X, Y> maybe(Func<X, Source<Y>> start) {
        return new PsComposer<>(FnSelf.instance(), start);
    }

    public static <X, Y, Z> F<Y, Func<X, Z>> curried1(BiFunc<X, Y, Z> bi) {
        return new F<>(y -> x -> bi.apply(x, y));
    }

    public static <X, Y, Z> F<X, Func<Y, Z>> curried2(BiFunc<X, Y, Z> bi) {
        return new F<>(x -> y -> bi.apply(x, y));
    }

    public static <X, Y, Z> F<Y, Z> curry1(BiFunc<X, Y, Z> bi, X x) {
        return new F<>(y -> bi.apply(x, y));
    }

    public static <X, Y, Z> F<X, Z> curry2(BiFunc<X, Y, Z> bi, Y y) {
        return new F<>(x -> bi.apply(x, y));
    }

    public static <X, Y> Func<X, Target<Y>> trg_curried1(BiTarget<X, Y> bi) {
        return x -> y -> bi.accept(x, y);
    }

    public static <X, Y> Func<Y, Target<X>> trg_curried2(BiTarget<X, Y> bi) {
        return y -> x -> bi.accept(x, y);
    }

    public static <X, Y> Target<X> trg_curry1(BiTarget<X, Y> bi, Y y) {
        return x -> bi.accept(x, y);
    }

    public static <X, Y> Target<Y> trg_curry2(BiTarget<X, Y> bi, X x) {
        return y -> bi.accept(x, y);
    }

    public static <X, Y> Func<X, Y> fixed(Y y) {
        return x -> y;
    }

    private static final Func SELF = x -> x;

    @SuppressWarnings("unchecked")
    public static <X> Func<X, X> self() {
        return SELF;
    }
}
