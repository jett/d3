package ph.hatch.ddd.oe.test.domain;

import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@PrimaryKeyJoinColumn(name="PERSON_ID")
@DomainEntity
public class Employee extends Person {

    @Embedded
    ph.hatch.ddd.oe.test.domain.EmployeeId employeeId;

    @Embedded
    ph.hatch.ddd.oe.test.domain.ProvinceCode birthProvinceCode;

    @Column(name="birthday")
    Date birthdate;

    public Employee(){

    }

    public Employee(String personId, String firstName, String lastName) {

        this.personId = new PersonId(personId);

        this.firstName = firstName;
        this.lastName = lastName;

        this.birthdate = new Date("12/13/1975");

    }

    public Employee(String personId, String firstName, String lastName, ProvinceCode birthProvinceCode) {

        this.personId= new PersonId(personId);

        this.firstName = firstName;
        this.lastName = lastName;

        this.birthProvinceCode = birthProvinceCode;

    }

    public EmployeeId getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {

        return this.firstName;

    }

    public String getLastName() {

        return this.lastName;

    }


}
