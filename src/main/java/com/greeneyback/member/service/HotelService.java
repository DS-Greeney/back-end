package com.greeneyback.member.service;


import com.greeneyback.member.entity.HotelEntity;
import com.greeneyback.member.repository.HotelRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public void csvHotelDataSave() throws IOException, CsvValidationException {

        String[] hotelInfo;

        // 이 부분 절대주소 주의....
        CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\Users\\ski03\\study\\greeney\\back-end\\src\\main\\resources\\csv\\hotel_data.csv"), "UTF-8"));
        csvReader.readNext();

        do {
            hotelInfo = csvReader.readNext();

            if(hotelInfo != null) {
                HotelEntity hotelEntity = new HotelEntity();

                hotelEntity.setHotelId(Integer.valueOf(hotelInfo[0]));
                hotelEntity.setHotelName(hotelInfo[1]);
                hotelEntity.setHotelAddr(hotelInfo[2]);
                hotelEntity.setHotelTel(hotelInfo[3]);
                hotelEntity.setHotelService(hotelInfo[4]);
                hotelEntity.setHotelInfo(hotelInfo[5]);
                hotelEntity.setHotelLa(BigDecimal.valueOf(Double.parseDouble(hotelInfo[6])));
                hotelEntity.setHotelLo(BigDecimal.valueOf(Double.parseDouble(hotelInfo[7])));
                hotelEntity.setAreaCode(Integer.valueOf(hotelInfo[8]));
                hotelEntity.setHotelUrl(hotelInfo[9]);
                hotelEntity.setHotelStar(0);

                hotelRepository.save(hotelEntity);
            }

        } while (hotelInfo != null);

    }

}
