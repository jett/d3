package ph.hatch.ddd.oe.test.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CountryCode implements Serializable {

    @Column(name = "COUNTRY_CD")
    String code;

    public CountryCode(){

    }

    public CountryCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
