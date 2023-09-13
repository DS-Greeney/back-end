package com.greeneyback.member.controller.data;

import com.greeneyback.member.dto.AddrDTO;
import com.greeneyback.member.dto.TourspotDTO;
import com.greeneyback.member.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
public class TourDataController {

    // 투어 정보를 넣어줄 서비스 선언
    private final TourService tourService;

    // 투어 스팟의 area와 sigungucode 저장하는 get
    @GetMapping("/addr/api")
    public String callAreaApi() throws IOException, ParseException {
        StringBuilder result = new StringBuilder();

        String urlStr = "https://apis.data.go.kr/B551011/GreenTourService1"+
                "/areaCode1" +
                "?serviceKey=tK7Va58kue58fAehjhvQSgM9B1YDPZVt2UtOPh%2Fk1Os2UWleCMLcW5EZog%2FtRyj0E3ToAVSKf%2F2d9ogWT%2BTRfw%3D%3D" +
                "&numOfRows=100" +
                "&pageNo=1" +
                "&MobileOS=ETC" +
                "&MobileApp=AppTest" +
                "&areaCode=1" +  // areaCode 바뀔때마다 변경
                "&_type=json";
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONObject parseResponse = (JSONObject) jsonObject.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONObject parseItems = (JSONObject) parseBody.get("items");
            JSONArray array = (JSONArray) parseItems.get("item");

            // 데이터 삽입
            for(int i = 0; i<array.size(); i++) {
                JSONObject jsonObj = (JSONObject) array.get(i);

                // 데이터 addrDTO에 추가
                AddrDTO addrDTO = new AddrDTO();                            // 이 부분 노가다
                addrDTO.setAddrId("1"+ "-" + (String) jsonObj.get("code")); // areaCode 바뀔때마다 변경, // ID는 areaCode-sigunguCode로 통일
                addrDTO.setAreaCode(1);                                     // areaCode 바뀔때마다 변경
                addrDTO.setAreaName("서울");                                 // areaCode 바뀔때마다 변경
                addrDTO.setSigunguCode(Integer.parseInt((String) jsonObj.get("code")));
                addrDTO.setSigunguName((String) jsonObj.get("name"));
                tourService.addrsave(addrDTO);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }


    // 투어 스팟 저장 하는 get
    // !!!!!!!!!!!!!!!!!!!아직 수정 중!!!!!!!!!!!!!!!!
    @GetMapping("/tourspot/api")
    public String callTourspotApi() throws IOException, ParseException {
        StringBuilder result = new StringBuilder();

        String urlStr = "http://apis.data.go.kr/B551011/GreenTourService1"+
                "/areaBasedList1" +
                "?serviceKey=tK7Va58kue58fAehjhvQSgM9B1YDPZVt2UtOPh%2Fk1Os2UWleCMLcW5EZog%2FtRyj0E3ToAVSKf%2F2d9ogWT%2BTRfw%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=1" +
                "&MobileOS=ETC" +
                "&MobileApp=AppTest" +
                "&areaCode=39" +    // 이 부분 수정해서 데이터 넣기
                "&_type=json" +
                "&arrange=A";
        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONObject parseResponse = (JSONObject) jsonObject.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONObject parseItems = (JSONObject) parseBody.get("items");
            JSONArray array = (JSONArray) parseItems.get("item");

            // 드디어 데이터 삽입 !!!
            for(int i = 0; i<array.size(); i++) {
                JSONObject jsonObj = (JSONObject) array.get(i);

                // 데이터 dto에 추가
                TourspotDTO tourspotDTO = new TourspotDTO();
                tourspotDTO.setTourspot_id(Integer.parseInt((String)jsonObj.get("contentid")));
                tourspotDTO.setAreaCode(Integer.parseInt((String) jsonObj.get("areacode")));
                tourspotDTO.setSigunguCode(Integer.parseInt((String) jsonObj.get("sigungucode")));
                tourspotDTO.setAddr((String) jsonObj.get("addr"));
                tourspotDTO.setMainimage((String) jsonObj.get("mainimage"));
                tourspotDTO.setSummary((String) jsonObj.get("summary"));
                tourspotDTO.setTel((String) jsonObj.get("tel"));
                tourspotDTO.setTitle((String) jsonObj.get("title"));

                Map<String, BigDecimal> locationMap = tourService.getGeoDataByAddress((String) jsonObj.get("addr"));

                tourspotDTO.setLatitude(locationMap.get("latitude"));
                tourspotDTO.setLongitude(locationMap.get("longitude"));

                tourService.spotSave(tourspotDTO);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

}
