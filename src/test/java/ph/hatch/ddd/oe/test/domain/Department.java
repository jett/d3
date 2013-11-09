package ph.hatch.ddd.oe.test.domain;

import org.hibernate.annotations.Fetch;
import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
@DomainEntity
public class Department {

    @EmbeddedId
    @DomainEntityIdentity
    DepartmentId departmentId;

    @Column(name = "NAME")
    String departmentName;

    @Column(name = "BUDGET")
    BigDecimal budget;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
            (
                    name="DEPT_EMPLOYEES",
                    joinColumns=@JoinColumn(name="DEPT_ID")
            )
    @Fetch(value = org.hibernate.annotations.FetchMode.SELECT)
    @Column(name="EMP_ID")
    Set<EmployeeId> employeeIds;

    public Department(){

    }

    public Department(String id, String name) {

        departmentId = new DepartmentId(id);
        this.departmentName = name;

        this.employeeIds = new HashSet<EmployeeId>();
        this.budget = new BigDecimal(20000000);

    }

    public void addEmployee(EmployeeId employeeId) {

        employeeIds.add(employeeId);

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public DepartmentId getDepartmentId() {
        return this.departmentId;
    }

    public Set getEmployees() {

        return employeeIds;

    }

}
