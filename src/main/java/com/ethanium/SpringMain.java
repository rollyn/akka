package com.ethanium;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import org.junit.Assert;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static com.ethanium.SpringExtension.SPRING_EXTENSION_PROVIDER;

public class SpringMain {
    public static void main(String[] args) throws Exception {

        ActorSystem system = ActorSystem.create();
        ActorRef greeter = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system)
                .props("greetingActor"), "greeter");

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = ask(greeter, new GreetingActor.Greet("John"), timeout);

        Assert.assertEquals("Hello, John", Await.result(result, duration));
    }
}
