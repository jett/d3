package ph.hatch.ddd.oe.test.domain;

import ph.hatch.ddd.domain.annotations.DomainEntity;
import ph.hatch.ddd.domain.annotations.DomainEntityIdentity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
@DomainEntity
public class Country {

    @EmbeddedId
    @DomainEntityIdentity
    ph.hatch.ddd.oe.test.domain.CountryCode countryCode;

    @Column(name = "NAME")
    String name;

    public Country(){}

    public Country(String code, String name) {
        this.countryCode = new CountryCode(code);
        this.name = name;
    }


}
