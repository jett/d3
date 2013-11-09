package ph.hatch.ddd.oe.test

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import ph.hatch.ddd.domain.annotations.DomainEntity
import ph.hatch.ddd.oe.OESerializers
import ph.hatch.ddd.oe.ObjectExplorer
import ph.hatch.ddd.oe.ObjectRegistry
import ph.hatch.ddd.oe.ObjectRepository
import ph.hatch.ddd.oe.test.domain.*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:oe/test-context.xml")
@Transactional
class TestOE {

    @Autowired
    ObjectRepository objectRepository;

    @Autowired
    ObjectRegistry objectRegistry;

    @Before
    public void testPersistence() {

        Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create()

        objectRepository.persist(new Country("PH", "Philippines"));
        objectRepository.persist(new Province("001", "NCR", new CountryCode("PH")));

        objectRepository.persist(new Employee("EMP-001", "Jett", "Gamboa", new ProvinceCode("001")))
        objectRepository.persist(new Employee("EMP-002", "Anne", "Gamboa"))
        objectRepository.persist(new Employee("EMP-003", "Juan", "dela Cruz"))

        Object result = objectRepository.load(Employee.class, new EmployeeId("EMP-003"))

        //println gson.fromJson(gson.toJson(result), Map.class)

        Department department = new Department("OPS", "Operations")
        department.addEmployee(new EmployeeId("EMP-001"))
        department.addEmployee(new EmployeeId("EMP-003"))

        objectRepository.persist(department)

        result = objectRepository.load(Department.class, new DepartmentId("OPS"))

        //println gson.fromJson(gson.toJson(result), Map.class)
    }

    @Test
    public void testExplorer() {

        Gson gson = new Gson()

        Department department = objectRepository.loadByEntityId(Department.class, new DepartmentId("OPS"))
        ObjectExplorer objectExplorer = new ObjectExplorer(objectRegistry, objectRepository);

        //objectExplorer.buildMap(department)
        objectExplorer.setDateFormat("MM/dd/yyyy")
        objectExplorer.includeNulls(true);

        Map mymap = objectExplorer.explore(department, true)

        println mymap.employeeIds.size()

        mymap.employeeIds.each() { employee ->
            println employee.Employee.firstName +  " " + employee.Employee.lastName
        }
        //[0].Employee.firstName


        OESerializers oeSerializers = new OESerializers();

        println oeSerializers.maptoxml(mymap, "Department")


    }

}
