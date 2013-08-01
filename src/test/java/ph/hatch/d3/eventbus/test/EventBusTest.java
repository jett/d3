package ph.hatch.d3.eventbus.test;

import net.engio.mbassy.bus.BusConfiguration;
import net.engio.mbassy.bus.MBassador;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ph.hatch.ddd.domain.annotations.DomainEventPublisher;

/**
 * Created with IntelliJ IDEA.
 * User: jett
 * Date: 7/28/13
 * Time: 12:42 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-jms-beans.xml")
//@TestExecutionListeners(listeners = {WebContextEmulator.class, DependencyInjectionTestExecutionListener.class}, inheritListeners = true)
//@TransactionConfiguration
//@Transactional
public class EventBusTest {


    @Autowired
    DomainEventPublisher eventPublisher;

    @Test
    public void testMe() {
        System.out.println("test here");

        MBassador bus = new MBassador(BusConfiguration.Default());

        EventHandlers listener = new EventHandlers();
        // the listener will be registered using a weak-reference if not configured otherwise with @Listener
        bus.subscribe(listener);

        DummyOneEvent message = new DummyOneEvent("test Details");

        bus.publishAsync(message); //returns immediately, publication will continue asynchronously
        System.out.println("post");

        try {
            Thread.sleep(4000);
        } catch(Exception e) {
            System.out.println("exception");
        }

    }

    @Test
    public void mbassadorPublishTest() {

        DummyOneEvent message = new DummyOneEvent("test Details");
        eventPublisher.publish(message);
        try {
            Thread.sleep(4000);
        } catch(Exception e) {
            System.out.println("exception");
        }

    }

}
