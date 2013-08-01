package ph.hatch.ddd.domain.annotations;

import ph.hatch.ddd.domain.annotations.DomainEvent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Jett
 * Date: 7/29/13
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DomainEventPublisher {

    //void publish(Serializable applicationEvent);
    void publish(Object applicationEvent);

}
