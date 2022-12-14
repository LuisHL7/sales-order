package test.luis.huapaya.TechnicalTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.luis.huapaya.TechnicalTest.services.ExcelGenerator;
import test.luis.huapaya.TechnicalTest.models.SalesOrder;
import test.luis.huapaya.TechnicalTest.services.ISalesOrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SalesOrderController {

    @Autowired
    private ISalesOrderService salesOrderService;

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=salesOrder" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <SalesOrder> listOfSalesOrder = salesOrderService.salesOrderList();

        ExcelGenerator generator = new ExcelGenerator(listOfSalesOrder);
        generator.generateExcelFile(response);
    }

    @GetMapping("/sales-order")
    public List<SalesOrder> getSalesOrderList() throws IOException {
        return salesOrderService.salesOrderList();
    }


}
