package org.jimkast.jkell.jdk;

import org.jimkast.jkell.types.BiFunc;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.IntBiFunc;
import org.jimkast.jkell.types.IntIntBiFunc;
import org.jimkast.jkell.types.IntIntFunc;

public final class Ops {
    public static IntIntBiFunc ADD = (x, y) -> x + y;
    public static IntIntBiFunc SUB = (x, y) -> x - y;
    public static IntIntBiFunc MUL = (x, y) -> x * y;
    public static IntIntBiFunc DIV = (x, y) -> x / y;
    public static IntIntBiFunc MOD = (x, y) -> x % y;
    public static IntIntBiFunc OR = (x, y) -> x | y;
    public static IntIntBiFunc AND = (x, y) -> x & y;
    public static IntIntBiFunc LSHIFT = (x, y) -> x << y;
    public static IntIntBiFunc RSHIFT = (x, y) -> x >> y;
    public static IntIntBiFunc URSHIFT = (x, y) -> x >>> y;
    public static IntIntBiFunc XOR = (x, y) -> x ^ y;
    public static IntIntFunc COMPL = x -> ~x;

    public static BiFunc<String, String, String> CONCAT = (x, y) -> x + y;

    public static Func<Boolean, Boolean> NOT = x -> !x;
    public static BiFunc<Object, Object, Boolean> EQ = Object::equals;
    public static IntBiFunc<Boolean> EQ_INT = (x, y) -> x == y;
    public static IntBiFunc<Boolean> GT = (x, y) -> x > y;
    public static IntBiFunc<Boolean> GTE = (x, y) -> x >= y;
    public static IntBiFunc<Boolean> LT = (x, y) -> x < y;
    public static IntBiFunc<Boolean> LTE = (x, y) -> x <= y;
}
