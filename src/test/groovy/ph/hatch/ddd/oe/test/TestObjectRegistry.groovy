package ph.hatch.ddd.oe.test

import org.junit.Test
import ph.hatch.ddd.oe.ObjectMeta
import ph.hatch.ddd.oe.ObjectRegistry
import ph.hatch.ddd.oe.test.domain.Department

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "classpath:oe/test-context.xml")
//@Transactional
class TestObjectRegistry {

//    @Autowired
//    ObjectRegistry objectRegistry;


    private digGraph

    @Test
    public void testDomainStructure() {

        ObjectRegistry objectRegistry = new ObjectRegistry("ph.hatch.ddd.oe.test.domain")

        ObjectMeta meta = objectRegistry.getMetaForClass(Department.class)

        meta.getFields().each() { k, v ->

            ObjectMeta fieldMeta = v

            println k + " " + fieldMeta.isCollection() + " " + fieldMeta.getClassName()

        }

    }

}
