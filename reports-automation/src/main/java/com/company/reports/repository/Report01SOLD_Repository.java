package com.company.reports.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.reports.entity.Report01SOLD;
import com.company.reports.utils.QueryReader;

@Repository
public class Report01SOLD_Repository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Report01SOLD> findAll() {
        String queryPath = "src/main/Queries/Report01-SOLD.sql";
        String sql = QueryReader.loadQuery(queryPath);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Report01SOLD.class));
    }
}