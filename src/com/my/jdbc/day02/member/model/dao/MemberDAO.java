package com.my.jdbc.day02.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.jdbc.day02.member.model.vo.Member;

// JDBC 다루기
public class MemberDAO {
	// 전역변수로 사용
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "STUDENT";
	private final String PASSWORD = "sTUDENT";
	// 실행할 쿼리문 입력 => "DDL언어"
	private final String SQL = "INSERT INTO MEMBER_TBL VALUES('khuser02', 'pass02', '이용자', '여', 22, 'khuser02@kh.com', '01022222222', '서울시', '독서, 농구', SYSDATE)";
	// 드라이브 등록 시 사용하는 드라이브 이름도 전역변수로 빼내어 편리하게 사용
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	
	// [1] 회원 전체 조회
	public List<Member> selectAll() {
/* 전역변수로 선언해놓았으므로 필요X
		// ORACLE 경로 입력 (고정)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// 다루려는 테이블이 존재하는 DB 계정 연결 (STUDENT계정)
		String user = "STUDENT";
		String password = "sTUDENT";
*/
		// 실행할 쿼리문(ResultSet rset) 입력 => "select문"
		String sql = "SELECT * FROM MEMBER_TBL";

		// 출력문을 return 받을 Member객체 생성
		Member member = null;
		
		// 조회된 정보를 list로 받기 위해 선언
		List<Member> mList = null;
		
		try {
			// 1. 드라이브 등록
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 생성
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. 쿼리문 실행준비(Statement 생성) => "select문"
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 실행(executeQuery메소드 사용) 및 결과 받기(ResultSet타입 사용)
			//		(SELECT문이므로 executeQuery 메소드 사용, ResultSet타입으로 반환)
			ResultSet rset = stmt.executeQuery(sql);
			/** (중요) select 쿼리문
			 * executeQuery(sql) 사용, ResultSet 타입으로 반환 **/
			
			// 조회된 정보를 배열형태의 list로 받기
			mList = new ArrayList<Member>();
			
			// 5. 후처리 (rset은 그냥 사용할 수 없으므로 후처리를 해줘야한다.)
			// .next()메소드를 사용하여 rset을 하나씩 꺼내 사용할 수 있다.
			// 여러 값들(컬럼들)을 하나씩 반복하여 호출하기 위해 while문 사용
			while(rset.next()) {
				// 출력문을 return 받을 Member객체 선언
				member = new Member();
				// member객체에 값 넣기 (setter(불러오기)/getter(넣기-컬럼명)메소드 사용)
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				
				// 객체(member)에 들어온 데이터들을 리스트(mList)에 추가
				mList.add(member);
			}
			// 6. 자원해제
			rset.close();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// mList로 member객체에 들어온 데이터들을 모두 넣었기 때문에
		// member를 반환할 필요없이 mList를 반환하면 모든 데이터를 볼 수 있다.
		//return member;	// void : member -> List<Member>
		return mList;
	}
	
	
	// [2] 아이디로 조회
	public Member selectOneById(String memberId) {
		Member member = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);// 쿼리문 실행 준비
			ResultSet rset = pstmt.executeQuery(); // 쿼리문 실행
			if(rset.next()) {
				// 후처리(rset은 그대로 못쓰기 때문에 member에서 꺼내서 각 메소드에 맵핑을 해준 것)
				member = new Member();	// null 상태로 두면 안되므로 member객체 생성
				member.setMemberId(rset.getString(1));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
//				rset.getChar() (X)
			}
			rset.close();
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	
	
	// [3] 이름으로 회원조회
	// PreparedStatement 사용
	public List<Member> selectMemberByName(String memberName) {
		List<Member> mList = null;
		String sql = "SELECT * FROM MEMBER_TBL WHERE MEMBER_NAME LIKE ?";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// String 'sql' 에 위치홀더(물음표)가 하나 존재하므로 쿼리문 실행준비를 해준다
			pstmt.setString(1, "%"+memberName+"%");		// 쿼리문 실행 준비
			ResultSet rset = pstmt.executeQuery();
			mList = new ArrayList<Member>();
			while(rset.next()) {
				Member member = new Member();
				// 후처리(rset은 그대로 못쓰기 때문에 member에서 꺼내서 각 메소드에 맵핑을 해준 것)
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberAge(rset.getInt("MEMBER_AGE"));
				member.setMemberGender(rset.getString("MEMBER_GENDER"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberAddress(rset.getString("MEMBER_ADDRESS"));
				member.setMemberHobby(rset.getString("MEMBER_HOBBY"));
				member.setMemberDate(rset.getTimestamp("MEMBER_DATE"));
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mList;
	}
	
	
	// [7] 로그인하기
	public int checkLogin(Member member) {
		String query = "SELECT COUNT(*) AS M_COUNT FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());	// 쿼리문 실행 준비
			ResultSet rset = pstmt.executeQuery();		// 쿼리문 실행
			if(rset.next()) {
				result = rset.getInt("M_COUNT");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	// [4] 회원가입
	public int insertMember(Member member) {
		// 데이터는 이미 준비되었음
		//Member member = new Member();
		// insertMember() 안에 member를 불러오는 형식으로 사용했기 때문에 선언이 필요없어졌다.
		//member.setMemberId("khuser02");
		//member.setMemberName("이용자");
		//member.setMemberId("pass02");
		//member.setMemberAge(22);
		//member.setMemberGender("M");
		// 데이터 준비완료
		
		// 전역변수로 사용하기 위해 (url, user, password)를 가장 위에 선언하였으며, getConnection(값)의 변수도 '대문자'로 변경
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String user = "STUDENT";
//		String password = "sTUDENT";
		
		// 실행할 쿼리문 입력 => "DDL언어(INSERT, UPDATE, DELETE)"
		// (1) 원래 sql문
		//String sql = "INSERT INTO MEMBER_TBL VALUES('khuser02', 'pass02', '이용자', '여', 22, 'khuser02@kh.com', '01022222222', '서울시', '독서, 농구', SYSDATE)";
		// (2) Statement 방법 사용(member에 있는 변수들을 불러와 사용)
//		String sql = "INSERT INTO MEMBER_TBL VALUES(" 
//				+ "'" + member.getMemberId() + "'," + "'"
//				+ member.getMemberPwd() + "'," + "'" + member.getMemberName() + "'," + "'" + member.getMemberGender()
//				+ "'," + member.getMemberAge() + "," + "'" + member.getMemberEmail() + "'," + "'" + member.getMemberPhone() + "',"
//				+ "'" + member.getMemberAddress() + "'," + "'" + member.getMemberHobby() + "'," + "SYSDATE)";
		// (3) PreparedStatement 방법 사용 (위치홀더(물음표)를 사용하여 sql문 작성)
		String sql = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		
		
		// result를 전역변수로 선언 (result값을 return하기 위해 try안에 지역변수 result를 전역변수로.)
		int result = 0;
		try {
			// 1. 드라이버 등록 (Class.forName)
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 생성 (Connection)
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. 쿼리문 실행 준비 (Statement 생성)
				// 1) *Statement 사용 ver.
//			Statement stmt = conn.createStatement();
			/** 시험문제
			 * PreparedStatement는 pstmt.setString 시 작성하는 순서에는 의미가 없다.
			 * 컬럼의 개수는 맞추되, 꼭 1,2,3.. 순서로 작성할 필요x, 순서가 뒤바뀌어도 가능하다
			 * 컬럼의 개수만큼 위치홀더(?)를 적어주고, 컬럼명과 인덱스(번호)만 매치되면 된다*/
				// 2) *PreparedStatement 사용 ver. (Statement준비하겠다는 의미로 prepare 사용 (prepare'd'(x)))
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(9, member.getMemberHobby());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(6, member.getMemberEmail());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberAddress());	// 쿼리문 실행준비!
			
			// 4. 쿼리문 실행(executeUpdate메소드 사용) 및 결과 받기(int 타입 사용)
			//	(DML(INSERT, UPDATE, DELETE)이므로 executeUpdate 메소드 사용, int타입으로 반환)
			// result를 전역변수로 위에 선언
			//result = stmt.executeUpdate(sql);
			/** (중요) DML(INSERT, UPDATE, DELETE) 쿼리문
			 * excuteUpdate(sql) 사용, int 타입으로 반환 **/
			
			
			/**(시험문제) preparedStatement는 (sql)문을 실행하지 않는다.
			 * Statement와 PreparedStatement의 차이점**/
			result = pstmt.executeUpdate();		// 쿼리문 실행, sql 필요없음!
			
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 5. 후처리 따로 할 필요없이 result값을 return 해주면 된다.
		//	(select문에서 사용하는 rset의 경우에는 그냥 사용할 수 없으므로 후처리가 필수)
		return result;
		// 쿼리문 성공시 정수 출력(1, 2, 5, ..), 실패시 0 출력 (INSERT의 경우 0이 나오지 않지만, UPDATE,DELETE의 경우 나올 수 있다)
		// ex) UPDATE 쿼리문, DELETE 쿼리문 실행 시 - '0개 행 이(가) 업데이트/삭제되었습니다.' 의 '0'이라는 숫자가 result로 들어가는 것.
	}
	
	
	
	// [5] 회원 정보 수정
	public int updateMember(Member member) {
		int result = 0;
		String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_ADDRESS = ?, MEMBER_HOBBY = ? WHERE MEMBER_ID =?";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberAddress());
			pstmt.setString(5, member.getMemberHobby());
			pstmt.setString(6, member.getMemberId());	// 쿼리문 실행 준비 완료
			result = pstmt.executeUpdate();
			// 후처리
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	// [6] 회원탈퇴(삭제)	
	// PreparedStatement 연습
	public int deleteMember(String memberId) {
		String sql = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		int result = 0;
		
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 생성 (Connection)
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. 쿼리문 실행 준비 (Statement 생성 -> PreparedStatement를 사용해서)
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// PreparedStatement 에 넣을 값 입력
			pstmt.setString(1, memberId);	// 쿼리문 실행 준비
			result = pstmt.executeUpdate();	// 쿼리문 실행
			// 4. 후처리
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
