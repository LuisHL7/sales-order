package test.luis.huapaya.TechnicalTest.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import test.luis.huapaya.TechnicalTest.models.SalesOrder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class ExcelGenerator {
    private List<SalesOrder> salesOrderList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<SalesOrder> salesOrderList) {
        this.salesOrderList = salesOrderList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Sales Order");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);

        style.setFont(font);
        createCell(row, 0, "DeliveryId", style);
        createCell(row, 1, "Code", style);
        createCell(row, 2, "Name", style);
        createCell(row, 3, "LineNumber", style);
        createCell(row, 4, "Sku", style);
        createCell(row, 5, "ExpectedQuantity", style);
        createCell(row, 6, "Address", style);
        createCell(row, 7, "PostalCode", style);
        createCell(row, 8, "City", style);
        createCell(row, 9, "State", style);
        createCell(row, 10, "CountryIso", style);
        createCell(row, 11, "Telephone", style);
        createCell(row, 12, "Email", style);
        createCell(row, 13, "ContactPersons", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (SalesOrder record: salesOrderList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getCode(), style);
            createCell(row, columnCount++, record.getContactName(), style);
            createCell(row, columnCount++, record.getNumber(), style);
            createCell(row, columnCount++, record.getProducts()[0].getSku(), style);
            createCell(row, columnCount++, record.getProducts()[0].getUnits(), style);
            createCell(row, columnCount++, record.getAddress().getAddress(), style);
            createCell(row, columnCount++, record.getAddress().getPostalCode(), style);
            createCell(row, columnCount++, record.getAddress().getCity(), style);
            createCell(row, columnCount++, record.getAddress().getProvince(), style);
            createCell(row, columnCount++, record.getAddress().getCountryCode(), style);
            createCell(row, columnCount++, record.getPhone(), style);
            createCell(row, columnCount++, record.getEmail(), style);
            createCell(row, columnCount + 1, record.getContactPersons(), style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
