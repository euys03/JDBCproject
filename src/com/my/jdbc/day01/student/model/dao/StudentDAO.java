package com.my.jdbc.day01.student.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.jdbc.day01.student.model.vo.Student;

public class StudentDAO {
	// 접속정보
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "STUDENT"; // (DB에서 설정한 STUDENT계정 아이디와 비밀번호 입력)
	private final String PASSWORD = "sTUDENT"; // ID와 비밀번호가 소스코드에 있다는 뜻(취약).
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

// 실행은 StudentRun에서 한다.
	
	// [1. 학생 전체 목록 조회]
	public List<Student> selectAll() {
		String sql = "SELECT * FROM STUDENT_TBL"; // 모든 데이터 갖고올 수 있도록 SQL문 작성
		Student student = null;
		List<Student> sList = null;

		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);

			// 2. DB 연결 생성 -> Statement를 생성할 수 있다.
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			// 3. 쿼리문 실행준비 (Statement 생성)
			Statement stmt = conn.createStatement();

			// 4. 쿼리문 실행 및 결과 받기(ResulrtSet사용) - SELECT
			ResultSet rset = stmt.executeQuery(sql);
			sList = new ArrayList<Student>();

			// 5. 후처리 - rset은 그냥 못쓰기 때문에 후처리 해야한다.
			// rset을 꺼내 쓰기 위해서는 next()를 사용해야 한다.
			// 반복문이므로 while문 사용
			// rset호출 + 컬럼명(STUDENT_ID, AGE, GENDER, ... : DB에서 가져온 값들)
			while (rset.next()) {
				// Controller로 보낸다.
				student = new Student(); // student 객체 생성
				student.setStudentId(rset.getString("STUDENT_ID")); // 필드에 값 담기(setStudentId)
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				// (시험문제)
				// 컬럼명을 적어서 필드값을 갖고올 때 int를 가져오고 싶은 경우 getString이 아닌 getInt를 사용하면 된다.
				student.setGender(rset.getString("GENDER"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				/** (시험문제) 상차ㅋ sList.add 꼭 쓰기 **/
				// 컬럼명을 적어서 필드값을 갖고올 때 DATE를 가져오고 싶은 경우 getString이 아닌 getDate를 사용하면 된다.
				sList.add(student);

				/*	// System.out.println 하여 값을 받아 출력하게 되면 나중에 getString한 값을 사용하기 위해
				 * 	// return 또한 그만큼 해줘야하지만 return은 한번만 가능하므로 Student객체를 생성하여 호출하는
				 * 	// 형식으로 출력한다.
				 * System.out.println("ID : " + rset.getString("STUDENT_ID")); // 필드값!
				 * System.out.println("STUDENT_NAME : " + rset.getString("STUDENT_NAME")); //
				 * 필드값! System.out.println("STUDENT_PWD : " + rset.getString("STUDENT_PWD")); //
				 * 필드값! System.out.println("AGE : " + rset.getString("AGE")); // 필드값!
				 * System.out.println("GENDER : " + rset.getString("GENDER")); // 필드값!
				 * System.out.println("ADDRESS : " + rset.getString("ADDRESS")); // 필드값!
				 */
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

		// RETURN은 딱 한번만 사용할 수 있으므로 아래 주석과 같은 코드는 불가능
		// 해결방법) .vo(STUDENT라는 객체에 담아서)에 6개를 담아 한 번에 RETRURN하면 가능
		/*
		 * return rset.getString("STUDENT_ID"); return rset.getString("STUDENT_ID");
		 * return rset.getString("STUDENT_ID"); return rset.getString("STUDENT_ID");
		 * return rset.getString("STUDENT_ID"); return rset.getString("STUDENT_ID");
		 */
		return sList; // student객체를 리턴 -> void -> Student
	}
	

	// [2. 학생 아이디로 조회]
	public Student selectOneById(String studentId) {
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = '" + studentId + "'";
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DB 연결 객체 생성
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. Statement 생성, 쿼리문 실행 준비 완료
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 실행, 5. 결과 받기
			ResultSet rset = stmt.executeQuery(sql);
			// 6. 후처리, rset을 그대로 못쓰니까
			if (rset.next()) {
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setGender(rset.getString("GENDER"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			// 7. 자원해제
			rset.close();
			stmt.close();
			conn.close();
//			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	
	// [3. 학생 이름으로 조회]
	public List<Student> selectAllByName(String studentName) {
		List<Student> sList = null;
		Student student = null;
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '" + studentName + "'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			// ArrayList인 Studentlist의 경우 절대 null이 들어가선 안되고 항상 객체가 생성되어 있는 상태
			sList = new ArrayList<Student>();

			while (rset.next()) {
				// setter
				student = new Student();
				student.setStudentId(rset.getString("STUDENT_ID"));
				student.setStudentName(rset.getString("STUDENT_NAME"));
				student.setStudentPwd(rset.getString("STUDENT_PWD"));
				student.setAge(rset.getInt("AGE"));
				student.setEmail(rset.getString("EMAIL"));
				student.setPhone(rset.getString("PHONE"));
				student.setGender(rset.getString("GENDER"));
				student.setAddress(rset.getString("ADDRESS"));
				student.setHobby(rset.getString("HOBBY"));
				student.setEnrollDate(rset.getDate("ENROLL_DATE"));
				// 택배상자
				sList.add(student);

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}

	
	// [4. 회원가입]
	public int insertMember(Student student) {
		/*
		 * Run으로 아래 생성된 객체들을 넘겨준다. insertMember에는 (student)로 입력받아 사용 // 데이터는 이미 준비되어 있다고
		 * 가정. Student student = new Student(); student.setStudentId("khuser02");
		 * student.setStudentName("이용자"); student.setStudentPwd("pass02");
		 * student.setAge(22); student.setGender("M"); // 데이터 준비 완료
		 */

		// 원래 sql문
		// String sql = "INSERT INTO STUDENT_TBL
		// VALUES('khuser03','pass03','삼용자','M',33,'khuser03@naver.com','01088320393','서울시
		// 동대문구','자전거,축구',SYSDATE)";

		// DML(INSERT, UPDATE, DELETE)는 모두 이와 같은 방식으로 해야 한다.
		// getter메소드 사용하여 student에 있는 값을 가져다가 인서트할 수 있다.
		String sql = "INSERT INTO STUDENT_TBL VALUES(" + "'" + student.getStudentId() + "'," + "'"
				+ student.getStudentPwd() + "'," + "'" + student.getStudentName() + "'," + "'" + student.getGender()
				+ "'," + student.getAge() + "," + "'" + student.getEmail() + "'," + "'" + student.getPhone() + "',"
				+ "'" + student.getAddress() + "'," + "'" + student.getHobby() + "'," + "SYSDATE)";

		int result = 0;

		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			// 쿼리문 실행 - DML(INSERT, UPDATE, DELETE)
			result = stmt.executeUpdate(sql);
			// DML을 실행할 때는 executeUpdate를 써줘야 한다.
			// (시험문제) result에는 성공하면 1, 실패하면 0 -> int로 받는다
			// executeQuery vs executeUpdate 사용하는 경우 차이
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	// [5. 정보 수정]
	public int updateStudent(Student student) {
		int result = 0;
		String sql = "UPDATE STUDENT_TBL SET "
				+ "STUDENT_PWD = '"+ student.getStudentPwd() +"', "
				+ "EMAIL = '"+ student.getEmail() +"', "
				+ "PHONE = '"+ student.getPhone() +"', "
				+ "ADDRESS = '"+ student.getAddress() +"', "
				+ "HOBBY = '"+ student.getHobby() +"'"
				+ "WHERE STUDENT_ID = '"+ student.getStudentId() +"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	// [6. 회원탈퇴]
	public int deleteMemer(String studentId) {
		int result = 0;
		String sql = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = '"+ studentId +"'";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			// 쿼리문 실행 - DML(INSERT, UPDATE, DELETE) -> executeUpdate -> int반환
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		// Void methods cannot return a value
	}
	
}
