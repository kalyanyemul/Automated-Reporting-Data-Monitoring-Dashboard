package com.company.reports.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.reports.entity.Report02HOLD;
import com.company.reports.utils.QueryReader;

@Repository
public class Report02HOLD_Repository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Report02HOLD> findAll() {
        String queryPath = "src/main/Queries/Report02-HOLD.sql";
        String sql = QueryReader.loadQuery(queryPath);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Report02HOLD.class));
    }
}