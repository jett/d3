package ph.hatch.d3.infrastructure.event.publisher.simple;

import ph.hatch.ddd.domain.DomainEvent;

public class TestEvent extends DomainEvent {

    private String message;

    public TestEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
