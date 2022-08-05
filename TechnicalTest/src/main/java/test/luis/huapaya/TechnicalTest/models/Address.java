package test.luis.huapaya.TechnicalTest.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
    private String address;
    private String postalCode;
    private String city;
    private String province;
    private String countryCode;
}
