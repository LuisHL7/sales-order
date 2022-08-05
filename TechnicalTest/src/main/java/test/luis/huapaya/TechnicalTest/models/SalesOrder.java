package test.luis.huapaya.TechnicalTest.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesOrder {
    private String id;
    private int code;
    private String contactName;
    private int number;
    private int sku;
    private int units;

}
