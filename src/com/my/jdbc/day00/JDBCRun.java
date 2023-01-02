package com.my.jdbc.day00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	public static void main(String [] args) {
//		System.out.println("Hello JDBC!!");
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성	(연결된 상태)
		 * 3. Statement 객체 생성	(쿼리문 실행준비)
		 * 4. SQL 전송			(쿼리문 실행된 상태)
		 * 5. 결과값 받기	(ResultSet을 받은 상태) -> 후처리 필요
		 * 6. 자원해제		(close();)
		 * 
		 * */
		
		try {
			// Referenced Libraries에 있는 ojdbc6.jar파일(실제 드라이버) 사용하기 위해 작성한 경로
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "KH";	// KH라는 계정(사용하고 있는 DB계정 작성)
			String password = "KH";	// 비밀번호 KH -> conn에 들어가 있는 상태
			String sql = "SELECT * FROM EMPLOYEE";
			
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결 생성 -> 인터페이스(Connection) 생성 및 생성된 DB 저장
			// Q. 클래스 내에서 객체 생성을 하지 않고도 메소드 호출 가능한 이유? 
			// A. DriverManager에 있는 getConection메소드가 Static메소드였기 때문이다
			Connection conn = DriverManager.getConnection(url, user, password);
			
			// 3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리문 실행 (쿼리문 실행 후 ResultSet 인터페이스인 rset에 DB저장
			// 				  (*쿼리문 실행하기 위해 'executeQuery메소드' 사용*)
			ResultSet rset = stmt.executeQuery(sql);
			
			// 5. 후처리 - DB에서 가져온 데이터를 사용
			// (while문으로 변경해보기 > DB안에 있는 23개의 데이터 출력)
			while(rset.next()) {
				System.out.println("사원 아이디 : " + rset.getNString("EMP_ID"));
			/*if(rset.next()) {
				System.out.println("사원 아이디 : " + rset.getString("EMP_ID"));
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
	}
}
