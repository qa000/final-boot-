package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@ModelAttribute MemberDTO memberDTO, @RequestParam("profile") MultipartFile profile) {
        try {
            memberDTO.setProfile(profile);
            memberService.saveMember(memberDTO);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("회원가입 실패: " + e.getMessage());
        }
    }

    // 이메일 발송 및 확인
    @PostMapping("/emailCheck")
    public ResponseEntity<String> emailCheck(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String uuid = memberService.emailCheck(email);
            return ResponseEntity.ok(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("이메일 인증 실패: " + e.getMessage());
        }
    }

    // 아이디 중복체크
    @PostMapping("/idCheck")
    public ResponseEntity<Boolean> idCheck(@RequestParam("id") String id) {
        try {
            boolean exists = memberService.idCheck(id);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(false);
        }
    }
}
