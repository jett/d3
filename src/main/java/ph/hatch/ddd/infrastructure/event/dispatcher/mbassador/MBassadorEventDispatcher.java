package ph.hatch.ddd.infrastructure.event.dispatcher.mbassador;

import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.event.EventDispatcher;

@Component
public class MBassadorEventDispatcher implements EventDispatcher {

    MBassador bus = new MBassador(BusConfiguration.Default());

    public void registerListener(Object listener) {
        // System.out.println("registering a listener for " + listener );
        bus.subscribe(listener);
    }

    @Override
    public void dispatch(DomainEvent event) {

        bus.publish(event);

    }

}
