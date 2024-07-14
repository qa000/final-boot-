package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final String uploadDir = "C:/Users/user/Desktop/ReactWorkspace/fianl-project/uploads/";

    // 회원 저장
    public void saveMember(MemberDTO memberDTO) throws IOException {
        MemberEntity member = new MemberEntity();
        member.setId(memberDTO.getId());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(encodedPassword);

        member.setName(memberDTO.getName());
        member.setBirth(memberDTO.getBirth());
        member.setEmail(memberDTO.getEmail());
        member.setPhone(memberDTO.getPhone());
        member.setAddress(memberDTO.getAddress1() + " " + memberDTO.getAddress2() + " " + memberDTO.getAddress3());

        if (memberDTO.getProfile() != null && !memberDTO.getProfile().isEmpty()) {
            String profileName = saveProfileImage(memberDTO.getProfile());
            member.setProfileName(profileName);
        }

        memberRepository.save(member);
    }

    // 이미지 저장
    private String saveProfileImage(MultipartFile profile) throws IOException {
        String profileName = System.currentTimeMillis() + "_" + profile.getOriginalFilename();
        Path path = Paths.get(uploadDir + profileName);

        // 디렉토리가 존재하지 않을 경우 생성
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        profile.transferTo(path.toFile());
        return profileName;
    }

    // 이메일 인증
    public String emailCheck(String email) {
        System.out.println("Received email address: " + email); // 이메일 주소 로그 출력
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("이메일 주소가 유효하지 않습니다.");
        }

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        // 이메일 발송
        MimeMessage mail = mailSender.createMimeMessage();

        String message = "<h2>안녕하세요. 고양이의 발자국입니다.</h2>"
                + "<p>먼저 고양이의 발자국을 선택해주셔서 대단히 감사드립니다.</p>"
                + "<p>회원님의 선택에 후회가 없도록, 최선을 다 하겠습니다.</p>"
                + "<p>인증번호는 <b>" + uuid + "</b> 입니다.</p>"
                + "<p>인증번호 입력창에 입력해주세요</p>";

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(email);
            helper.setSubject("고양이의 발자국 인증번호");
            helper.setText(message, true);

            mailSender.send(mail);
        } catch (Exception e) {
            throw new RuntimeException("이메일 전송 중 오류 발생", e);
        }

        return uuid;
    }

    // 아이디 체크
    public boolean idCheck(String id) {
        return memberRepository.existsById(id);
    }


}