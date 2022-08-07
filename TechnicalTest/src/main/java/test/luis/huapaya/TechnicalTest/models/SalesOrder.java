package test.luis.huapaya.TechnicalTest.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SalesOrder {
    private String docNumber;
    private List<CustomField> codeCustomer = new ArrayList<>();;
    private String contactName;
    private int number;
    private List<Product> products;
    private Address address;
    private String phone;
    private String email;
    private List<ContactPerson> contactPersons = new ArrayList<>();
}
