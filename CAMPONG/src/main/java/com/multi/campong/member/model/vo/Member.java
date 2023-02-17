package com.multi.campong.member.model.vo;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int mNo;
	private String id;
	private String password;
	private String birthYear;
	private String role;
	private String nickName;
	private String phone;
	private String email1;
	private String email2;
	private String address;
	private String kakaoToken;
	private String status;
	private Date enrollDate;
	private Date modifyDate;
    private MultipartFile attachFile;          // 첨부 파일
    private List<MultipartFile> imageFiles;    // 첨부 이미지
	
}
