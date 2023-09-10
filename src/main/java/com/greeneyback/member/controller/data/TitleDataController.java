package com.greeneyback.member.controller.data;

import com.greeneyback.member.dto.TitleDTO;
import com.greeneyback.member.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TitleDataController {
    private final TitleService titleService;
    List<String> titleName = new ArrayList<String>();
    List<Integer> goalChallengeNm = new ArrayList<>();
    List<Integer> nextChallengeNm = new ArrayList<>();
    StringBuilder result = new StringBuilder();

    @GetMapping("/title/save")
    public String titleSave() {
        titleName.add("에코그린세포");
        goalChallengeNm.add(0);
        nextChallengeNm.add(3);
        titleName.add("환경 운동가 지망생");
        goalChallengeNm.add(3);
        nextChallengeNm.add(7);
        titleName.add("환경 보호 새내기");
        goalChallengeNm.add(10);
        nextChallengeNm.add(10);
        titleName.add("기특한 친환경 애호가");
        goalChallengeNm.add(20);
        nextChallengeNm.add(10);
        titleName.add("으쌰으쌰 친환경 행동가");
        goalChallengeNm.add(30);
        nextChallengeNm.add(12);
        titleName.add("친절한 환경운동가");
        goalChallengeNm.add(42);
        nextChallengeNm.add(12);
        titleName.add("발빠른 지구지킴이");
        goalChallengeNm.add(54);
        nextChallengeNm.add(15);
        titleName.add("똑똑한 친환경 전문가");
        goalChallengeNm.add(69);
        nextChallengeNm.add(15);
        titleName.add("열렬한 환경 보호 투쟁가");
        goalChallengeNm.add(84);
        nextChallengeNm.add(17);
        titleName.add("존경받는 환경 보호 영웅");
        goalChallengeNm.add(101);
        nextChallengeNm.add(20);
        titleName.add("최고의 환경부장관");
        goalChallengeNm.add(121);
        nextChallengeNm.add(30);
        titleName.add("빛나는 에코신");
        goalChallengeNm.add(151);
        nextChallengeNm.add(0);

        for (int i=0; i<titleName.size(); i++) {
            TitleDTO titleDTO = new TitleDTO();
            titleDTO.setTitleId(i+1);
            titleDTO.setTitleName(titleName.get(i));
            titleDTO.setGoalChallengeNm(goalChallengeNm.get(i));
            titleDTO.setNextChallengeNm(nextChallengeNm.get(i));

            titleService.titleSave(titleDTO);
            result.append(i+","+titleName.get(i)+","+goalChallengeNm.get(i)+nextChallengeNm.get(i)+"\n");
        }

        return result.toString();
    }
}
