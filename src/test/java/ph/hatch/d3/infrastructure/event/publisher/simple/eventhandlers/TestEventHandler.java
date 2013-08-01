package ph.hatch.d3.infrastructure.event.publisher.simple.eventhandlers;

import org.springframework.stereotype.Component;
import ph.hatch.d3.infrastructure.event.publisher.simple.TestEvent;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

@Component
public class TestEventHandler {

    @DomainEventListener
    public void handleTestEvent(TestEvent testEvent) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            System.out.println("exception");
        }

        System.out.println("handled event  " + testEvent);
    }
}
