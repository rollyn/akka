package com.ethanium;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import scala.reflect.ClassTag$;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

public class Main2 {

    public static void main(String[] args) throws Exception {

        ActorSystem system = ActorSystem.create();
        ActorRef actorRef = system.actorOf(Props.create(Consumer2.class));

        actorRef.tell(new SayHello(), ActorRef.noSender());
        actorRef.tell(new SayBye(), ActorRef.noSender());

        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<String> f = ask(actorRef, new Hola(), 1).mapTo(ClassTag$.MODULE$.apply(String.class));
        String result = (String) Await.result(f, timeout.duration());
        System.out.println("RETURNED::"+result);

        system.terminate();
    }
}
