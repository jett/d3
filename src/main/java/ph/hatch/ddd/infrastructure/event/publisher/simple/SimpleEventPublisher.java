package ph.hatch.ddd.infrastructure.event.publisher.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;
import ph.hatch.ddd.event.EventDispatcher;

@Component
public class SimpleEventPublisher implements DomainEventPublisher  {


    @Autowired
    EventDispatcher eventDispatcher;

    @Override
    public void publish(DomainEvent domainEvent) {

        eventDispatcher.dispatch(domainEvent);

    }


}
