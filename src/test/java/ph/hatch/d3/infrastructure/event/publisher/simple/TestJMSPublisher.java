package ph.hatch.d3.infrastructure.event.publisher.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:jms-context.xml")
public class TestJMSPublisher {

    @Autowired
    DomainEventPublisher eventPublisher;

    @Test
    public void testJMSDispatch() {

        TestEvent testEvent = new TestEvent("testevent message for jms");

        eventPublisher.publish(testEvent);

        // pause for a few seconds so we can see the handler in action
        try {
            Thread.sleep(4000);
        } catch(Exception e) {
            System.out.println("exception");
        }

    }

    @Test
    public void testExecutor() {

        try {
            TestEvent testEvent = new TestEvent("testevent message for jms");

            eventPublisher.publish(testEvent);
            Thread.sleep(5000);

            testEvent = new TestEvent("testevent message for jms 2");
            eventPublisher.publish(testEvent);

            Thread.sleep(5000);

        } catch(Exception e) {
            System.out.println("exception");
        }
    }

}
