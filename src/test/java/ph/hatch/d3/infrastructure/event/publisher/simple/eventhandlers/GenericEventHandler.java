package ph.hatch.d3.infrastructure.event.publisher.simple.eventhandlers;

import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

@Component
public class GenericEventHandler {

    @DomainEventListener
    public void handleTestEvent(DomainEvent testEvent) {
        System.out.println("generic handler for all events ::: " + testEvent);
    }

}
