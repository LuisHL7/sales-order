package test.luis.huapaya.TechnicalTest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesOrder {
    private String id;
    private String code;
    private String contactName;
    private int number;
    private int sku;
    private int units;
    private Address address;
    private String phone;
    private String email;
    private String contactPersons;

}
