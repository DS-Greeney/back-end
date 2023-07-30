package com.greeneyback.member.controller;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.helper.CSVHelper;
import com.greeneyback.member.message.ResponseMessage;
import com.greeneyback.member.service.RstrntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/csv")
public class RstrntController {

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
}
