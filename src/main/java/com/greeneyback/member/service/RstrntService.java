package com.greeneyback.member.service;

import com.greeneyback.member.dto.CommentDTO;
import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.SpotCommentEntity;
import com.greeneyback.member.entity.SpotCommentImageEntity;
import com.greeneyback.member.repository.MemberRepository;
import com.greeneyback.member.repository.RstrntRepository;
import com.greeneyback.member.repository.SpotCmntImgRepository;
import com.greeneyback.member.repository.SpotCmntRepository;
import com.greeneyback.member.repository.impl.RstrntRepositoryImpl;
import com.greeneyback.member.repository.impl.SpotCmntRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.greeneyback.member.entity.QRstrntEntity.rstrntEntity;

@Service
@RequiredArgsConstructor
public class RstrntService {
      /*
    @Autowired
    RstrntRepository rstrntRepository;


    public void save(MultipartFile file) {
        try {
            List<RstrntEntity> rstrntEntities = CSVHelper.csvToRstrntEntities(file.getInputStream());
            rstrntRepository.saveAll(rstrntEntities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: "+e.getMessage());
        }
    }

    public List<RstrntEntity> getAllRstrntEntities() {
        return rstrntRepository.findAll();
    }

     */

    private final JPAQueryFactory queryFactory;

    private final RstrntRepository rstrntRepository;

    @Autowired
    private final RstrntRepositoryImpl rstrntRepositoryImpl;

    private final MemberRepository memberRepository;
    private final SpotCmntRepository spotCmntRepository;
    private final SpotCmntImgRepository spotCmntImgRepository;
    private final SpotCmntRepositoryImpl spotCmntRepositoryImpl;

    public List<RstrntEntity> findAllRstrntEntities() {
        return rstrntRepository.findAll();
    }

    public Optional<RstrntEntity> findById(int id) {
        return rstrntRepository.findById(id);
    }

    public Optional<RstrntEntity> findRstrntDetail(int rstrntId) {
        Optional<RstrntEntity> restaurant = rstrntRepository.findById(rstrntId);
        return restaurant;
    }

    public void restaurantSave(RstrntDTO rstrntDTO) {
        RstrntEntity rstrntEntity = RstrntEntity.toRstrntEntity(rstrntDTO);
        rstrntRepository.save(rstrntEntity);
    }

    public List<RstrntEntity> findByMyLocation(HashMap<String, Double> myLocation) {
        return rstrntRepositoryImpl.findByLocation(myLocation);
    }

    public List<RstrntEntity> findByMyLocationAreaFilter(HashMap<String, Double> myLocation, int areaCode) {
        return rstrntRepositoryImpl.findByLocationAreaCode(myLocation, areaCode);
    }

    // 별점순 정렬
    public List<RstrntEntity> findAllOrderByStar() {
        return queryFactory.selectFrom(rstrntEntity)
                .orderBy(rstrntEntity.rstrntStar.desc())
                .fetch();
    }


    // 리뷰를 db에 저장하는 메소드, 저장한 Entity를 반환한다.
    public SpotCommentEntity saveRstrntReviewComment(CommentDTO commentDTO) {
        SpotCommentEntity rstrntCommentEntity = new SpotCommentEntity();

        // userId 찾기
        MemberEntity memberEntity = memberRepository.findByUserId(commentDTO.getUserId());

        // entity 설정
        rstrntCommentEntity.setSpotId(commentDTO.getSpotId());
        rstrntCommentEntity.setUser(memberEntity);
        rstrntCommentEntity.setCategoryNumber(commentDTO.getCategoryNumber());
        rstrntCommentEntity.setSpotCmntContent(commentDTO.getCmntContent());
        rstrntCommentEntity.setSpotCmntTime(LocalDate.now());
        rstrntCommentEntity.setSpotCmntStar(commentDTO.getCmntStar());

        spotCmntRepository.save(rstrntCommentEntity);

        return rstrntCommentEntity;
    }

    public void calculateAvgStar(CommentDTO commentDTO) {
        int spotId = commentDTO.getSpotId();

        List<SpotCommentEntity> comments = spotCmntRepository.findBySpotId(spotId);

        float totalStars = (float) comments.stream().mapToDouble(SpotCommentEntity::getSpotCmntStar).sum();
        float avgStar = totalStars/comments.size();

        queryFactory.update(rstrntEntity)
                .set(rstrntEntity.rstrntStar, avgStar)
                .where(rstrntEntity.rstrntId.eq(spotId))
                .execute();
    }

    // 이미지 url을 저장하는 메소드
    public void saveRstrntReviewImage(SpotCommentEntity rstrntCommentEntity, List<String> imgUrlList) {

        // forEach문을 통해서 db에 이미지 entity 추가
        for(String imgUrl : imgUrlList) {
            // 이미지 entity 선언
            SpotCommentImageEntity rstrntImageEntity = new SpotCommentImageEntity();
            // entity 설정
            rstrntImageEntity.setSpotCmnt(rstrntCommentEntity);
            rstrntImageEntity.setSpotImgUrl(imgUrl);

            spotCmntImgRepository.save(rstrntImageEntity);
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
            review.put("userPicture", memberEntity.getUserPicture());
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


    public List<RstrntEntity> findBySearchAndMyLocation(List<String> stringList, HashMap<String, Double> myLocation) {
        return rstrntRepositoryImpl.findBySearchAndMyLocation(stringList, myLocation);
    }
}
