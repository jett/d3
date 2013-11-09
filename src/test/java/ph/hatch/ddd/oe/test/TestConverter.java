package ph.hatch.ddd.oe.test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Set;

import static org.reflections.ReflectionUtils.withAnnotation;


public class TestConverter {

    @Test
    public void testConverter() {

//        Reflections reflections = new Reflections(new ConfigurationBuilder()
//                .addUrls(ClasspathHelper.forPackage("ph.hatch.ddd.oe.test.domain"))
//                .setScanners(new ResourcesScanner(),
//                new TypeAnnotationsScanner(),
//                new FieldAnnotationsScanner(),
//                new TypeElementsScanner(),
//                new SubTypesScanner()));

        Set<URL> url = ClasspathHelper.forPackage("ph.hatch.ddd.oe.test.domain");
        Configuration config = new ConfigurationBuilder().addUrls(url);
        Reflections reflections = new Reflections(config);

//        Reflections reflections = new Reflections(new ConfigurationBuilder()
//                .addUrls(ClasspathHelper.forPackage("ph.hatch.ddd.oe.test.domain")));

        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(DomainEntity.class);

        for(Class entity : entities) {

            System.out.println(entity.getCanonicalName());

            Set<Field> fields = ReflectionUtils.getFields(entity, withAnnotation(DomainEntityIdentity.class));

            for(Field field: fields) {

                System.out.println("\t" + field.getName());

            }

        }

    }

    @Test
    public void testBiMap() {

        BiMap<String,String> biMap = HashBiMap.create();

        biMap.put("a", "b");

        System.out.println(biMap.get("a"));
        System.out.println(biMap.inverse().get("b"));

    }

}
