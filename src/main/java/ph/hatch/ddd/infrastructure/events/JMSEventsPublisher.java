package ph.hatch.ddd.infrastructure.events;


import com.google.gson.Gson;
import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEventPublisher;

@Component
public class JMSEventsPublisher implements DomainEventPublisher  {

    @Autowired
    MBassadorJMSEventSender sender;

    MBassador bus = new MBassador(BusConfiguration.Default());


    public void registerListener(Object listener) {
        // System.out.println("registering a listener for " + listener );
        bus.subscribe(listener);
    }

    @Override
    public void publish(Object event) {

        // bus.publish(event);
        Gson gson = new Gson();

        String eventClassName = event.getClass().getName();
        String eventDetails = gson.toJson(event);

        EventEnvelope envelope = new EventEnvelope(eventClassName, eventDetails);

        sender.send(envelope);

    }


}
