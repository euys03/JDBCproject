package com.my.jdbc.day00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	public static void main(String [] args) {
		//System.out.println("Hello JDBC!!");
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성	(연결된 상태)
		 * 3. Statement 객체 생성	(쿼리문 실행준비)
		 * 4. SQL 전송			(쿼리문 실행된 상태)
		 * 5. 결과값 받기	(ResultSet을 받은 상태) -> 후처리
		 * 6. 자원해제		(close())
		 * 
		 * */
		
		try {
			// Referenced Libraries에 있는 ojdbc6.jar 파일 사용하기 위해 쓴 경로
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "KH";	// KH라는 계정
			String password = "KH";	// 비번: KH -> conn에 들어가 있는 상태
			String sql = "SELECT * FROM EMPLOYEE";
			
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결 생성 -> 인터페이스(connection) 생성 및
			// 클래스 바로 적고 메소드 호출 가능한 이유? DriverManager에 있는 getConection메소드가 Static메소드였기 때문에.
			Connection conn = DriverManager.getConnection(url, user, password);
			
			// 3. 쿼리문 실행준비(Statement 객체 생성)
			Statement stmt = conn.createStatement();
			
			// 4. 쿼리문 실행 (RESULTSET을 실행, 쿼리문 실행하기 위해 executeQuery메소드)
			ResultSet rset = stmt.executeQuery(sql);
			
			// 후처리 - DB에서 가져온 데이터를 사용
			// (while문으로 변경해보기)
			while(rset.next()) {
				System.out.println("사원 아이디 : " + rset.getNString("EMP_ID"));
			/*if(rset.next()) {
				System.out.println("사원 아이디 : " + rset.getString("EMP_ID"));
				*/
			}
			
			// 자원해제
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
