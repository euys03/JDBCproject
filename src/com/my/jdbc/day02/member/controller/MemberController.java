package com.my.jdbc.day02.member.controller;

import java.util.List;

import com.my.jdbc.day02.member.model.dao.MemberDAO;
import com.my.jdbc.day02.member.model.vo.Member;

public class MemberController {
	
	/** [1] 전체 회원 조회 (selectAll 생성자 넘겨받기)
	 * **/
	// printAll 생성자 생성
	public List<Member> printAll() {	// SELECT * FROM MEMBER_TBL
		MemberDAO mDao = new MemberDAO();
		// MemberDAO에서 mList를 반환하는 것으로 변경하였기 때문에
		// List<Member> 자료형을 사용하는 selectAll 생성자를, controller에서 사용하고 있으므로
		// 'controller의 selectAll도' 자료형을 변경해주어야 한다. (Run에도 동일하게 변경)
		//Member member = mDao.selectAll(); -> List<Member> mList
		//return member;	-> mList
		List<Member> mList = mDao.selectAll();
		return mList;
	}
	
	
	/** [2] 아이디로 회원 조회 
	 * **/
	public Member printOneById(String memberId) {	// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?
		// ResultSet이 1개면 Member
		// ResultSetdl 1개 이상이면 List<Member>
		// MEMBER_ID는 중복이 불가하여 ResultSet이 1개일 수밖에 없다
		MemberDAO mDao = new MemberDAO();
		Member member = mDao.selectOneById(memberId);
		return member;
	}
	
	
	/** [3] 이름으로 회원 조회
	 * @param
	 * **/
	public List<Member> printMemberByName(String memberName) {	// SELECT * FROM MEMBER_TBL WHERE MEMBER_NAME = ?
		// ResultSet이 1개면 Member
		// ResultSetdl 1개 이상이면 List<Member>
		MemberDAO mDao = new MemberDAO();
		List<Member> mList = mDao.selectMemberByName(memberName);
		return mList;
	}
	
	
	/** [4] 회원가입 (insertMember 생성자 넘겨받기)
	 * @param member
	 * @return result
	 ***/
	// Statement ver.사용
	public int registerMember(Member member) {	// INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)
		// 인스턴스(new MemberDAO) 생성
		MemberDAO mDao = new MemberDAO();
		// MemberDAO에서 insertMember 호출
		int result = mDao.insertMember(member);
		return result;	// => result(0(실패), 정수(성공))을 Run 으로 반환.
	}
	
	
	/** [5] 회원정보 수정
		 * @param member
		 * @return
		 * */
		public int modifyMemberInfo(Member member) {
			MemberDAO mDao = new MemberDAO();
			int result = mDao.updateMember(member);
			return result;	// return 0;으로 두지 않아요! 꼭 바꿔주기
	//		return 0;
		}

		
	/** [6] 회원탈퇴(삭제)
	 * @param memberId
	 * */
	// PreparedStatemet 연습
	public int removeMember(String memberId) {	// DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.deleteMember(memberId);
		return result;
	}

	
	/** [7] 회원 로그인
	 * @param member
	 * @return
	 * */
	public int checkInfo(Member member) {	// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?
		MemberDAO mDao = new MemberDAO();
		int result = mDao.checkLogin(member);
		return result;
	}
	
}
