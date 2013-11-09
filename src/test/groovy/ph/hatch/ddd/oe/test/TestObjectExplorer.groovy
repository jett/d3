package ph.hatch.ddd.oe.test

import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import ph.hatch.ddd.oe.ObjectRegistry
import ph.hatch.ddd.oe.test.domain.Department
import ph.hatch.ddd.oe.test.domain.DepartmentId
import ph.hatch.ddd.oe.test.domain.Employee
import ph.hatch.ddd.oe.test.domain.EmployeeId
import ph.hatch.ddd.oe.test.domain.repository.DepartmentMemRepository
import ph.hatch.ddd.oe.test.domain.repository.EmployeeMemRepository

class TestObjectExplorer {

    @Before
    public void testSetup() {

    }

    @Test
    public void testExplorer() {

        EmployeeMemRepository employeeRepository = new EmployeeMemRepository()
        DepartmentMemRepository departmentRepository = new DepartmentMemRepository()

        employeeRepository.save(new Employee("EMP-001", "Jett", "Gamboa"))
        employeeRepository.save(new Employee("EMP-002", "Anne", "Gamboa"))

        Department department = new Department("OPS", "Operations")
        department.addEmployee(new EmployeeId("EMP-001"))

        departmentRepository.save(department)

        //println employeeRepository.load(new EmployeeId("EMP-001"))
        department = departmentRepository.load(new DepartmentId("OPS"))

        Gson gson = new Gson()
        println gson.fromJson(gson.toJson(department), Map.class)

    }

    @Test
    public void testCollections() {

        ObjectRegistry objectExplorer = new ObjectRegistry("ph.hatch.ddd.oe.test.domain");

    }
}
