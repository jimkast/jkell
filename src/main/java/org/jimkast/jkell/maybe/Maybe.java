package org.jimkast.jkell.maybe;

import org.jimkast.jkell.types.Func;

public interface Maybe<T> {
    <X> Func<X, X> map(Func<T, X> next) throws Exception;
}
