package com.ethanium;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class Consumer2 extends AbstractActor {

    public Consumer2() {
        receive(ReceiveBuilder
                .match(SayHello.class, this::sayHello)
                .match(SayBye.class, this::sayBye)
                .match(Hola.class, this::sayRet)
                .build());
    }

    private void sayRet(final Hola message) {
        System.out.println("Hola World");
        sender().tell("FROM SAYRET", self());
    }

    private void sayHello(final SayHello message) {
        System.out.println("Hello World");
    }

    private void sayBye(final SayBye message) {
        System.out.println("Bye World");
    }

    public static Props props() {
        return Props.create(Consumer2.class);
    }
}
