package org.jimkast.jkell.http;

import java.io.IOException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.jimkast.jkell.types.Exec;

public final class JettyServer implements Exec {
    private final int port;
    private final AbstractHandler handler;

    public JettyServer(int port, AbstractHandler handler) {
        this.port = port;
        this.handler = handler;
    }

    public void run() throws Exception {
        Server server = new Server(port);
        server.setHandler(handler);
        try {
            server.start();
            server.join();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
