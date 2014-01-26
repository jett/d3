package ph.hatch.ddd.oe.test.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonId implements Serializable {

    @Column(name = "PERSON_ID")
    String id;

    public PersonId(){
    }

    public PersonId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

}
