package org.jimkast.jkell.xml;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.jimkast.jkell.types.Func;
import org.jimkast.jkell.types.Target;

public final class JettyServlet extends AbstractHandler {
    private final Func<HttpServletRequest, Target<HttpServletResponse>> take;

    public JettyServlet(Func<HttpServletRequest, Target<HttpServletResponse>> take) {
        this.take = take;
    }

    @Override
    public void handle(String target, Request base, HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {
        try {
            take.apply(req).accept(rsp);
        } catch (RuntimeException | IOException | ServletException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
