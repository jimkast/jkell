//package org.jimkast.jkell;
//
//import org.jimkast.jkell.func.FnBoth;
//import org.jimkast.jkell.func.FnCase;
//import org.jimkast.jkell.func.FnCurried1;
//import org.jimkast.jkell.func.FnMapped;
//import org.jimkast.jkell.func.FnSelf;
//import org.jimkast.jkell.source.SrcFallback;
//import org.jimkast.jkell.source.SrcMapped;
//import org.jimkast.jkell.types.BiFunc;
//import org.jimkast.jkell.types.BiTarget;
//import org.jimkast.jkell.types.Func;
//import org.jimkast.jkell.types.Source;
//import org.jimkast.jkell.types.Target;
//
//public final class BiComposer<A, B, C> implements BiFunc<A, B, C> {
//    private final BiFunc<A, B, C> origin;
//
//    public BiComposer(BiFunc<A, B, C> origin) {
//        this.origin = origin;
//    }
//
//    public <D> BiComposer<A, B, D> map(Func<C, D> next) {
//        return new BiComposer<>(new BiFnMapped<>(origin, next));
//    }
//
//    public BiTarget<A, B> reduce(Target<C> next) {
//        return (a, b) -> next.accept(origin.apply(a, b));
//    }
//
//    public Target<A> reduce2(BiTarget<A, B, C> next) {
//        return x -> next.accept(x, origin.apply(x));
//    }
//
//    public <D> PsComposer<A, B, Z> fork(Func<B, Source<D>> next) {
//        return new PsComposer<>(origin, next);
//    }
//
//    public <D> PsComposer<A, B, Z> fork(Func<B, Boolean> check, Func<B, Z> result) {
//        return fork(new FnCase<>(check, result));
//    }
//
//    public <D> PsComposer<A, B, Z> fork(Func<B, Boolean> check, Z result) {
//        return fork(new FnCase<>(check, result));
//    }
//
//    @Override
//    public B apply(A x) throws Exception {
//        return origin.apply(x);
//    }
//
//
//    public static final class PsComposer<A, X, Y> implements Func<X, Source<Y>> {
//        private final Func<A, X> before;
//        private final Func<X, Source<Y>> origin;
//
//        public PsComposer(Func<A, X> before, Func<X, Source<Y>> origin) {
//            this.before = before;
//            this.origin = origin;
//        }
//
//        public <Z> PsComposer<A, X, Z> map(Func<Y, Z> next) {
//            return new PsComposer<>(before, new FnMapped<>(origin, new FnCurried1<>(SrcMapped::new, next)));
//        }
//
////        public PsComposer<X, Y, Z> reduce(Func<Y, Y> reducer) {
////            return new PsComposer<>(
////                x -> origin.apply()
////            );
////        }
////
////        public Composer<X, Target<Y>> foreach(Target<Y> target) {
////            return new Composer<>(
////                x -> origin.apply(x).feed()
////            );
////        }
//
//        public BiComposer<A, Y> orelse(Y other) {
//            return new BiComposer<>(
//                x -> origin.apply(before.apply(x)).feed(FnSelf.instance(), other)
//            );
//        }
//
//        public PsComposer<A, X, Y> fork(Func<X, Source<Y>> next) {
//            return new PsComposer<>(before, new FnBoth<>(origin, next, SrcFallback::new));
//        }
//
//        public PsComposer<A, X, Y> fork(Func<X, Boolean> check, Func<X, Y> result) {
//            return fork(new FnCase<>(check, result));
//        }
//
//        public PsComposer<A, X, Y> fork(Func<X, Boolean> check, Y result) {
//            return fork(new FnCase<>(check, result));
//        }
//
//        @Override
//        public Source<Y> apply(X x) throws Exception {
//            return origin.apply(x);
//        }
//    }
//
//
//    public static <X, Y> BiComposer<X, Y> wrap(Func<X, Y> start) {
//        return new BiComposer<>(start);
//    }
//
//    public static <X, Y> PsComposer<X, X, Y> maybe(Func<X, Source<Y>> start) {
//        return new PsComposer<>(FnSelf.instance(), start);
//    }
//}
