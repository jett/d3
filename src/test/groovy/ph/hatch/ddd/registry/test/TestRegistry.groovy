package ph.hatch.ddd.registry.test

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import ph.hatch.ddd.oe.OESerializers
import ph.hatch.ddd.oe.ObjectExplorer
import ph.hatch.ddd.oe.ObjectMeta
import ph.hatch.ddd.oe.ObjectRegistry
import ph.hatch.ddd.oe.ObjectRepository
import ph.hatch.ddd.oe.test.domain.*

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "classpath:oe/test-context.xml")
//@Transactional
class TestRegistry {


    @Test
    public void testMeta() {

        ObjectRegistry or = new ObjectRegistry("ph.hatch.ddd.oe.test.domain")

        println or.getEntities()

        or.entities.each() { entity ->
            ObjectMeta meta =  or.getMetaForClass(Class.forName(entity))

            println meta
        }

    }

}
