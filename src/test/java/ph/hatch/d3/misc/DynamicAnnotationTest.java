package ph.hatch.d3.misc;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import org.junit.Test;
import ph.hatch.d3.eventbus.test.EventHandlers;
import ph.hatch.ddd.domain.annotations.DomainEventListener;

import java.lang.reflect.Method;


public class DynamicAnnotationTest {


    @Test
    public void testAddAnnotation() {


        Class clazz = EventHandlers.class;

        // if our class method has the DomainEventListener annotation, annoate it with Mbassy's Handler
        try {

            ClassPool pool = ClassPool.getDefault();
            //CtClass cc = pool.getCtClass(beanName);
            CtClass cc = pool.getCtClass(clazz.getName());
            ClassFile cf = cc.getClassFile();
            ConstPool constpool = cf.getConstPool();

            AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
            Annotation mbassyListenerAnnot = new Annotation("net.engio.mbassy.listener.Handler", constpool);
            attr.addAnnotation(mbassyListenerAnnot);


            // iterate through our methods, if any one has the DomainEventListener attribute, annotate it with
            // Mbassy's Handler annotation
            for(CtMethod ctMethod : cc.getDeclaredMethods()) {

                Object listenerAnnotation = ctMethod.getAnnotation(DomainEventListener.class);

                if(listenerAnnotation !=  null) {
                    ctMethod.getMethodInfo().addAttribute(attr);

                    //mbassadorListener = true;
                    System.out.println(ctMethod.getLongName());
                }
            }

            cc.setName(clazz.getName()+"Proxy");
            cc.writeFile();

            // transform the ctClass to java class
            Class dynamiqueBeanClass = cc.toClass();

            System.out.println(dynamiqueBeanClass.getCanonicalName());
            Object mylistener = dynamiqueBeanClass.newInstance();
            for(Method m : mylistener.getClass().getDeclaredMethods()) {
                System.out.println(m.getName());
                for(java.lang.annotation.Annotation a : m.getDeclaredAnnotations()) {
                    System.out.println("\t" + a.toString());
                }
            }
            System.out.println("xxxxx");


        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
