package org.jimkast.jkell.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jimkast.jkell.types.Target;

public final class WwwRedirect implements ServletExchange {
    private final String domain;

    public WwwRedirect(String domain) {
        this.domain = domain;
    }

    @Override
    public Target<HttpServletResponse> apply(HttpServletRequest req) throws Exception {
        return res -> {
            res.setStatus(301);
            res.setHeader("Location", "//www." + domain + req.getRequestURI() + "?" + req.getQueryString());
        };
    }
}
