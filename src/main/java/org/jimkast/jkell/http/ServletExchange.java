package org.jimkast.jkell.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public interface ServletExchange extends Func<HttpServletRequest, Target<HttpServletResponse>> {
    @Override
    Target<HttpServletResponse> apply(HttpServletRequest req) throws Exception;
}
