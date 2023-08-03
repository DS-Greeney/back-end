package com.greeneyback.member.controller;

import com.greeneyback.member.dto.TitleDTO;
import com.greeneyback.member.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;
    List<String> titleName = new ArrayList<String>();
    StringBuilder result = new StringBuilder();

    @GetMapping("/title/save")
    public void titleSave() {
        titleName.add("에코그린세포");
        titleName.add("환경 운동가 지망생");
        titleName.add("환경 보호 새내기");
        titleName.add("기특한 친환경 애호가");
        titleName.add("으쌰으쌰 친환경 행동가");
        titleName.add("친절한 환경운동가");
        titleName.add("발빠른 지구지킴이");
        titleName.add("똑똑한 친환경 전문가");
        titleName.add("열렬한 환경 보호 투쟁가");
        titleName.add("존경받는 환경 보호 영웅");
        titleName.add("최고의 환경부장관");
        titleName.add("빛나는 에코신");

        for (int i=0; i<titleName.size(); i++) {
            TitleDTO titleDTO = new TitleDTO();
            titleDTO.setTitleId(i+1);
            titleDTO.setTitleName(titleName.get(i));

            titleService.titleSave(titleDTO);
            result.append(i+","+titleName.get(i)+"\n");
        }

//        return result.toString();
    }
}
