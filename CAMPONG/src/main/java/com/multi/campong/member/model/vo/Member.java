package com.multi.campong.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int uNo;
	private String id;
	private String password;
	private String role;
	private int birthYear;
	private String nickName;
	private String phone;
	private String email1;
	private String email2;
	private String address;
	private String kakaoToken;
	private String status;
	private Date enrollDate;
	private Date modifyDate;
}
