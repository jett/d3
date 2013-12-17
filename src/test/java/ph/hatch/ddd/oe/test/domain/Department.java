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
                    name="DEPT_PERSON",
                    joinColumns=@JoinColumn(name="DEPT_ID")
            )
    @Fetch(value = org.hibernate.annotations.FetchMode.SELECT)
    @Column(name="PERSON_ID")
    Set<PersonId> personIds;

    public Department(){

    }

    public Department(String id, String name) {

        departmentId = new DepartmentId(id);
        this.departmentName = name;

        this.personIds = new HashSet<PersonId>();
        this.budget = new BigDecimal(20000000);

    }

    public void addPerson(PersonId personId) {

        personIds.add(personId);

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public DepartmentId getDepartmentId() {
        return this.departmentId;
    }

    public Set getEmployees() {

        return personIds;

    }

}
