package ph.hatch.ddd.oe.test.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    @Column(name = "FNAME")
    String firstName;

    @Column(name = "LNAME")
    String lastName;

}
