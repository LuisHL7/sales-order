package test.luis.huapaya.TechnicalTest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String phone;
    private String email;
    private ContactPerson[] contactPersons;
}
