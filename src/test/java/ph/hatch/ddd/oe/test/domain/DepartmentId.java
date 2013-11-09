package ph.hatch.ddd.oe.test.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DepartmentId implements Serializable {

    @Column(name = "DEPT_ID")
    String id;

    public DepartmentId(){
    }

    public DepartmentId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

}
