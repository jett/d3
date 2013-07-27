package ph.hatch.d3.eventbus.test;

import net.engio.mbassy.listener.Handler;

public class EventHandlers {

    @Handler
    public void handleDummyOneEvent(DummyOneEvent dummyOneEvent) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            System.out.println("exception");
        }

        System.out.println("handled DummyOneEvent " + dummyOneEvent);
    }

}
