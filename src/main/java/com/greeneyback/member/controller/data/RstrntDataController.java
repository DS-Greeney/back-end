package com.greeneyback.member.controller.data;

import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.RstrntRepository;
import com.greeneyback.member.service.RstrntService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@CrossOrigin("http://localhost:8082")
@Controller
@RequestMapping("/api/csv")
@RequiredArgsConstructor
@Slf4j
public class RstrntDataController {
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
    private final RstrntRepository rstrntRepository;
    @GetMapping("/rstrnt/download") // 이 부분 수정했어요2
    public void csvDown(HttpServletResponse response) throws IOException, CsvValidationException {

        String[] restaurantInfo;

        // 이 부분 절대주소 주의....
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\Users\\ski03\\study\\greeney\\back-end\\src\\main\\resources\\csv\\rstrnt_data.csv"), "UTF-8"));
        csvReader.readNext();

        do {
            restaurantInfo = csvReader.readNext();

            if(restaurantInfo != null) {
                RstrntDTO rstrntDTO = new RstrntDTO();
                rstrntDTO.setRstrntId(Integer.parseInt(restaurantInfo[0])); // id
                rstrntDTO.setRstrntCtgry(restaurantInfo[1]);
                rstrntDTO.setRstrntName(restaurantInfo[2]);
                rstrntDTO.setRstrntAddr(restaurantInfo[3]);
                rstrntDTO.setRstrntTel(restaurantInfo[5]);
                rstrntDTO.setRstrntMenuinfo(restaurantInfo[7]);
                rstrntDTO.setRstrntLa(restaurantInfo[8]);
                rstrntDTO.setRstrntLo(restaurantInfo[9]);
                rstrntDTO.setRstrntStar(0);
                rstrntDTO.setAreaCode(0);

                rstrntService.restaurantSave(rstrntDTO);

            }
        } while (restaurantInfo != null);

    }

    @GetMapping("/rstrnt/areaCode")
    public void setAreaCode() {
        List<RstrntEntity> allRstrntEntities = rstrntService.findAllRstrntEntities();

        for (int i=0; i< allRstrntEntities.size(); i++) {
            if (allRstrntEntities.get(i).getRstrntAddr().contains("서울")) {
                allRstrntEntities.get(i).setAreaCode(1);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("인천")) {
                allRstrntEntities.get(i).setAreaCode(2);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("대전")) {
                allRstrntEntities.get(i).setAreaCode(3);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("대구")) {
                allRstrntEntities.get(i).setAreaCode(4);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("광주")) {
                allRstrntEntities.get(i).setAreaCode(5);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("부산")) {
                allRstrntEntities.get(i).setAreaCode(6);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("울산")) {
                allRstrntEntities.get(i).setAreaCode(7);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("세종")) {
                allRstrntEntities.get(i).setAreaCode(8);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("경기")) {
                allRstrntEntities.get(i).setAreaCode(31);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("강원")) {
                allRstrntEntities.get(i).setAreaCode(32);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("충청북도")) {
                allRstrntEntities.get(i).setAreaCode(33);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("충청남도")) {
                allRstrntEntities.get(i).setAreaCode(34);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("경상북도")) {
                allRstrntEntities.get(i).setAreaCode(35);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("경상남도")) {
                allRstrntEntities.get(i).setAreaCode(36);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("전라북도")) {
                allRstrntEntities.get(i).setAreaCode(37);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("전라남도")) {
                allRstrntEntities.get(i).setAreaCode(38);
                rstrntRepository.save(allRstrntEntities.get(i));
            }
            else if (allRstrntEntities.get(i).getRstrntAddr().contains("제주")) {
                allRstrntEntities.get(i).setAreaCode(39);
                rstrntRepository.save(allRstrntEntities.get(i));
            }

        }

    }

}
