package ph.hatch.d3.infrastructure.event.publisher.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-context.xml")
public class TestSimplePublisher {

    @Autowired
    DomainEventPublisher eventPublisher;

    @Test
    public void testDispatch() {


        TestEvent testEvent = new TestEvent("testevent message");

        eventPublisher.publish(testEvent);

        // pause for a few seconds so we can see the handler in action
        try {
            Thread.sleep(4000);
        } catch(Exception e) {
            System.out.println("exception");
        }

    }
}
