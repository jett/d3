package ph.hatch.ddd.infrastructure.events;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.EnumMemberValue;
import net.engio.mbassy.listener.Invoke;
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

@Component
public class MBassadorEventsLIstenerProcessor  implements ApplicationContextAware, DestructionAwareBeanPostProcessor {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Autowired
    private MBassadorEventsPublisher eventPublisher;

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Boolean mbassadorListener = false;

        Class clazz;

        // if this was proxied by Spring, get the original class name
        clazz = AopUtils.getTargetClass(bean);
        if(clazz == null) {
            clazz = bean.getClass();
        }

        // if our class method has the DomainEventListener annotation, annoate it with Mbassy's Handler
        try {

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.getCtClass(clazz.getName());
            ClassFile cf = cc.getClassFile();

            EnumMemberValue syncDelivery = new EnumMemberValue(cf.getConstPool());
            syncDelivery.setType("net.engio.mbassy.listener.Invoke");
            syncDelivery.setValue("Asynchronously");
//            syncDelivery.setValue("Synchronously");

            AnnotationsAttribute attr = new AnnotationsAttribute(cf.getConstPool(), AnnotationsAttribute.visibleTag);
            Annotation mbassyListenerAnnot = new Annotation("net.engio.mbassy.listener.Handler", cf.getConstPool());

            mbassyListenerAnnot.addMemberValue("delivery", syncDelivery);
            attr.addAnnotation(mbassyListenerAnnot);

            // System.out.println("postProcessBeforeInit: " + beanName);

            // iterate through our methods, if any one has the DomainEventListener attribute, annotate it with
            // Mbassy's Handler annotation
            for(CtMethod ctMethod : cc.getDeclaredMethods()) {

                Object listenerAnnotation = ctMethod.getAnnotation(DomainEventListener.class);

                if(listenerAnnotation !=  null) {
                    ctMethod.getMethodInfo().addAttribute(attr);
                    mbassadorListener = true;
                }
            }

            // if this is a class with a listener, register it with our Mbassador event's publisher
            if(mbassadorListener) {

                cc.setName(clazz.getName()+"d3Proxy");
                cc.writeFile();

                // transform the ctClass to our new proxied Java class
                Class proxiedBeanClass = cc.toClass();

                // instantiate the bean
                bean = proxiedBeanClass.newInstance();

//                for(Method m : bean.getClass().getDeclaredMethods()) {
//                    System.out.println(m.getName());
//                    for(java.lang.annotation.Annotation a : m.getDeclaredAnnotations()) {
//                        System.out.println("\t" + a.toString());
//                    }
//                }

                eventPublisher.registerListener(bean);

            }

        } catch(Exception e) {
            //e.printStackTrace();
        }


        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
