package com.greeneyback.member.controller;

import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.helper.CSVHelper;
import com.greeneyback.member.message.ResponseMessage;
import com.greeneyback.member.service.RstrntService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
@RequiredArgsConstructor
@Slf4j
public class RstrntController {
/*
    @Autowired
    RstrntService rstrntService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                rstrntService.save(file);

                message = "Uploaded the file successfully: "+file.getOriginalFilename();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: "+file.getOriginalFilename()+"!";

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<RstrntEntity>> getAllRstrnts() {
        try {
            List<RstrntEntity> rstrntEntities = rstrntService.getAllRstrntEntities();

            if (rstrntEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rstrntEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    private final RstrntService rstrntService;
    @GetMapping("/download")
    public void csvDown(HttpServletResponse response) throws IOException, CsvValidationException {


        String[] restaurantInfo;

        // 이 부분 절대주소 주의....
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\Users\\ski03\\study\\greeney\\back-end\\src\\main\\resources\\csv\\RT_VGTR_RSTRNT_INFO.csv"), "UTF-8"));
        csvReader.readNext();

        do {
            restaurantInfo = csvReader.readNext();

            if(restaurantInfo != null) {
                RstrntDTO rstrntDTO = new RstrntDTO();
                rstrntDTO.setRstrntId(restaurantInfo[0]); // id
                rstrntDTO.setRstrntCtgry(restaurantInfo[1]);
                rstrntDTO.setRstrntName(restaurantInfo[2]);
                rstrntDTO.setRstrntAddr(restaurantInfo[3]);
                rstrntDTO.setRstrntTel(restaurantInfo[5]);
                rstrntDTO.setRstrntMenuinfo(restaurantInfo[7]);
                rstrntDTO.setRstrntLa(restaurantInfo[8]);
                rstrntDTO.setRstrntLo(restaurantInfo[9]);

                rstrntService.restaurantSave(rstrntDTO);

            }
        } while (restaurantInfo != null);



    }
}
