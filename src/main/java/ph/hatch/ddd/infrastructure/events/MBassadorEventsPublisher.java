package ph.hatch.ddd.infrastructure.events;


import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.application.ApplicationEventPublisher;
import ph.hatch.ddd.domain.DomainEventPublisher;
import ph.hatch.ddd.domain.annotations.DomainEvent;

import java.io.Serializable;

@Component
public class MBassadorEventsPublisher implements DomainEventPublisher  {

    MBassador bus = new MBassador(BusConfiguration.Default());

    public void registerListener(Object listener) {
        System.out.println("registering a listener");
        bus.subscribe(listener);
    }

    @Override
    public void publish(Object event) {

        bus.publish(event);

    }


}
