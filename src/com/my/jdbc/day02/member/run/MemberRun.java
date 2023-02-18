package com.my.jdbc.day02.member.run;

import java.util.List;

import com.my.jdbc.day02.member.controller.MemberController;
import com.my.jdbc.day02.member.model.vo.Member;
import com.my.jdbc.day02.member.view.MemberView;

public class MemberRun {

	public static void main(String[] args) {
		MemberView mView = new MemberView();
		MemberController mCon = new MemberController();
		Member member = null;
		int result = 0;
		String memberId = "";
		String memberName = "";
		// Controller 에서 result값을 반환하여 result를 사용할 예정이므로 주석처리
//		List<Member> mList = mCon.printAll();
		// 출력 시 'toString 형식'으로 사용하여
		//Member [memberId=admin, memberPwd=admin, memberName=관리자, ...] 와 같이 출력한다.
		// 그러므로 출력문은 VIEW에서 출력하도록 VIEW로 옮겨주고 생성자(showAll)를 하나 생성하여
		// 해당 생성자를 MemberView에서 불러오는 형식으로 출력한다. 
		// -> MemberView를 mView로 칭하기로 Run(위)에서 선언하였다.
		//System.out.println(member.toString()); -> view로 이동
//		mView.showAll(mList);
		
//		// 데이터는 이미 준비되었음
//		Member member = new Member();
//		// insertMember() 안에 member를 불러오는 형식으로 사용했기 때문에 선언이 필요없어졌다.
//		member.setMemberId("khuser02");
//		member.setMemberName("이용자");
//		member.setMemberId("pass02");
//		member.setMemberAge(22);
//		member.setMemberGender("M");
//		// 데이터 준비완료
//	위와 같이 직접 모든 데이터를 하나하나 입력하는 방법이 아닌 
//	member에 VIEW에 만든 inputMember() 생성자를 호출하여 값을 입력받음
//		Member member = mView.inputMember();
		
		
		// Controller 에서 반환한 result값 받음
//		int result = mCon.registerMember(member);
//		if (result > 0) {
//			//System.out.println("SUCCESS !!");
//			mView.displaySuccess("SUCCESS !!");
//		} else {
//			//System.out.println("FAILED !");
//			mView.displaySuccess("FAILED !!");
//		}
		
		
		
		goodbye :
		while(true) {
			int choice = mView.mainMenu();
			switch(choice) {
			case 0 : 
				System.out.println("프로그램이 종료되었습니다.");
				break goodbye;
			case 1 : 
				// 전체 조회
				List<Member> mList = mCon.printAll();
				mView.showAll(mList);
				break;
			case 2 :
				// 아이디로 회원조회
				memberId = mView.inputMemberId("검색");
				member = mCon.printOneById(memberId);
				if(member != null) {
					mView.showOne(member);
				} else {
					mView.displayError("ID가 일치하는 회원이 존재하지 않습니다.");
				}
				break;
			case 3 : 
				// 이름으로 회원조회
				memberName = mView.inputMemberName("검색");
				mList = mCon.printMemberByName(memberName);
				if(mList.size() > 0) {
					mView.showAll(mList);
				} else {
					mView.displayError("이름이 일치하는 회원이 존재하지 않습니다.");
				}
				break;
			case 4 :
				// 회원 가입
				member = mView.inputMember();
				result = mCon.registerMember(member);
				if (result > 0) {
					// 성공!
					mView.displaySuccess("가입이 완료되었습니다!");
				} else {
					// 실패!
					mView.displayError("가입에 실패하였습니다.");
				}
				break;
			case 5 : 
				// 아이디를 입력받고
				memberId = mView.inputMemberId("수정");
				// 데이터가 존재하면
				member = mCon.printOneById(memberId);	// (1)member데이터 받기
				System.out.println(member);
				if(member != null) {					// (2)데이터여부 판단
					// 수정할 데이터 입력받기
					member = mView.modifyMember(memberId);	// 입력받은 값을 member에 저장하고
					// 입력받은 데이터로 수정하기!
					result = mCon.modifyMemberInfo(member);	// 저장된 member를 바로 불러와 result에 저장
					if(result > 0) {
						mView.displaySuccess("수정 성공!");
					} else {
						mView.displayError("수정이 되지 않았습니다.");
					}
				} else {
					mView.displayError("입력하신 아이디가 존재하지 않습니다.");
				}
				break;
			case 6 : 
				// 회원 삭제 (view의 memberId 넘겨받기)
				memberId = mView.inputMemberId("삭제");
				result = mCon.removeMember(memberId);
				if(result > 0) {
					mView.displaySuccess("회원 탈퇴 성공!");
				} else {
					mView.displayError("회원탈퇴가 완료되지 않았습니다.");
				}
				break;
			case 7 : 
				// 로그인
				member = mView.inputLoginInfo();
				result = mCon.checkInfo(member);
				if(result > 0) {
					// 로그인 성공
					mView.displaySuccess("로그인 성공!");
				} else {
					// 로그인 실패
					mView.displayError("일치하는 정보가 존재하지 않습니다.");
				}
				break;
			default : 
				mView.printMessage("잘못입력하셨습니다 1 ~ 7 사이의 수를 입력해주세요");
				break;
			}
		}
	}

}
