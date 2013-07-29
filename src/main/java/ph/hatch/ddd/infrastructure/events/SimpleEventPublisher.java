package ph.hatch.ddd.infrastructure.events;

import org.springframework.stereotype.Component;
import ph.hatch.ddd.application.ApplicationEventPublisher;
import ph.hatch.ddd.domain.DomainEventPublisher;
import ph.hatch.ddd.domain.annotations.DomainEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class SimpleEventPublisher implements DomainEventPublisher  {

//    private Set<EventHandler> eventHandlers = new HashSet<EventHandler>();

//    public void registerEventHandler(EventHandler handler) {
//        eventHandlers.add(handler);
//        new SpringEventHandler(eventType, beanName, method));
//    }

    @Override
    public void publish(Object event) {
//        doPublish(event);
    }

//    @Override
    public void publish(DomainEvent event) {
//        doPublish(event);
    }

//    protected void doPublish(Object event) {
//        for (EventHandler handler : new ArrayList<EventHandler>(eventHandlers)) {
//            if (handler.canHandle(event)) {
//                handler.handle(event);
//            }
//        }
//    }
}
