package ph.hatch.ddd.oe;

import java.util.HashMap;
import java.util.Map;

public class ObjectMeta {

    String collectionClassName;
    String className;
    String objectname;

    Boolean isCollection = false;

    Map<String, ObjectMeta> fields;

    public String getCollectionClassName() {
        return collectionClassName;
    }

    public String getClassName() {
        return className;
    }

    public String getObjectname() {
        return objectname;
    }

    public Boolean getCollection() {
        return isCollection;
    }

    public ObjectMeta(Class clazz, String objectName) {
        this.className = clazz.getCanonicalName();
        this.objectname = objectName;
    }

    public ObjectMeta(Class collectionClazz, Class clazz, String objectName) {
        this.collectionClassName = collectionClazz.getCanonicalName();
        this.className = clazz.getCanonicalName();
        this.objectname = objectName;
        this.isCollection = true;
    }

    public void addField(Class clazz, String fieldname) {
        if(fields == null) {

            fields = new HashMap();
        }

        fields.put(fieldname, new ObjectMeta(clazz, fieldname));
    }

    public void addCollectionField(Class collectionClazz, Class clazz, String fieldname) {
        if(fields == null) {
            fields = new HashMap();
        }

        fields.put(fieldname, new ObjectMeta(collectionClazz, clazz, fieldname));
    }

    public ObjectMeta getFieldMeta(String fieldname) {
        return fields.get(fieldname);
    }

    public Map getFields() {
        return fields;
    }

    public Boolean isCollection() {
        return isCollection;
    }

}
