package ph.hatch.ddd.oe.test.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProvinceCode implements Serializable {

    @Column(name = "PROVINCE_CD")
    String code;

    public ProvinceCode(){

    }

    public ProvinceCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
