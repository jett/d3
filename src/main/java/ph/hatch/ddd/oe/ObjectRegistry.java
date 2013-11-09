package ph.hatch.ddd.oe;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.reflections.Configuration;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.reflections.ReflectionUtils.withAnnotation;

public class ObjectRegistry {

    static Logger log = Logger.getLogger(ObjectRegistry.class.getName());

    private BiMap entityStore;          // stores all the Entities and identifiers
    private Map identityStore;          // stores names of the entity identity fields
    private Map<String, ObjectMeta> metaStore;    // stores meta for all classes

    private Map entityStore1;
    private Map entityStore2;

    public ObjectRegistry(String... packageNames) {

        entityStore = HashBiMap.create();

        entityStore1 = new HashMap();
        entityStore2 = new HashMap();
        identityStore = new HashMap();

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        //Configuration config = new ConfigurationBuilder().addUrls(url);

        for(String packageName : packageNames) {

            Set<URL> url = ClasspathHelper.forPackage(packageName);
            configurationBuilder.addUrls(url);

        }

        metaStore = new HashMap();

        Configuration config = configurationBuilder;
        Reflections reflections = new Reflections(config);

        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(DomainEntity.class);

        for(Class entity : entities) {

            // get identity fields
            Set<Field> fields = ReflectionUtils.getFields(entity, withAnnotation(DomainEntityIdentity.class));

            if(fields.size() == 1) {

                Field identityField =  (Field) fields.toArray()[0];

                log.info("adding: " + entity.getCanonicalName() + " : " + identityField.getType().getCanonicalName());

                identityStore.put(entity, identityField.getName());

                //entityStore.put(entity.getCanonicalName(), identityField.getName());
                // TODO, bug here if type is one of the base types

                if(!identityField.getType().getCanonicalName().equalsIgnoreCase(String.class.getCanonicalName()) ) {
                    entityStore.put(entity.getCanonicalName(), identityField.getType().getCanonicalName());
                }

                entityStore1.put(entity.getCanonicalName(), identityField.getType().getCanonicalName());

            } else {

                // TODO: log error, DomainEntity may only have one identity field

            }

            // get all fields

            ObjectMeta meta = new ObjectMeta(entity, entity.getCanonicalName());

            fields = ReflectionUtils.getAllFields(entity);

            for(Field field : fields) {

                //System.out.println(entity.getCanonicalName() + " : " + field.getName() + " > " + field.getType());

                // check if element is instance of a collection
                if(Collection.class.isAssignableFrom(field.getType())) {

                    // only get parameterized types
                    if(field.getGenericType() instanceof ParameterizedType) {

                        ParameterizedType objectListType = (ParameterizedType) field.getGenericType();
                        Class setClass = (Class<?>) objectListType.getActualTypeArguments()[0];

                        //System.out.println("set : " + field.getType());
                        //System.out.println("collection of " + setClass);

                        meta.addCollectionField(field.getType(), setClass, field.getName());

                    }

                } else {
                    meta.addField(field.getType(), field.getName());
                }
            }

            metaStore.put(entity.getCanonicalName(), meta);

        }

    }

    public String getClassForEntityIdentityField(String entityIdentityFieldname) {
        return (String) entityStore.inverse().get(entityIdentityFieldname);
    }

    public String getEntityIdentityFieldname(Class clazz) {
        return (String) identityStore.get(clazz);
    }

    public ObjectMeta getMetaForClass(Class clazz) {
        return metaStore.get(clazz.getCanonicalName());
    }

    public BiMap getEntityStore() {
        return entityStore;
    }


}
