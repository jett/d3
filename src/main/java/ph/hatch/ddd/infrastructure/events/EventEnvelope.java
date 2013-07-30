package ph.hatch.ddd.infrastructure.events;

public class EventEnvelope {

    private String eventClassName;
    private String eventClassDetails;

    public EventEnvelope() {

    }

    public EventEnvelope(String eventClassName, String eventClassDetails) {
        this.eventClassName = eventClassName;
        this.eventClassDetails = eventClassDetails;
    }

    public String getEventClassName() {
        return eventClassName;
    }

//    public void setEventClassName(String eventClassName) {
//        this.eventClassName = eventClassName;
//    }

    public String getEventClassDetails() {
        return eventClassDetails;
    }

//    public void setEventClassDetails(Map eventClassDetails) {
//        this.eventClassDetails = eventClassDetails;
//    }
}
