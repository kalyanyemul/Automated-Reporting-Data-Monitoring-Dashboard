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

import com.company.reports.entity.Report02HOLD;

public class Report02Writer implements ReportWriter<Report02HOLD> {

	public double InvestValue = 0;
	public double CurrentValue = 0;

	@Override
	public void writerReport(Workbook workbook, Sheet sheet, Iterable<Report02HOLD> data) {

		CellStyle decimalStyle = ExcelGenerator.createDecimalStyle(workbook);
		CellStyle headerStyle = ExcelGenerator.createHeaderStyle(workbook);
		CellStyle dateStyle = ExcelGenerator.createOnlyDateStyle(workbook);

		String[] headers = { "ID", "NSECode", "StockName", "BuyingDate", "BuyingPrice", "Quantity", "CurrentPrice",
				"InvestmentValue", "CurrentValue", "UnrealizedProfit" };

		Row headersRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headersRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerStyle);
		}

		int rowNum = 1;
		for (Report02HOLD temp : data) {
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

			// Column 06 - Quantity
			row.createCell(5).setCellValue(temp.getQuantity());

			// Column 07 - CurrentPrice
			Cell currentPriceCell = row.createCell(6);
			currentPriceCell.setCellValue(temp.getCurrentPrice());
			currentPriceCell.setCellStyle(decimalStyle);

			// Column 08 - InvestmentValue
			Cell investmentValueCell = row.createCell(7);
			investmentValueCell.setCellValue(temp.getInvestmentValue());
			investmentValueCell.setCellStyle(decimalStyle);

			// Column 09 - CurrentValue
			Cell currentValueCell = row.createCell(8);
			currentValueCell.setCellValue(temp.getCurrentValue());
			currentValueCell.setCellStyle(decimalStyle);

			// Column 10 - UnrealizedProfit
			Cell unrealizedProfitCell = row.createCell(9);
			unrealizedProfitCell.setCellValue(temp.getUnrealizedProfit());
			unrealizedProfitCell.setCellStyle(decimalStyle);

			if (temp.getStockName() != null) {
				InvestValue += temp.getInvestmentValue();
				CurrentValue += temp.getCurrentValue();
			}
		}

		sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, headers.length - 1));
		sheet.createFreezePane(0, 1);
		for (int i = 0; i < headers.length; i++) {
			sheet.autoSizeColumn(i);
		}

		try {
			writeSummaryExcel(InvestValue, CurrentValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeSummaryExcel(double value1, double value2) throws Exception {
		String filePath = "D:\\Z_Projects\\ReportsAutomation\\Dashboard.xlsx";
		// String filePath ="D:/Z_Projects/ReportsAutomation/Dashboard.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook summaryWorkbook = new XSSFWorkbook(fis);
		fis.close();
		
		Sheet summarySheet = summaryWorkbook.getSheet("Stocks");

		Row row = summarySheet.createRow(6);
		row.createCell(2).setCellValue(value1);
		row.createCell(3).setCellValue(value2);
		row.createCell(4).setCellValue(value2 - value1);

		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			summaryWorkbook.write(fos);
			fos.close();
		}
		summaryWorkbook.close();

	}
}
