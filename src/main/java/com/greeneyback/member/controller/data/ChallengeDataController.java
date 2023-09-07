package com.greeneyback.member.controller.data;

import com.greeneyback.member.dto.ChallengeDTO;
import com.greeneyback.member.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChallengeDataController {

    private final ChallengeService challengeService;
    List<String> challenge = new ArrayList<>();
    StringBuilder result = new StringBuilder();

    @GetMapping("/challenge/save")
    public String challengeSave() {
        challenge.add("가까운 거리 자전거나 도보로 이동하기");
        challenge.add("에어컨 없이 하루 보내기");
        challenge.add("양치할 때 양치컵 사용하기");
        challenge.add("배달음식 시키지 않기");
        challenge.add("장볼 때 일회용 플라스틱 봉지 대신 쇼핑백 지참하기");
        challenge.add("불필요한 전기 장치 끄기");
        challenge.add("채식 위주의 식사하기");
        challenge.add("샤워 시간 단축하기");
        challenge.add("화분이나 정원 가꾸기");
        challenge.add("로컬푸드 매장 방문해서 식재료 구입하기");
        challenge.add("냉수 및 온수로 샤워하기");
        challenge.add("5분 내로 샤워하기");
        challenge.add("스마트폰 밝기를 낮추고 다크모드로 설정하기");
        challenge.add("스팸메일과 이메일, 광고메일 등 정리하기");
        challenge.add("종이 영수증 대신에 모바일 영수증으로 받기");
        challenge.add("낮에 전등대신 자연광 이용하기");
        challenge.add("에어컨의 적정온도를 26도로 설정하기");
        challenge.add("자가용 대신 대중교통 이용하기");
        challenge.add("고체샴푸 사용하기");
        challenge.add("페트병 라벨 제거해서 분리수거하기");
        challenge.add("라면 용기 씻어서 햇빛에 말린 후 분리배출하기");
        challenge.add("음식 잔반 남기지 않기");
        challenge.add("물티슈 대신 걸레, 행주 사용하기");
        challenge.add("해가 진 저녁에 10분간 전등 소등하기");
        challenge.add("선풍기, 에어컨 대신 부채 사용하기");
        challenge.add("난방 대신 옷 껴입기");
        challenge.add("사용하지 않는 멀티탭 전원끄기");
        challenge.add("양치할 때 양치컵 사용하기");
        challenge.add("택배 박스 테이프 제거해서 분리수거하기");
        challenge.add("텀블러 사용해서 음료 테이크아웃하기");
        challenge.add("다회용기에 음식 포장해먹기");
        challenge.add("일회용품을 줄이기 위해 배달시키지 않고 집에서 음식해먹기");
        challenge.add("장바구니 사용하기");
        challenge.add("천연 수세미 사용하여 설거지하기");
        challenge.add("천연 비누 사용하기");
        challenge.add("종이 영수증 대신 스마트 영수증 받기");
        challenge.add("중고 의류 구매하기");
        challenge.add("제로웨이스트 가게 방문하기");

        for (int i=0; i<challenge.size(); i++) {
            ChallengeDTO challengeDTO = new ChallengeDTO();
            challengeDTO.setChallengeId(i+1);
            challengeDTO.setChallengeContent(challenge.get(i));
            challengeService.challengeSave(challengeDTO);

            result.append(challenge.get(i)+"\n");
        }
        return result.toString();
    }
}
