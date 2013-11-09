package ph.hatch.ddd.oe;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ph.hatch.ddd.domain.annotations.DomainEntity;

import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ObjectRepository<entityClass> {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Autowired
    ObjectRegistry objectRegistry;

    public void persist(Object entityInstance) {

        Session session = this.sessionFactory.getCurrentSession();

        session.persist(entityInstance);

    }

    public Object loadByEntityId(Class<DomainEntity> entity, Object entityId) {

        Session session = this.sessionFactory.getCurrentSession();

        String identityField = objectRegistry.getEntityIdentityFieldname(entity);

        if(identityField != null) {

            try {
                Class clazz = Class.forName(entity.getName());

                Criteria crit = session.createCriteria(clazz);
                crit.add(Restrictions.eq(identityField, entityId));

                List result = crit.list();

                if(result.size() == 1) {
                    return result.get(0);
                } else {
                    // TODO: throw a custom exception, error check for multiple returns
                }
            } catch(Exception e) {
                //TODO: catch this
                e.printStackTrace();
            }
        }

        return null;
    }

    public Object load(Class<DomainEntity> entity, Object entityId) {

        Session session = this.sessionFactory.getCurrentSession();

        String identityField = objectRegistry.getEntityIdentityFieldname(entity);

        if(identityField != null) {

            try {
                Class clazz = Class.forName(entity.getName());

                Criteria crit = session.createCriteria(clazz);
                crit.add(Restrictions.eq(identityField, entityId));

                List result = crit.list();

                if(result.size() == 1) {
                    return result.get(0);
                } else {
                    // TODO: throw a custom exception, error check for multiple returns
                }
            } catch(Exception e) {
                //TODO: catch this
                e.printStackTrace();
            }
        }

        return null;
    }

}
