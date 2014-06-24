package ph.hatch.ddd.infrastructure.event.publisher.jms;

import com.google.gson.Gson;
import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.DomainEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        createLocalEventQueueItem(envelope);

        // we do not send on publish, we let the local queue handler send it (via JMS)
        //sender.send(envelope);

    }

    private void createLocalEventQueueItem(EventEnvelope eventEnvelope) {

        String fileName = "eventsLog" + File.separator + new SimpleDateFormat("yyyyMMddhhmmss.SSS'.event'").format(new Date());

        File f = new File(fileName);

        try {

            f.getParentFile().mkdirs();
            f.createNewFile();

            FileWriter fw = new FileWriter(f);

            Gson gson = new Gson();

            String envelopeDetails = gson.toJson(eventEnvelope);

            fw.write(envelopeDetails);
            fw.close();

        } catch(Exception e) {

            System.out.println("error creating a file!!!");
            e.printStackTrace();

        }



    }


}
