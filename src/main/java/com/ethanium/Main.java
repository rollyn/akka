package com.ethanium;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create("akka");
        ActorRef actorRef = system.actorOf(Props.create(Consumer.class));

        for(int i=0;i<10;i++) {
            System.out.println("Producing..."+i);
            actorRef.tell(i, ActorRef.noSender());
        }

        system.shutdown();
        System.out.println("DONE");
    }
}
