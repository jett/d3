package ph.hatch.ddd.oe.test.domain.repository;

import ph.hatch.ddd.oe.test.domain.Employee;
import ph.hatch.ddd.oe.test.domain.PersonId;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMemRepository {

    Map dataStore = new HashMap();

    public void save(Employee employee) {

        dataStore.put(employee.getPersonId().toString(), employee);

    }

    public Employee load(PersonId personId) {

        if(dataStore.containsKey(personId.toString())) {
            return (Employee) dataStore.get(personId.toString());
        } else {
            return null;
        }

    }

}
