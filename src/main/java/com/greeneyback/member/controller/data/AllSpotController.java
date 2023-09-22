package com.greeneyback.member.controller.data;


import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.entity.AllSpotEntity;
import com.greeneyback.member.repository.AllSpotRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

// 모든 spot data를 db에 넣는 controller
@CrossOrigin("http://localhost:8082")
@Controller
@RequestMapping("/api/csv")
@RequiredArgsConstructor
@Slf4j
public class AllSpotController {

    private final AllSpotRepository allSpotRepository;


    @GetMapping("/allspot/download")
    public void csvDown(HttpServletResponse response) throws IOException, CsvValidationException {

        String[] allSpotInfo;

        // 이 부분 절대주소 주의....
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\Users\\ski03\\study\\greeney\\back-end\\src\\main\\resources\\csv\\all_spot_data.csv"), "UTF-8"));
        csvReader.readNext();

        do {
            allSpotInfo = csvReader.readNext();

            if(allSpotInfo != null) {
                AllSpotEntity allSpotEntity = new AllSpotEntity();
                allSpotEntity.setDataCode(Integer.valueOf(allSpotInfo[0]));  // pk
                allSpotEntity.setSpotId(Integer.valueOf(allSpotInfo[1]));  // 고유 ID
                allSpotEntity.setCategoryNumber(Integer.valueOf(allSpotInfo[2]));
                allSpotEntity.setAreaCode(Integer.valueOf(allSpotInfo[3]));
                allSpotEntity.setAddress(allSpotInfo[4]);
                allSpotEntity.setLatitude(BigDecimal.valueOf(Double.parseDouble(allSpotInfo[5])));
                allSpotEntity.setLongitude(BigDecimal.valueOf(Double.parseDouble(allSpotInfo[6])));
                allSpotEntity.setSpotName(allSpotInfo[7]);
                allSpotEntity.setSpotInfo(allSpotInfo[8]);
                allSpotEntity.setSpotImage(allSpotInfo[9]);

                allSpotRepository.save(allSpotEntity);

            }
        } while (allSpotInfo != null);

    }


}
