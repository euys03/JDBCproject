package com.my.jdbc.day02.member.model.vo;

import java.sql.Timestamp;

public class Member {
	// 변수 선언 -> 카멜 표기법으로 표현(_ 앞은 모두 소문자, _ 뒤 한 글자만 대문자)
	private String memberId;	// MEMBER_ID(컬럼명) -> memberId(변수명)
	private String memberPwd;
	private String memberName;
	private String memberGender;
	private int memberAge;
	private String memberEmail;
	private String memberPhone;
	private String memberAddress;
	private String memberHobby;
	// Timestamp를 사용하면 '시, 분, 초'도 표현할 수 있다.
	private Timestamp memberDate;
	
	// 생성자 1)기본생성자
	public Member() {
		super();
	}
	
	// 생성자 2)매개변수(memberId, memberPwd, ..)가 있는 생성자
	public Member(String memberId, String memberPwd, String memberName, String memberGender, int memberAge,
			String memberEmail, String memberPhone, String memberAddress, String memberHobby, Timestamp memberDate) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.memberAge = memberAge;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
		this.memberHobby = memberHobby;
		this.memberDate = memberDate;
	}

	// getter/setter 메소드
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberHobby() {
		return memberHobby;
	}

	public void setMemberHobby(String memberHobby) {
		this.memberHobby = memberHobby;
	}

	public Timestamp getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Timestamp memberDate) {
		this.memberDate = memberDate;
	}

	// toString
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberGender=" + memberGender + ", memberAge=" + memberAge + ", memberEmail=" + memberEmail + ", memberPhone="
				+ memberPhone + ", memberAddress=" + memberAddress + ", memberHobby=" + memberHobby + ", memberDate="
				+ memberDate + "]";
	}


	
}
