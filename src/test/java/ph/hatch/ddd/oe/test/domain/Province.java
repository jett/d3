package ph.hatch.ddd.oe.test.domain;

import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import javax.persistence.*;

@Entity
@Table(name = "PROVINCE")
@DomainEntity
public class Province {

    @EmbeddedId
    @DomainEntityIdentity
    ph.hatch.ddd.oe.test.domain.ProvinceCode provinceCode;

    @Column(name = "NAME")
    String name;

//    @Embedded
//    @AttributeOverride(name = "countryCode", column = @Column(name="", nullable=true))
    CountryCode countryCode;

    public Province(){}

    public Province(String code, String name) {
        this.provinceCode = new ProvinceCode(code);
        this.name = name;
    }

    public Province(String code, String name, CountryCode countryCode) {
        this.provinceCode = new ProvinceCode(code);
        this.name = name;
        this.countryCode = countryCode;
    }


}
