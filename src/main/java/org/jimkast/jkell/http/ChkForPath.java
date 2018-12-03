package org.jimkast.jkell.http;

import javax.servlet.http.HttpServletRequest;
import org.jimkast.jkell.types.Func;

public final class ChkForPath implements Func<HttpServletRequest, Boolean> {
    private final Func<String, Boolean> check;

    public ChkForPath(Func<String, Boolean> check) {
        this.check = check;
    }

    @Override
    public Boolean apply(HttpServletRequest req) throws Exception {
        return check.apply(req.getServletPath());
    }
}
