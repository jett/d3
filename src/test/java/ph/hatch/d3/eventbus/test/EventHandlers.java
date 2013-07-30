package ph.hatch.d3.eventbus.test;

import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

@Component
public class EventHandlers {

    @DomainEventListener
    public void handleDummyOneEvent(DummyOneEvent dummyOneEvent) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            System.out.println("exception");
        }

        System.out.println("handled DummyOneEvent " + dummyOneEvent);
    }

    @DomainEventListener
    @Handler(delivery = Invoke.Asynchronously)
    public void secondHandler(DummyOneEvent dummyOneEvent) {
        try {
            Thread.sleep(1500);
        } catch(Exception e) {
            System.out.println("exception");
        }

        System.out.println("this is the second handler for the DummyOneEvent " + dummyOneEvent);
    }


}
