package ph.hatch.ddd.infrastructure.event.publisher.jms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventEnvelope {

    private String eventTimestamp;
    private String eventClassName;
    private String eventDetails;

    public EventEnvelope() {

    }

    public EventEnvelope(String eventClassName, String eventClassDetails) {
        this.eventClassName = eventClassName;
        this.eventDetails = eventClassDetails;
        this.eventTimestamp = new SimpleDateFormat("yyyyMMddhhmmss.SSS").format(new Date());
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

}
