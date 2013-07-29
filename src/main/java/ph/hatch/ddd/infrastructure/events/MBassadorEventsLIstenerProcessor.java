package ph.hatch.ddd.infrastructure.events;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

import java.lang.reflect.Method;

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
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object o, String s) throws BeansException {

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

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
            //CtClass cc = pool.getCtClass(beanName);
            CtClass cc = pool.getCtClass(clazz.getName());
            ClassFile cf = cc.getClassFile();

            AnnotationsAttribute attr = new AnnotationsAttribute(cf.getConstPool(), AnnotationsAttribute.visibleTag);
            Annotation mbassyListenerAnnot = new Annotation("net.engio.mbassy.listener.Handler", cf.getConstPool());
            attr.addAnnotation(mbassyListenerAnnot);

            // iterate through our methods, if any one has the DomainEventListener attribute, annotate it with
            // Mbassy's Handler annotation
            for(CtMethod ctMethod : cc.getDeclaredMethods()) {

                Object listenerAnnotation = ctMethod.getAnnotation(DomainEventListener.class);

                if(listenerAnnotation !=  null) {
                    ctMethod.getMethodInfo().addAttribute(attr);

                    mbassadorListener = true;
                }
            }


        } catch(Exception e) {
            //e.printStackTrace();
        }

        // if this is a class with a listener, register it with our Mbassador event's publisher
        if(mbassadorListener) {
            eventPublisher.registerListener(bean);
        }

        return bean;
    }

}
