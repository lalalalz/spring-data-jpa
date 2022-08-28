package lalalalz.springdatajpa.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String city;
    private String street;

    protected Address() {
    }

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
