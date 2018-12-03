package org.jimkast.jkell.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Source;
import org.jimkast.jkell.types.Target;

public interface ServletExchangeMatch extends Func<HttpServletRequest, Source<Target<HttpServletResponse>>> {
    @Override
    Source<Target<HttpServletResponse>> apply(HttpServletRequest req) throws Exception;
}
