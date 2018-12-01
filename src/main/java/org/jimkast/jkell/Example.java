package org.jimkast.jkell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import net.sf.saxon.s9api.XdmValue;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class Example {

    public static void main(String... args) throws Exception {
//        Exec app =
        Target<Integer> trg = Composer
            .<Integer, Integer>wrap(i -> i * 3)
            .map(i -> i + 1)
            .map(i -> i * 2)
            .fork(i -> i < 30, 30)
            .fork(i -> i < 50, 50)
            .orelse(100)
            .bi((i, result) -> i + ": " + result)
            .reduce(System.out::println);


        Func<InputStream, Target<OutputStream>> exchange = Composer
            .<InputStream, Reader>wrap(InputStreamReader::new)
            .map(BufferedReader::new)
            .map(BufferedReader::readLine)
            .fork(s -> s.equals("ytsupport"), XdmValue.makeValue("SUPERADMIN"))
            .fork(s -> s.startsWith("bo"), s -> XdmValue.makeValue("BOUSER:" + Math.random()))
            .fork(s -> s.startsWith("ag"), s -> XdmValue.makeValue("AGENT:" + Math.random()))
            .map(XdmValue::toString)
            .orelse("Unknown agent!!!")
            .map(String::getBytes)
            .map(bytes -> out -> out.write(bytes));


        for (int i = 0; i < 30; i++) {
            trg.accept(i);
        }


        exchange.apply(System.in).accept(System.out);
    }
}
