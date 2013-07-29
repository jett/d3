package ph.hatch.ddd.infrastructure.events;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

import java.lang.reflect.Method;

/**
 * User: Jett
 * Date: 5/31/12
 */

@Component
public class EventListenerProcessor implements ApplicationContextAware, DestructionAwareBeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private SimpleEventPublisher eventPublisher;

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class clazz;

        // if this was proxied by Spring, get the original class name
        clazz = AopUtils.getTargetClass(bean);
        if(clazz == null) {
            clazz = bean.getClass();
        }

//        for (Method method : bean.getClass().getMethods()) {
        for (Method method : clazz.getMethods()) {

            DomainEventListener listenerAnnotation = method.getAnnotation(DomainEventListener.class);

            if (listenerAnnotation == null) {
                continue;
            }

            Class<?> eventType = method.getParameterTypes()[0];

//            if (listenerAnnotation.asynchronous()){
//                //TODO just a temporary fake impl
//                EventHandler handler = new AsynchronousEventHandler(eventType, beanName, method, beanFactory);
//                //TODO add to some queue
//                eventPublisher.registerEventHandler(handler);
//            }
//            else{
////                System.out.println("**** identified "  + beanName + "." + method.getName() + " as a handler of " + eventType.getName());
//                EventHandler handler = new SpringEventHandler(eventType, beanName, method, beanFactory);
//                eventPublisher.registerEventHandler(handler);
//            }

        }

        return bean;
    }
}
