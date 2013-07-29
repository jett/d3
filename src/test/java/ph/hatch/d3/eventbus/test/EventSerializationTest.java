package ph.hatch.d3.eventbus.test;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-jms-beans.xml")
public class EventSerializationTest {

    @Test
    public void testMe() {

        DummyOneEvent eventOne = new DummyOneEvent("test Details");

        Gson gson = new Gson();

        String serialized = gson.toJson(eventOne);

        DummyOneEvent eventOneTwin = gson.fromJson(serialized, DummyOneEvent.class);

        System.out.println(serialized);


    }

}
