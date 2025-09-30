package com.company.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.reports.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/tax-report")
    public String generateReport01() {
        String msg = reportService.generateReport01SOLD();
        return msg;
    }
    
    @GetMapping("/holding-report")
    public String generateReport02() {
        String msg = reportService.generateReport02HOLD();
        return msg;
    }
}
