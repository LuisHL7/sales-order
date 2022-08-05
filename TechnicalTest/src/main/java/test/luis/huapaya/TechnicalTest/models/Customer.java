package test.luis.huapaya.TechnicalTest.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer {
    private String customId;
    private String name;
    private Address billAddress;
    private String postalCode;
    private String city;
    private String province;
    private String countryCode;
    private String phone;
    private String email;
    private ContactPerson[] contactPersons;
}
