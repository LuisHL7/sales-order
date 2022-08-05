package test.luis.huapaya.TechnicalTest.services;

import test.luis.huapaya.TechnicalTest.models.Customer;

import java.io.IOException;
import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomerList() throws IOException;
}
