package com.greeneyback.member.controller.data;



import com.greeneyback.member.service.HotelService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("http://localhost:8082")
@Controller
@RequestMapping("/api/csv")
@RequiredArgsConstructor
@Slf4j
public class HotelDataController {

    // 호텔 데이터 삽입
    private final HotelService hotelService;
    @GetMapping("/hotel/download")
    public void csvDown(HttpServletResponse response) throws CsvValidationException, IOException {
        hotelService.csvHotelDataSave();
    }


}
