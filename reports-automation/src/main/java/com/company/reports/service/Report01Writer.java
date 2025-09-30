package com.company.reports.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.company.reports.entity.Report01SOLD;

public class Report01Writer implements ReportWriter<Report01SOLD> {
	
	public double StcgBuyValue = 0;
	public double StcgSellValue = 0;
	public double LtcgBuyValue = 0;
	public double LtcgSellValue = 0;

	@Override
	public void writerReport(Workbook workbook, Sheet sheet, Iterable<Report01SOLD> data) {

		CellStyle decimalStyle = ExcelGenerator.createDecimalStyle(workbook);
		CellStyle headerStyle = ExcelGenerator.createHeaderStyle(workbook);
		CellStyle dateStyle = ExcelGenerator.createOnlyDateStyle(workbook);

		String[] headers = { "ID", "NSECode", "StockName", "BuyingDate", "BuyingPrice", "SellingDate", "SellingPrice",
				"Quantity", "BuyingValue", "SellingValue", "RealizedProfit", "TaxCategory" };

		Row headersRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headersRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerStyle);
		}

		int rowNum = 1;
		for (Report01SOLD temp : data) {
			Row row = sheet.createRow(rowNum++);

			// Column 01 - ID
			row.createCell(0).setCellValue(temp.getID());

			// Column 02 - NSECode
			row.createCell(1).setCellValue(temp.getNSECode());

			// Column 03 - StockName
			row.createCell(2).setCellValue(temp.getStockName());

			// Column 04 - BuyingDate
			Cell buyingDateCell = row.createCell(3);
			buyingDateCell.setCellValue(java.sql.Date.valueOf(temp.getBuyingDate()));
			buyingDateCell.setCellStyle(dateStyle);

			// Column 05 - BuyingPrice
			Cell buyingPriceCell = row.createCell(4);
			buyingPriceCell.setCellValue(temp.getBuyingPrice());
			buyingPriceCell.setCellStyle(decimalStyle);

			// Column 06 - SellingDate
			Cell sellingDateCell = row.createCell(5);
			sellingDateCell.setCellValue(java.sql.Date.valueOf(temp.getSellingDate()));
			sellingDateCell.setCellStyle(dateStyle);

			// Column 07 - SellingPrice
			Cell sellingPriceCell = row.createCell(6);
			sellingPriceCell.setCellValue(temp.getSellingPrice());
			sellingPriceCell.setCellStyle(decimalStyle);

			// Column 08 - Quantity
			row.createCell(7).setCellValue(temp.getQuantity());

			// Column 09 - BuyingValue
			Cell buyingValueCell = row.createCell(8);
			buyingValueCell.setCellValue(temp.getBuyingValue());
			buyingValueCell.setCellStyle(decimalStyle);

			// Column 10 - SellingValue
			Cell sellingValueCell = row.createCell(9);
			sellingValueCell.setCellValue(temp.getSellingValue());
			sellingValueCell.setCellStyle(decimalStyle);

			// Column 11 - RealizedProfit
			Cell realizedProfitCell = row.createCell(10);
			realizedProfitCell.setCellValue(temp.getRealizedProfit());
			realizedProfitCell.setCellStyle(decimalStyle);

			// Column 12 - TaxCategory
			row.createCell(11).setCellValue(temp.getTaxCategory());

			if (temp.getTaxCategory() != null) {
				if (temp.getTaxCategory().equals("STCG")) {
					StcgBuyValue += temp.getBuyingValue();
					StcgSellValue += temp.getSellingValue();
				} else if (temp.getTaxCategory().equals("LTCG")) {
					LtcgBuyValue += temp.getBuyingValue();
					LtcgSellValue += temp.getSellingValue();
				}
			}
		}

		sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, headers.length - 1));
		sheet.createFreezePane(0, 1);
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}

		try {
			writeSummaryExcel(StcgBuyValue, StcgSellValue, LtcgBuyValue, LtcgSellValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeSummaryExcel(double value1, double value2, double value3, double value4) throws Exception {
		
		String filePath = "D:\\Z_Projects\\ReportsAutomation\\Dashboard.xlsx";
		// String filePath ="D:/Z_Projects/ReportsAutomation/Dashboard.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook summaryWorkbook = new XSSFWorkbook(fis);
		fis.close();
		
		Sheet summarySheet = summaryWorkbook.getSheet("Stocks");

		Row row = summarySheet.createRow(3);
		row.createCell(2).setCellValue(value1);
		row.createCell(3).setCellValue(value2);
		row.createCell(4).setCellValue(value2 - value1);
		
		row.createCell(6).setCellValue(value3);
		row.createCell(7).setCellValue(value4);
		row.createCell(8).setCellValue(value4 - value3);
		
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			summaryWorkbook.write(fos);
			fos.close();
		}
		summaryWorkbook.close();

	}
}
