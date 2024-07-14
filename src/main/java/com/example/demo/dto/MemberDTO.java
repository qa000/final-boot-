package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberDTO {
    private String id;              // 아이디
    private String password;        // 비밀번호
    private String name;            // 이름
    private String birth;           // 생년월일
    private String email;           // 이메일
    private String phone;           // 핸드폰
    private String address1;        // 주소
    private String address2;        // 주소
    private String address3;        // 주소
    private MultipartFile profile;  // 프로필


}
