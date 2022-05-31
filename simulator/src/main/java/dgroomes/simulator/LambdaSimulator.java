package dgroomes.simulator;

import dgroomes.echo.Echo;
import dgroomes.helloworldlambda.HelloWorldLambdaFunction;
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
    var echo = new Echo("local");
    var helloWorldLambdaFunction = new HelloWorldLambdaFunction(echo);
    var simulatorHttpHandler = new SimulatorHttpHandler(helloWorldLambdaFunction);

    ServerBootstrap builder = ServerBootstrap.bootstrap()
            .setListenerPort(PORT)
            .setExceptionListener(new LoggingExceptionListener())
            .register("*", simulatorHttpHandler);

    try (HttpServer server = builder.create()) {
      server.start();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close(CloseMode.GRACEFUL)));
      log.info("The Lambda simulator server is serving traffic on port {}", PORT);
      server.awaitTermination(TimeValue.MAX_VALUE);
    }
  }

}
