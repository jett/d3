package ph.hatch.d3.eventbus.test;

import net.engio.mbassy.listener.Handler;
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

}
