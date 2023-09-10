package com.greeneyback.member.service;


import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.entity.*;
import com.greeneyback.member.repository.HotelRepository;
import com.greeneyback.member.repository.MemberRepository;
import com.greeneyback.member.repository.SpotCmntImgRepository;
import com.greeneyback.member.repository.SpotCmntRepository;
import com.greeneyback.member.repository.impl.HotelRepositoryImpl;
import com.greeneyback.member.repository.impl.SpotCmntRepositoryImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final MemberRepository memberRepository;
    private final HotelRepository hotelRepository;
    private final HotelRepositoryImpl hotelRepositoryImpl;
    private final SpotCmntRepository spotCmntRepository;
    private final SpotCmntImgRepository spotCmntImgRepository;
    private final SpotCmntRepositoryImpl spotCmntRepositoryImpl;

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

    public List<HotelEntity> findAllhotelEntities() {
        return hotelRepository.findAll();
    }

    public Optional<HotelEntity> findById(int id) {
        return hotelRepository.findById(id);
    }

    public Optional<HotelEntity> findHotelDetail(int hotelId) {
        Optional<HotelEntity> hotel = hotelRepository.findById(hotelId);
        return hotel;
    }

    public List<HotelEntity> findByMyLocation(HashMap<String, Double> myLocation) {
        return hotelRepositoryImpl.findByLocation(myLocation);
    }

    public List<HotelEntity> findByMyLocationAreaFilter(HashMap<String, Double> myLocation, int areaCode) {
        return hotelRepositoryImpl.findByLocationAreaCode(myLocation, areaCode);
    }

    // 리뷰를 db에 저장하는 메소드, 저장한 Entity를 반환한다.
    public SpotCommentEntity saveHotelReviewComment(CommentDTO commentDTO) {
        SpotCommentEntity hotelCommentEntity = new SpotCommentEntity();

        // userId 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(commentDTO.getUserId());

        // entity 설정
        hotelCommentEntity.setSpotId(commentDTO.getSpotId());
        hotelCommentEntity.setUser(memberEntity);
        hotelCommentEntity.setCategoryNumber(commentDTO.getCategoryNumber());
        hotelCommentEntity.setSpotCmntContent(commentDTO.getCmntContent());
        hotelCommentEntity.setSpotCmntTime(LocalDate.now());
        hotelCommentEntity.setSpotCmntStar(commentDTO.getCmntStar());

        spotCmntRepository.save(hotelCommentEntity);

        return hotelCommentEntity;
    }

    // 이미지 url을 저장하는 메소드
    public void saveHotelReviewImage(SpotCommentEntity hotelCommentEntity, List<String> imgUrlList) {

        // forEach문을 통해서 db에 이미지 entity 추가
        for(String imgUrl : imgUrlList) {
            // 이미지 entity 선언
            SpotCommentImageEntity hotelImageEntity = new SpotCommentImageEntity();
            // entity 설정
            hotelImageEntity.setSpotCmnt(hotelCommentEntity);
            hotelImageEntity.setSpotImgUrl(imgUrl);

            spotCmntImgRepository.save(hotelImageEntity);
        }

    }

    // 리뷰 리스트를 불러오는 메소드
    public List<Object> getReviewList(int spotId, int categoryNumber) {
        // review들을 모은 List
        List<Object> reviewList = new ArrayList<>();

        // spotId과 categoryNumber를 이용해서 spotCommentEntity에서 컬럼들을 찾는다.
        List<SpotCommentEntity> spotCommentEntityList = spotCmntRepositoryImpl.findBySpotIdAndCategoryNumber(spotId, categoryNumber);

        // 찾은 컬럼들을 하나씩 재구성해 reviewList에 넣는다.
        for(SpotCommentEntity spotCommentEntity : spotCommentEntityList) {

            HashMap<String, Object> review = new HashMap<>();   // 하나의 review를 구성하는 hashmap
            List<String> imgUrlList = new ArrayList<>();        // review에 담기는 imageUrl의 리스트

            // userId를 통해 userNickname을 찾는다.
            MemberEntity memberEntity = memberRepository.findByUserId(spotCommentEntity.getUser().getUserId());
            String userNickname = memberEntity.getUserNickname();

            // spotCmntId를 통해 spotCmntImgRepository에서 컬럼들을 찾는다.
            List<SpotCommentImageEntity> spotImageEntityList = spotCmntImgRepository.findBySpotCmnt(spotCommentEntity);

            for(SpotCommentImageEntity spotCommentImageEntity : spotImageEntityList) {
                imgUrlList.add(spotCommentImageEntity.getSpotImgUrl());  // imgUrlList에 추가
            }

            // 하나의 리뷰를 구성해준다.
            review.put("userNickname", userNickname);
            review.put("hotelCmntContent", spotCommentEntity.getSpotCmntContent());
            review.put("hotelCmntTime", spotCommentEntity.getSpotCmntTime());
            review.put("hotelCmntStar", spotCommentEntity.getSpotCmntStar());
            review.put("categoryNumber", spotCommentEntity.getCategoryNumber());
            review.put("hotelCmntImg", imgUrlList);

            // 최종적으로 reviewList에 넣어준다.
            reviewList.add(review);

        }

        return reviewList;
    }


}
