package ph.hatch.ddd.infrastructure.event.publisher.jms;

import com.google.gson.Gson;
import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@Component
public class JMSEventPublisher implements DomainEventPublisher  {

    @Autowired
    JMSEventSender sender;
//
//    MBassador bus = new MBassador(BusConfiguration.Default());
//
//    public void registerListener(Object listener) {
//        // System.out.println("registering a listener for " + listener );
//        bus.subscribe(listener);
//    }

    @Override
    public void publish(DomainEvent event) {

        // bus.publish(event);
        Gson gson = new Gson();

        String eventClassName = event.getClass().getName();
        String eventDetails = gson.toJson(event);

        EventEnvelope envelope = new EventEnvelope(eventClassName, eventDetails);

        sender.send(envelope);

    }


}
