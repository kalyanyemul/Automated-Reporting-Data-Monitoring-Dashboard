package com.company.reports.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class QueryReader {
    public static String loadQuery(String filePath){
        try{
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
