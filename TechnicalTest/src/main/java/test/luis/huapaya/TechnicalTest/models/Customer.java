package test.luis.huapaya.TechnicalTest.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class Customer {
    private List<CustomField> customFields;
    private String name;
    private Address billAddress;
    private String phone;
    private String email;
    private List<ContactPerson> contactPersons;
}
