package com.greeneyback.member.service;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출

        if (memberDTO.getUserNickname() != null && memberDTO.getUserEmail() != null && memberDTO.getUserPassword() != null
                && memberDTO.getUserBirthdate() != null && memberDTO.getUserPhonenum() != null && memberDTO.getUserBirthdate() != null
                && memberDTO.getUserGender() != null) {
            MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
            memberRepository.save(memberEntity);
            // repository의 save 메서드 호출 (조건. entity 객체를 넘겨줘야 함)
        }

    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 한다.
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        */
        Optional<MemberEntity> byUserEmail = memberRepository.findByUserEmail(memberDTO.getUserEmail()); // 이메일을 호출하여 dto에 담긴 email 값을 넘겨준다.
        if (byUserEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            MemberEntity memberEntity = byUserEmail.get();  // Optional 객체안의 enetity 객체를 가져온다. (optional은 포장지)
            if(memberEntity.getUserPassword().equals(memberDTO.getUserPassword())) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴 (control에서 dto를 사용하기 때문에)
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치(로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다.(해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            return memberDTO;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }
    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByUserEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

}
