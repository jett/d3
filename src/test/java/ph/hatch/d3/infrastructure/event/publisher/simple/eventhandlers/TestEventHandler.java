package ph.hatch.d3.infrastructure.event.publisher.simple.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ph.hatch.d3.infrastructure.event.publisher.simple.AutowiredItem;
import ph.hatch.d3.infrastructure.event.publisher.simple.SecondEvent;
import ph.hatch.d3.infrastructure.event.publisher.simple.TestEvent;
import ph.hatch.ddd.domain.annotations.DomainEventListener;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@Component
public class TestEventHandler {

    @Autowired
    AutowiredItem autowiredItem;

    @Autowired
    DomainEventPublisher eventPublisher;

    @DomainEventListener
    public void handleTestEvent(TestEvent testEvent) {
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            System.out.println("exception");
        }

        System.out.println("handled event  " + testEvent);
        System.out.println(autowiredItem.getMessage());

//        if(autowiredItem==null) {
//            System.out.println("i was not autowired");
//        } else {
//            System.out.println("autowired");
//        }

//        System.out.println("check autowired: " + autowiredItem.getMessage());
//        System.out.println("autowired");
//        System.out.println(autowiredItem);
    }

    @DomainEventListener
    public void handleAndPublish(TestEvent testEvent) {

        System.out.println("Handler: handleAndPublish");
        System.out.println("handled TestEvent with message: " + testEvent);

        System.out.println("dispatching another event from this eventHandler");
        SecondEvent secondEvent = new SecondEvent("secondEvent message");

        eventPublisher.publish(secondEvent);

    }

    @DomainEventListener
    public void handleSecondEvent(SecondEvent secondEvent) {

        System.out.println("SecondEvent Handler: handleSecondEvent");
        System.out.println("SecondEvent message: " + secondEvent);

    }
}
