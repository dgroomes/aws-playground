package dgroomes;

import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

  private static final int PORT = 8080;
  private static final Logger log = LoggerFactory.getLogger(Runner.class);

  public static void main(final String[] args) throws Exception {
    var handler = new HttpHandler();

    ServerBootstrap builder = ServerBootstrap.bootstrap()
            .setListenerPort(PORT)
            .setExceptionListener(new LoggingExceptionListener())
            .register("*", handler);

    try (HttpServer server = builder.create()) {
      server.start();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close(CloseMode.GRACEFUL)));
      log.info("The server is running and serving traffic on port {}", PORT);
      server.awaitTermination(TimeValue.MAX_VALUE);
    }
  }
}
