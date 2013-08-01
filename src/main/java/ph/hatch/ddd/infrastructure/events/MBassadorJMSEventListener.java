package ph.hatch.ddd.infrastructure.events;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

import javax.jms.*;

@Component
public class MBassadorJMSEventListener implements MessageListener
{

    @Autowired
    @Qualifier("localPublisher")
    DomainEventPublisher eventPublisher;

    public void onMessage(Message message)
    {
        try
        {
            MapMessage mapMessage = (MapMessage) message;

            String eventClass = mapMessage.getString("eventClass");
            String eventDetails = mapMessage.getString("eventDetails");

            Class clazz = Class.forName(eventClass);

            if(clazz != null) {

                Gson gson = new Gson();

                Object eventClassInstance = gson.fromJson(eventDetails, clazz);

                // publish the event
                eventPublisher.publish(eventClassInstance);

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
