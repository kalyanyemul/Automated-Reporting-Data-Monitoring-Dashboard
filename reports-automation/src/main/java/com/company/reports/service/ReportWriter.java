package com.company.reports.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface ReportWriter<T> {
    void writerReport(Workbook workbook, Sheet sheet, Iterable<T> data);
}
