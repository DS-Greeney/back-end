package com.greeneyback.member.helper;

import com.greeneyback.member.entity.RstrntEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "RSTRNT_ID", "RSTRNT_NM", "RSTRNT_ROAD_NM_ADDR", "RSTRNT_TEL_NO", "SLE_VGTR_MENU_INFO_DC", "RSTRNT_LA", "RSTRNT_LO" };

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

//    public static List<RstrntEntity> csvToRstrntEntities(InputStream is) {
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//
//            List<RstrntEntity> rstrntEntities = new ArrayList<RstrntEntity>();
//
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//            for (CSVRecord csvRecord : csvRecords) {
//                RstrntEntity rstrntEntity = new RstrntEntity(
//                        csvRecord.get("RSTRNT_ID"),
//                        csvRecord.get("RSTRNT_CTGRY_N"),
//                        csvRecord.get("RSTRNT_NM"),
//                        csvRecord.get("RSTRNT_ROAD_NM_ADDR"),
//                        csvRecord.get("RSTRNT_TEL_NO"),
//                        csvRecord.get("SLE_VGTR_MENU_INFO_DC"),
//                        csvRecord.get("RSTRNT_LA"),
//                        csvRecord.get("RSTRNT_LO"),
//                        0,
//                        0
//                );
//
//                rstrntEntities.add(rstrntEntity);
//            }
//
//            return rstrntEntities;
//
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file: "+e.getMessage());
//        }
//    }

}
