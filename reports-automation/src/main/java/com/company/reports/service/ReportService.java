package com.company.reports.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.reports.entity.Report01SOLD;
import com.company.reports.entity.Report02HOLD;
import com.company.reports.repository.Report01SOLD_Repository;
import com.company.reports.repository.Report02HOLD_Repository;

@Service
public class ReportService {

    @Autowired
    private Report01SOLD_Repository report01Repo;
    
    @Autowired
    private Report02HOLD_Repository report02Repo;

    public String generateReport01SOLD() {
        List<Report01SOLD> data = report01Repo.findAll();

        try {
            Workbook workbook = ExcelGenerator.creWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            ReportWriter<Report01SOLD> writer = new Report01Writer();
            writer.writerReport(workbook, sheet, data);

            String folderPath = "src/main/Generated-reports";
            String YYYYMMDD = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String fileName = YYYYMMDD + "_Tax-report-generated.xlsx";

            ExcelGenerator.saveWorkbook(workbook, folderPath, fileName);
            return "Done : " + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Generating Excel Sheet : " + e.getMessage();
        }
    }
    
    public String generateReport02HOLD() {
        List<Report02HOLD> data = report02Repo.findAll();

        try {
            Workbook workbook = ExcelGenerator.creWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            ReportWriter<Report02HOLD> writer = new Report02Writer();
            writer.writerReport(workbook, sheet, data);

            String folderPath = "src/main/Generated-reports";
            String YYYYMMDD = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String fileName = YYYYMMDD + "_Holding-report-generated.xlsx";

            ExcelGenerator.saveWorkbook(workbook, folderPath, fileName);
            return "Done : " + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Generating Excel Sheet : " + e.getMessage();
        }
    }
}
