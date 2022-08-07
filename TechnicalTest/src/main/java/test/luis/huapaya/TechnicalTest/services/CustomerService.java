package test.luis.huapaya.TechnicalTest.services;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Service;
import test.luis.huapaya.TechnicalTest.models.Customer;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Override
    public List<Customer> getCustomerList() throws IOException {
        ResponseBody body = ConsumerApi.apiConsumer("https://api.holded.com/api/invoicing/v1/contacts").body();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(body.string(), new TypeReference<>() {});

    }
}
