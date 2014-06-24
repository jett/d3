package ph.hatch.ddd.infrastructure.event.publisher.jms.fileeventqueue;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ph.hatch.ddd.infrastructure.event.publisher.jms.EventEnvelope;
import ph.hatch.ddd.infrastructure.event.publisher.jms.JMSEventSender;

import javax.jms.JMSException;
import java.io.File;

/**
 * Created by jett on 5/6/14.
 */
public class LocalEventQueueProcessor {

    @Autowired
    JMSEventSender sender;

    public File process(File file) throws Exception{

        System.out.println("Processing Event File: " + file);

        String myString = FileUtils.readFileToString(file);

        Gson gson = new Gson();
        EventEnvelope eventEnvelope = gson.fromJson(myString, EventEnvelope.class);

        try {

            sender.send(eventEnvelope);
            file.delete();

        } catch(JMSException e) {

            // JMS failed, we leave it here, the next poll will get it
            System.out.println("sending failed, leaving in local queue.");

        }

        return file;
    }

}
