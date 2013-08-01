package ph.hatch.ddd.event;

import ph.hatch.ddd.domain.DomainEvent;

public interface EventDispatcher {

    public void dispatch(DomainEvent event);

}