package ph.hatch.d3.eventbus.test;

public class DummyOneEvent {
    String details;

    public DummyOneEvent(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return this.details;
    }
}
