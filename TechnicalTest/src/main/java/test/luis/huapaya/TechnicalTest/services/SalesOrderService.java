package test.luis.huapaya.TechnicalTest.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.luis.huapaya.TechnicalTest.models.Customer;
import test.luis.huapaya.TechnicalTest.models.Product;
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
        List<SalesOrder> salesOrderList = objectMapper.readValue(body.string(), new TypeReference<>() {
        });
        addDataCustomer(salesOrderList);
        return salesOrderList;
    }

    private void addDataCustomer(List<SalesOrder> salesOrderList) throws IOException {
        List<Customer> customerList = customerService.getCustomerList();
        for (SalesOrder salesOrder : salesOrderList) {
            boolean verify = false;
            for (int j = 0; j < customerList.size() && !verify; j++) {
                if (salesOrder.getContactName().equals(customerList.get(j).getName())) {
                    salesOrder.setCode(customerList.get(j).getCustomId());
                    salesOrder.setAddress(customerList.get(j).getBillAddress());
                    salesOrder.setPhone(customerList.get(j).getPhone());
                    salesOrder.setEmail(customerList.get(j).getEmail());
                    if (customerList.get(j).getContactPersons().length > 0) {
                        salesOrder.setContactPersons(customerList.get(j).getContactPersons()[0].getName());
                    }
                    verify = true;
                }
            }
        }
        System.out.println(salesOrderList);
    }


}
