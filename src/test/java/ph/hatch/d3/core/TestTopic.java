package ph.hatch.d3.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTopic {

    public static void main(String[] args) throws Exception {

        System.out.println("Creating bean factory...");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-jms-beans.xml"});

        MessageSender sender = (MessageSender)context.getBean("MessageSender");
        System.out.println("Sending message...");
        sender.send("Hello world");

        // delay so we have time to receive
        Thread.sleep(2000);

        context.destroy();
    }



}
