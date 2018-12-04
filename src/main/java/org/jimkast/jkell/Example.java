package org.jimkast.jkell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import net.sf.saxon.s9api.XdmValue;
import org.jimkast.jkell.jdk.Ops;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class Example {

    public static void main(String... args) throws Exception {
//        Exec app =
        Target<Integer> trg = F
            .<Integer, Integer>wrap(i -> i * 3)
            .then(i -> i + 1)
            .then(i -> i * 2)
            .thenif(i -> i < 30, 30)
            .elseif(i -> i < 50, 50)
            .orelse(100)
            .bi((i, result) -> i + ": " + result)
            .reduce(System.out::println);

        Func<InputStream, Target<OutputStream>> exchange = F
            .<InputStream, Reader>wrap(InputStreamReader::new)
            .then(BufferedReader::new)
            .then(BufferedReader::readLine)
            .thenif("bo"::equals, XdmValue.makeValue("SUPERADMIN"))
            .elseif(
                "bo"::startsWith,
                F.curry1(Ops.CONCAT, "BOUSER:").then(XdmValue::makeValue)
            )
            .elseif(
                "ag"::startsWith,
                F.curry1(Ops.CONCAT, "AGENT:").then(XdmValue::makeValue)
            )
            .then(XdmValue::toString)
            .orelse("Unknown agent!!!")
            .then(F.curry2(String::getBytes, StandardCharsets.UTF_8))
            .then(F.trg_curried2(OutputStream::write));


        for (int i = 0; i < 30; i++) {
            trg.accept(i);
        }

        exchange.apply(System.in).accept(System.out);
    }
}
