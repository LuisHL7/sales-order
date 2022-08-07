package test.luis.huapaya.TechnicalTest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CustomField {
    private Object value;
}
