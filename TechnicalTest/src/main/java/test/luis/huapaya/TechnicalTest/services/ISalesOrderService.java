package test.luis.huapaya.TechnicalTest.services;

import test.luis.huapaya.TechnicalTest.models.SalesOrder;

import java.io.IOException;
import java.util.List;

public interface ISalesOrderService {
    List<SalesOrder> salesOrderList() throws IOException;
}
