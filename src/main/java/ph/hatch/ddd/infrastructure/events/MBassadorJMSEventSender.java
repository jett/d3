package ph.hatch.ddd.infrastructure.events;

import javax.jms.*;
import java.util.Map;

public class MBassadorJMSEventSender {

    private ConnectionFactory connectionFactory;
    private Destination destination;

    public ConnectionFactory getConnectionFactory()
    {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }

    public Destination getDestination()
    {
        return destination;
    }

    public void setDestination(Destination destination)
    {
        this.destination = destination;
    }

    public void send(String msg)
    {
        try
        {
            Connection conn = connectionFactory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(msg);
            producer.send(message);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void send(EventEnvelope eventEnvelope) {

        try {

            Connection conn = connectionFactory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            MapMessage message = session.createMapMessage();
            message.setString("eventClass", eventEnvelope.getEventClassName());
            message.setString("eventDetails", eventEnvelope.getEventClassDetails());

            producer.send(message);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}