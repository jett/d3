package ph.hatch.ddd.oe.test.domain.repository;

import ph.hatch.ddd.oe.test.domain.Employee;
import ph.hatch.ddd.oe.test.domain.EmployeeId;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMemRepository {

    Map dataStore = new HashMap();

    public void save(Employee employee) {

        dataStore.put(employee.getEmployeeId().toString(), employee);

    }

    public Employee load(EmployeeId employeeId) {

        if(dataStore.containsKey(employeeId.toString())) {
            return (Employee) dataStore.get(employeeId.toString());
        } else {
            return null;
        }

    }

}
