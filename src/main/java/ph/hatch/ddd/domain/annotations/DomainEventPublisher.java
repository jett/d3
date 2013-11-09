package ph.hatch.ddd.domain.annotations;

import ph.hatch.ddd.domain.DomainEvent;

public interface DomainEventPublisher {

    //void publish(Serializable applicationEvent);
    void publish(DomainEvent domainEvent);

}
