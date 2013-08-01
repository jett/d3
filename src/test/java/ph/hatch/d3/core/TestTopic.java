package ph.hatch.d3.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ph.hatch.d3.eventbus.test.DummyOneEvent;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-jms-beans.xml")
public class TestTopic {

    @Autowired
    DomainEventPublisher eventPublisher;

    @Autowired
    MessageSender sender;

    //public static void main(String[] args) throws Exception {
    @Test
    public void testDispatch() {

        sender.send("Hello world");

        DummyOneEvent message = new DummyOneEvent("test Details");
        eventPublisher.publish(message);

        // delay so we have time to receive
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("test async done");

        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }



}
