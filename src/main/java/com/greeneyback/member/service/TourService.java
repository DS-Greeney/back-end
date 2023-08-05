package com.greeneyback.member.service;

import com.greeneyback.member.dto.AddrDTO;
import com.greeneyback.member.dto.TourspotDTO;
import com.greeneyback.member.entity.AddrEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.AddrRepository;
import com.greeneyback.member.repository.TourspotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TourService {

    private final TourspotRepository tourspotRepository;
    private final AddrRepository addrRepository;

    public void spotSave(TourspotDTO tourspotDTO) {
        TourspotEntity tourspotEntity = TourspotEntity.toTourspotEntity(tourspotDTO);
        tourspotRepository.save(tourspotEntity);
    }

    public void addrsave(AddrDTO addrDTO) {
        AddrEntity addrEntity = AddrEntity.toAddrEntity(addrDTO);
        addrRepository.save(addrEntity);
    }

    public Map<String, String> getGeoDataByAddress(String addr) {

        try {
            String apiKey = "AIzaSyC8IBQFK9cWfU2uAJFznyitrP2mKp5024U";
            String surl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(addr,"UTF-8")+"&key=" + apiKey;
            URL url = new URL(surl);
            InputStream inputStream = url.openConnection().getInputStream();

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;

            while((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            Map<String, String> location = new HashMap<String, String>();


            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseStrBuilder.toString());
            JSONArray parseResults = (JSONArray) jsonObject.get("results");

            JSONObject parseResult = (JSONObject) parseResults.get(0);
            JSONObject parseGeometry = (JSONObject) parseResult.get("geometry");
            JSONObject parseLocation = (JSONObject) parseGeometry.get("location");
            Double parseLatitude = (Double) parseLocation.get("lat");
            Double parseLongitude = (Double) parseLocation.get("lng");

            log.info("여기까지 성공" + parseLatitude.toString());
            log.info("여기까지 성공" + parseLongitude.toString());

            String la = parseLatitude.toString();
            String lo = parseLongitude.toString();

            location.put("latitude", la);
            location.put("longitude", lo);

            return location;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TourspotEntity> findAllTourspotEntities() {
        List<TourspotEntity> tourspots = tourspotRepository.findAll();
        return tourspots;
    }
}

