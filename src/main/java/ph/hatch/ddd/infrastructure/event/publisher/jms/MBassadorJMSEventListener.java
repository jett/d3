package ph.hatch.ddd.infrastructure.event.publisher.jms;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;
import ph.hatch.ddd.event.EventDispatcher;

import javax.jms.*;

@Component
public class MBassadorJMSEventListener implements MessageListener
{

    @Autowired
    EventDispatcher eventDispatcher;

    public void onMessage(Message message) {
        try
        {
            MapMessage mapMessage = (MapMessage) message;

            String eventClass = mapMessage.getString("eventClass");
            String eventDetails = mapMessage.getString("eventDetails");

            Class clazz = Class.forName(eventClass);

            if(clazz != null) {

                Gson gson = new Gson();

                Object eventClassInstance = gson.fromJson(eventDetails, clazz);

                // dispatch the event
                eventDispatcher.dispatch((DomainEvent) eventClassInstance);

            }

        }
        catch(ClassNotFoundException e) {

        }
        catch (JMSException e)
        {
            throw new RuntimeException(e);
        }

    }
}
