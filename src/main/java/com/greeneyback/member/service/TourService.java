package com.greeneyback.member.service;

import com.greeneyback.member.dto.AddrDTO;
import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.dto.TourspotDTO;
import com.greeneyback.member.entity.*;
import com.greeneyback.member.repository.*;
import com.greeneyback.member.repository.impl.SpotCmntRepositoryImpl;
import com.greeneyback.member.repository.impl.TourspotRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.*;

import static com.greeneyback.member.entity.QTourspotEntity.tourspotEntity;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class TourService {

    private final JPAQueryFactory queryFactory;

    private final TourspotRepository tourspotRepository;
    private final AddrRepository addrRepository;
    private final MemberRepository memberRepository;
    private final SpotCmntRepository spotCmntRepository;
    private final SpotCmntImgRepository spotCmntImgRepository;

    private final SpotCmntRepositoryImpl spotCmntRepositoryImpl;

    @Autowired
    private final TourspotRepositoryImpl tourspotRepositoryImpl;

    public void spotSave(TourspotDTO tourspotDTO) {
        TourspotEntity tourspotEntity = TourspotEntity.toTourspotEntity(tourspotDTO);
        tourspotRepository.save(tourspotEntity);
    }

    public void addrsave(AddrDTO addrDTO) {
        AddrEntity addrEntity = AddrEntity.toAddrEntity(addrDTO);
        addrRepository.save(addrEntity);
    }

    public Map<String, BigDecimal> getGeoDataByAddress(String addr) {

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

            Map<String, BigDecimal> location = new HashMap<String, BigDecimal>();


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

            BigDecimal la = BigDecimal.valueOf(parseLatitude);
            BigDecimal lo = BigDecimal.valueOf(parseLongitude);

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

    public Optional<TourspotEntity> findById(int id) {
        return tourspotRepository.findById(id);
    }

    public Optional<TourspotEntity> findTourspotDetail(int tourspotId) {
        Optional<TourspotEntity> tourspot = tourspotRepository.findById(tourspotId);
        return tourspot;
    }

    public List<TourspotEntity> findByMyLocation(HashMap<String, Double> myLocation) {
        return tourspotRepositoryImpl.findByLocation(myLocation);
    }

    public List<TourspotEntity> findByMyLocationAreaFilter(HashMap<String, Double> myLocation, int areaCode) {
        return tourspotRepositoryImpl.findByLocationAreaCode(myLocation, areaCode);
    }

    // 별점순 정렬
    public List<TourspotEntity> findAllOrderByStar() {
        return queryFactory.selectFrom(tourspotEntity)
                .orderBy(tourspotEntity.tourspotStar.desc())
                .fetch();
    }

    // 리뷰를 db에 저장하는 메소드, 저장한 Entity를 반환한다.
    public SpotCommentEntity saveTourReviewComment(CommentDTO commentDTO) {
        SpotCommentEntity tourspotCommentEntity = new SpotCommentEntity();

        // userId 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(commentDTO.getUserId());

        // entity 설정
        tourspotCommentEntity.setSpotId(commentDTO.getSpotId());
        tourspotCommentEntity.setUser(memberEntity);
        tourspotCommentEntity.setCategoryNumber(commentDTO.getCategoryNumber());
        tourspotCommentEntity.setSpotCmntContent(commentDTO.getCmntContent());
        tourspotCommentEntity.setSpotCmntTime(LocalDate.now());
        tourspotCommentEntity.setSpotCmntStar(commentDTO.getCmntStar());

        spotCmntRepository.save(tourspotCommentEntity);

        return tourspotCommentEntity;
    }

    // 평균 별점을 계산하고 저장하는 메소드
    public void calculateAvgStar(CommentDTO commentDTO) {
        int spotId = commentDTO.getSpotId();

        List<SpotCommentEntity> comments = spotCmntRepository.findBySpotId(spotId);

        float totalStars = (float) comments.stream().mapToDouble(SpotCommentEntity::getSpotCmntStar).sum();
        float avgStar = totalStars/comments.size();

        queryFactory.update(tourspotEntity)
                .set(tourspotEntity.tourspotStar, avgStar)
                .where(tourspotEntity.tourspotId.eq(spotId))
                .execute();
    }

    // 이미지 url을 저장하는 메소드
    public void saveTourReviewImage(SpotCommentEntity tourspotCommentEntity, List<String> imgUrlList) {

        // forEach문을 통해서 db에 이미지 entity 추가
        for(String imgUrl : imgUrlList) {
            // 이미지 entity 선언
            SpotCommentImageEntity tourspotImageEntity = new SpotCommentImageEntity();
            // entity 설정
            tourspotImageEntity.setSpotCmnt(tourspotCommentEntity);
            tourspotImageEntity.setSpotImgUrl(imgUrl);

            spotCmntImgRepository.save(tourspotImageEntity);
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
            review.put("tourspotCmntContent", spotCommentEntity.getSpotCmntContent());
            review.put("tourspotCmntTime", spotCommentEntity.getSpotCmntTime());
            review.put("tourspotCmntStar", spotCommentEntity.getSpotCmntStar());
            review.put("categoryNumber", spotCommentEntity.getCategoryNumber());
            review.put("tourCmntImg", imgUrlList);

            // 최종적으로 reviewList에 넣어준다.
            reviewList.add(review);

        }

        return reviewList;
    }


    public List<TourspotEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation) {
        return tourspotRepositoryImpl.findBySearchAndMyLocation(stringList, myLocation);
    }
}

