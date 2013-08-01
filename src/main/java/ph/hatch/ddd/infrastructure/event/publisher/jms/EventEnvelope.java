package ph.hatch.ddd.infrastructure.event.publisher.jms;

public class EventEnvelope {

    private String eventClassName;
    private String eventDetails;

    public EventEnvelope() {

    }

    public EventEnvelope(String eventClassName, String eventClassDetails) {
        this.eventClassName = eventClassName;
        this.eventDetails = eventClassDetails;
    }

    public String getEventClassName() {
        return eventClassName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

}
