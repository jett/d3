package ph.hatch.d3.infrastructure.event.publisher.simple;

import ph.hatch.ddd.domain.DomainEvent;

/**
 * Created by jett on 1/29/14.
 */
public class SecondEvent extends DomainEvent {

    private String message;

    public SecondEvent(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
