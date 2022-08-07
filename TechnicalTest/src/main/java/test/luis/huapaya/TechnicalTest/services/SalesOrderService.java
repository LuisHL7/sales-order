package test.luis.huapaya.TechnicalTest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.luis.huapaya.TechnicalTest.models.Customer;
import test.luis.huapaya.TechnicalTest.models.SalesOrder;

import java.io.IOException;
import java.util.List;

@Service
public class SalesOrderService implements ISalesOrderService {

    @Autowired
    private ICustomerService customerService;


    @Override
    public List<SalesOrder> salesOrderList() throws IOException {
        ResponseBody body = ConsumerApi.apiConsumer("https://api.holded.com/api/invoicing/v1/documents/salesorder").body();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<SalesOrder> salesOrderList = objectMapper.readValue(body.string(), new TypeReference<>() {});
        System.out.println(salesOrderList);
        addDataCustomer(salesOrderList);
        return salesOrderList;
    }

    private void addDataCustomer(List<SalesOrder> salesOrderList) throws IOException {
        List<Customer> customerList = customerService.getCustomerList();
        System.out.println(customerList);
        for (SalesOrder salesOrder : salesOrderList) {
            boolean verify = false;
            for (int j = 0; j < customerList.size() && !verify; j++) {
                if (salesOrder.getContactName().equalsIgnoreCase(customerList.get(j).getName())) {
                    if (customerList.get(j).getCustomFields().size() > 0) salesOrder.setCodeCustomer(customerList.get(j).getCustomFields());

                    salesOrder.setAddress(customerList.get(j).getBillAddress());
                    salesOrder.setPhone(customerList.get(j).getPhone());
                    salesOrder.setEmail(customerList.get(j).getEmail());
                    System.out.println(customerList.get(j).getContactPersons());
                    if (customerList.get(j).getContactPersons().size() > 0) salesOrder.setContactPersons(customerList.get(j).getContactPersons());
                    verify = true;
                }
            }
        }
        System.out.println(salesOrderList);
    }


}
