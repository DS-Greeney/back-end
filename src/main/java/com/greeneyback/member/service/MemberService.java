package com.greeneyback.member.service;

import com.greeneyback.member.dto.MemberDTO;
import com.greeneyback.member.entity.MemberEntity;
import com.greeneyback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final EncryptService encryptService;

    public void registerUser(MemberDTO memberDTO) {
        // 이메일 양방향 암호화
        String encodedEmail = encryptService.encryptEmail(memberDTO.getUserEmail());
        memberDTO.setUserEmail(encodedEmail);

        // 비밀번호 단방향 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getUserPassword());
        memberDTO.setUserPassword(encodedPassword);

        String nickname = memberDTO.getUserNickname();
        memberDTO.setUserNickname(nickname);

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public Optional<MemberEntity> userLogin(MemberDTO memberDTO) throws Exception {
        String nickname = memberDTO.getUserNickname();

        Optional<MemberEntity> user = memberRepository.findByUserNickname(nickname);

        if (user.isPresent()) { // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다.)
            // passwordEncoder를 이용한 암호 비교 로직
            if (passwordEncoder.matches(memberDTO.getUserPassword(), user.get().getUserPassword()) == true) {
                return user;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출

        if (memberDTO.getUserNickname() != null && memberDTO.getUserEmail() != null && memberDTO.getUserPassword() != null
//                && memberDTO.getUserBirthdate() != null && memberDTO.getUserPhonenum() != null && memberDTO.getUserBirthdate() != null
//                && memberDTO.getUserGender() != null
            ) {
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

    public Optional<MemberEntity> findUserById(Long id) {
        return memberRepository.findById(id);
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

    public void updateUserProfileImage(Long userId, String imageURl) {
        MemberEntity memberEntity = memberRepository.findByUserId(userId);
        memberEntity.setUserPicture(imageURl);
        memberRepository.save(memberEntity);
    }

}
