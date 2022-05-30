package dgroomes.simulator;

import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaSimulator {

  private static final int PORT = 8080;
  private static final Logger log = LoggerFactory.getLogger(LambdaSimulator.class);

  public static void main(final String[] args) throws Exception {
    try (HttpServer server = buildServer()) {
      server.start();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close(CloseMode.GRACEFUL)));
      log.info("The Lambda simulator server is serving traffic on port {}", PORT);
      server.awaitTermination(TimeValue.MAX_VALUE);
    }
  }

  private static HttpServer buildServer() {
    return ServerBootstrap.bootstrap()
            .setListenerPort(PORT)
            .setExceptionListener(new LoggingExceptionListener())
            .register("*", new HttpHandler())
            .create();
  }
}
