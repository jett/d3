package ph.hatch.ddd.oe.test.domain.repository;

import ph.hatch.ddd.oe.test.domain.Department;
import ph.hatch.ddd.oe.test.domain.DepartmentId;

import java.util.HashMap;
import java.util.Map;

public class DepartmentMemRepository {

    Map dataStore = new HashMap();

    public void save(Department department) {

        dataStore.put(department.getDepartmentId().toString(), department);

    }

    public Department load(DepartmentId departmentId) {

        if(dataStore.containsKey(departmentId.toString())) {
            return (Department) dataStore.get(departmentId.toString());
        } else {
            return null;
        }

    }

}
