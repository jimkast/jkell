package org.jimkast.jkell.http;

import java.util.regex.Pattern;
import org.jimkast.jkell.types.Func;

public final class ChkRegex implements Func<String, Boolean> {
    private final Pattern ptn;

    public ChkRegex(String regex) {
        this(Pattern.compile(regex, Pattern.DOTALL | Pattern.CASE_INSENSITIVE));
    }

    public ChkRegex(Pattern ptn) {
        this.ptn = ptn;
    }

    @Override
    public Boolean apply(String s) throws Exception {
        return ptn.matcher(s).matches();
    }
}
