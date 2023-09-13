package com.greeneyback.member.service;

import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.entity.SearchEntity;
import com.greeneyback.member.repository.MemberRepository;
import com.greeneyback.member.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final MemberRepository memberRepository;

    // String을 space 기준으로 잘라 리스트로 반환하는 메소드
    public List<String> stringToList(String strings) {
        List<String> stringList = new ArrayList<>();

        if (strings.contains(" ")) {
            String[] stringArray = strings.split(" ");
            for(String string : stringArray) {
                stringList.add(string);
            }

        } else {
            stringList.add(strings);
        }

        return stringList;
    }

    public void saveSearch(List<String> stringList, Long userId, int category) {

        // userId로 user검색
        MemberEntity memberEntity = memberRepository.findByUserId(userId);

        // stringList를 StringBuffer에 넣고 DB에 저장
        StringBuffer stringBuffer = new StringBuffer();

        for(String keyword : stringList) {
            stringBuffer.append(keyword);
            stringBuffer.append(",");  // ,로 구분할 수 있도록
        }

        // searchEntity 구성
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setSearchContent(stringBuffer.toString());
        searchEntity.setCategory(category);
        searchEntity.setUser(memberEntity);
        searchEntity.setSearchTime(LocalDate.now());

        searchRepository.save(searchEntity);

    }
}
