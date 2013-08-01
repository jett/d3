package ph.hatch.d3.eventbus.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ph.hatch.d3.eventbus.test.DummyOneEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-jms-beans.xml")
public class JMSEventBusTest {

    @Autowired
    @Qualifier("jmsPublisher")
    DomainEventPublisher eventPublisher;

    @Test
    public void testDispatch() {

        DummyOneEvent d1event = new DummyOneEvent("this is the dummy event");

        eventPublisher.publish(d1event);

        // pause so we still see the listener responding
        try {

            for(int x=0; x < 10; x++) {
                DummyOneEvent eventSample = new DummyOneEvent("eventid: " + Integer.toString(x));
                eventPublisher.publish(eventSample);
            }


            for(int x=0; x < 10; x++) {
                Thread.sleep(200);
                System.out.println(x);
            }


            Thread.sleep(3000);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
