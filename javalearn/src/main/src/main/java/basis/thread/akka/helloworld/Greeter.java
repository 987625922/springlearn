package basis.thread.akka.helloworld;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if (o == Msg.GREET) {
            System.out.println("hello world!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(o);
        }
    }
}
