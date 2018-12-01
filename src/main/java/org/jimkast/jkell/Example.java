package org.jimkast.jkell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import net.sf.saxon.s9api.XdmValue;
import org.jimkast.jkell.func.FnCurried1;
import org.jimkast.jkell.func.FnCurried2;
import org.jimkast.jkell.target.TrgCurry2;
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
            .fork("bo"::equals, XdmValue.makeValue("SUPERADMIN"))
            .fork(new FnCurried1<>("bo", String::startsWith), s -> XdmValue.makeValue("BOUSER:" + s))
            .fork(new FnCurried1<>("ag", String::startsWith), s -> XdmValue.makeValue("AGENT:" + s))
            .map(XdmValue::toString)
            .orelse("Unknown agent!!!")
            .map(new FnCurried2<>(StandardCharsets.UTF_8, String::getBytes))
            .map(new TrgCurry2<>(OutputStream::write));


        for (int i = 0; i < 30; i++) {
            trg.accept(i);
        }

        exchange.apply(System.in).accept(System.out);
    }
}
