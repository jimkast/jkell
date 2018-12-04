//package org.jimkast.jkell.http;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.jimkast.jkell.F;
//import org.jimkast.jkell.func.FnCurried2;
//import org.jimkast.jkell.func.FnSelf;
//import org.jimkast.jkell.types.Func;
//import org.jimkast.jkell.types.Target;
//import org.jimkast.jkell.xml.JettyServlet;
//
//public final class WebApp {
//
//    public static void main(String... args) throws Exception {
//        Func<HttpServletRequest, String> aaa = new FnCurried2<>(ServletRequest::getParameter, "dfgd");
//
//        new JettyServer(8080,
//            new JettyServlet(
//                F.wrap(FnSelf.<HttpServletRequest>instance())
//                    .fork(F.wrap(HttpServletRequest::getMethod)
//                            .then(F.curry1(String::equalsIgnoreCase, "GET")),
//                        new WwwRedirect("youtravel.com")
//                    ).fork(
////                        Composer.wrap(HttpServletRequest::getServletPath).pipe(s -> true),
//                    new ChkForPath(new ChkRegex("/login")),
//                    req -> res -> res.getWriter().print("Hello " + req.getMethod() + " " + req.getRequestURI())
//                ).orelse()
//                    .<HttpServletRequest, Target<HttpServletResponse>>orelse(req -> res -> res.getWriter().print("Hello " + req.getMethod() + " " + req.getRequestURI()))
//            )
//        ).run();
//    }
//}
