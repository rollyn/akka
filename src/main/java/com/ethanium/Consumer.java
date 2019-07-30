package com.ethanium;

import akka.actor.UntypedActor;

public class Consumer extends UntypedActor {

    public void onReceive(Object o) throws Exception {
        if (o.getClass().equals(Integer.class)) {
            System.out.println("Consuming..."+o);
        }
    }
}
