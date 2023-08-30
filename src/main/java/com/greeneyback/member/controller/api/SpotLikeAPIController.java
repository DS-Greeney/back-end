package com.greeneyback.member.controller.api;

import com.greeneyback.member.dto.SpotLikeDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.entity.SpotLikeEntity;
import com.greeneyback.member.entity.TourspotEntity;
import com.greeneyback.member.repository.MemberRepository;
import com.greeneyback.member.repository.RstrntRepository;
import com.greeneyback.member.repository.SpotLikeRepository;
import com.greeneyback.member.repository.TourspotRepository;
import com.greeneyback.member.service.SpotLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/greeney/mypage")
public class SpotLikeAPIController {

    @Autowired
    private final SpotLikeService spotLikeService;
    private final SpotLikeRepository spotLikeRepository;
    private final MemberRepository memberRepository;
    private final TourspotRepository tourspotRepository;
    private final RstrntRepository rstrntRepository;

    @PostMapping("/like")
    public HashMap<String, Object> spotLikeToggle(@RequestParam String userId, @RequestParam String itemId, @RequestParam String like) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            if (like.equals("1")) { // 찜 추가
                SpotLikeDTO spotLikeDTO = new SpotLikeDTO();

                Optional<MemberEntity> likeUser = memberRepository.findById(Long.valueOf(userId));
                spotLikeDTO.setUser(likeUser.get());

                Optional<TourspotEntity> likeTourspot = tourspotRepository.findById(Integer.valueOf(itemId));
                Optional<RstrntEntity> likeRstrnt = rstrntRepository.findById(itemId);
                // 숙소
                // 여행코스

                if (likeTourspot.isPresent()) {
                    spotLikeDTO.setCategoryNumber(1);
                    spotLikeDTO.setTourspot(likeTourspot.get());

                    map.put("likeTourspotId", likeTourspot.get().getTourspot_id());
                }
                else if (likeRstrnt.isPresent()) {
                    spotLikeDTO.setCategoryNumber(2);
                    spotLikeDTO.setRstrnt(likeRstrnt.get());

                    map.put("likeRstrntId", likeRstrnt.get().getRstrntId());
                }
                // 숙소
                // 여행코스

                spotLikeService.saveSpotLike(spotLikeDTO);

                map.put("success", Boolean.TRUE);
            }
            else if (like.equals("0")) { // 찜 삭제
                Optional<MemberEntity> user = memberRepository.findById(Long.valueOf(userId));
                List<SpotLikeEntity> spotLikes = spotLikeRepository.findByUser(user.get()); // 유저가 찜한 목록

//                for (SpotLikeEntity spotLike : spotLikes) {
//
//                    if (spotLike.getTourspot().getTourspot_id() == Integer.parseInt(itemId)) { // 유저가 찜한 목록의 tourspotId와 받은 itemId가 같으면
//                        spotLikeService.deleteSpotLikeById(spotLike.getSpotLikeId()); // 유저가 찜한 목록의 해당 id의 데이터 삭제
//
//                        map.put("dislikeSpotId", spotLike.getSpotLikeId());
//                    }
//
//                }

                map.put("sucess", Boolean.TRUE);

            }

            return map;

        } catch (Exception e) {
            map.put("success", Boolean.FALSE);
            map.put("message", e.getMessage());
            return map;
        }

    }

}
