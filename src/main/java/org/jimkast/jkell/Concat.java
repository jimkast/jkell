package org.jimkast.jkell;

import org.jimkast.jkell.types.BiFunc;

public final class Concat implements BiFunc<String, String, String> {
    @Override
    public String apply(String s, String s2) throws Exception {
        return s + s2;
    }
}
