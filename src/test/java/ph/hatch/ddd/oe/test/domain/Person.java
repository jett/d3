package ph.hatch.ddd.oe.test.domain;

import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy=InheritanceType.JOINED)
@DomainEntity
public abstract class Person {

    @EmbeddedId
    @DomainEntityIdentity
    PersonId personId;

    @Column(name = "FNAME")
    String firstName;

    @Column(name = "LNAME")
    String lastName;

    public PersonId getPersonId() {
        return personId;
    }

}
