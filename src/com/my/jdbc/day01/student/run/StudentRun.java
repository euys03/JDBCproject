package com.my.jdbc.day01.student.run;

import java.util.List;

import com.my.jdbc.day01.student.controller.StudentController;
import com.my.jdbc.day01.student.model.vo.Student;
import com.my.jdbc.day01.student.view.StudentView;

public class StudentRun {
	public static void main(String [] args) {
		/*
		StudentController sCon = new StudentController();
		StudentView sView = new StudentView();
//		List<Student> sList = sCon.printAll();
		//System.out.println(student.toString());
//		sView.showAll(sList);	// 실행
		
		
		// 데이터는 이미 준비되었음
		//Student student = new Student();
		Student student = sView.inputStudent();
		// 데이터 준비 완료
		int result = sCon.registerMember(student);
		if(result > 0) {
			System.out.println("SUCCESS !!");
		} else {
			System.out.println("FAILED !!");
		}
		*/
		StudentView sView = new StudentView();
		StudentController sCon = new StudentController();
		Student student = null;
		List<Student> sList = null;
		String studentId = "";
		String studentName = "";
		int result = 0;
		
		done :
		while(true) {
			int choice = sView.mainMenu();
			switch(choice) {
				case 1 : 
					// 전체 조회
					sList = sCon.printAll();
					// 데이터가 있는지 없는지 출력하는메소드 (isEmpty())
					// sList 항상 Null이 아니다 StudentDao:70
					if(!sList.isEmpty()) {
						sView.showAll(sList);
					} else {
						sView.displayError("일치하는 데이터가 없습니다.");
					}					
					break;
				case 2 : 
					// 아이디로 조회
					studentId = sView.inputStudentId("검색");
					student = sCon.printOneById(studentId);
					// student(데이터)가 있거나 없을 때
					if(student != null) {
						sView.showOne(student);
					} else {
						sView.displayError("학생 데이터가 없습니다.");
					}
					break;
				case 3 : 
					// 이름으로 조회
					studentName = sView.inputStudentName("검색");
					sList = sCon.printAllByName(studentName);
					/** sList 항상 Null이 아니다 (StudentDAO:150열을 보면 알 수 있다.)
					 * 항상 NULL이 아니므로 데이터가 있는지 없는지 출력하는메소드 (부정연산자(!)+isEmpty())
					 * - 비어있으면 true, 비어있지 않으면 false
					 * ArrayList인 StudentList는 항상 객체가 생성되어있는 상태이므로
					 * 안에 데이터가 있는지 없는지 검사를 해야하므로 (student != null) 표현보다
					 * 부정연산자(!) + isEmpty() 함수로 출력한다.**/
					if(!sList.isEmpty()) {	// ture
						sView.showAll(sList);
					} else {		// false
						sView.displayError("일치하는 데이터가 없습니다.");
					}
					break;
				case 4 : 
					// 회원 가입
					student = sView.inputStudent();
					result = sCon.registerStudent(student);
					if(result > 0) {
						// 성공메시지!
						sView.displaySuccess("가입이 완료되었습니다.");
					}else {
						// 실패메시지!
						sView.displaySuccess("가입에 실패하였습니다.");
					}
					break;
				case 5 : 
					// 회원 정보수정
					studentId = sView.inputStudentId("수정");
					// ID(stuentId)를 입력받아서 Student 객체에 저장.
					student = sCon.printOneById(studentId);
					// 입력받은 ID와 동일한 데이터가 있는지 확인
					if(student != null) {
						// 기존의 정보를 수정하기 위해 student에 저장된 데이터들을 불러온다
						student = sView.modifyStudent(student);
//						student.setStudentId(studentId);
						sCon.modifyStudent(student);
						if(result > 0) {
							sView.displaySuccess("수정 성공!");
						} else {
							sView.displayError("수정이 되지 않았습니다.");
						}
					} else {
						sView.displayError("일치하는 학생이 없습니다.");
					}
					break;
				case 6 :
					// 회원 탈퇴
					studentId = sView.inputStudentId("삭제");
					result = sCon.removeStudent(studentId);
					// result : 성공하면 정수, 실패하면 0 의 데이터를 받는다.
					if(result > 0) {
						// 성공메시지
						sView.displaySuccess("탈퇴완료");
					} else {
						// 실패메시지
						sView.displayError("탈퇴되지 않았습니다.");
					}
					break;
				case 0 :
					// 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					break done;
				default : 
					sView.printMessage("잘못 입력하였습니다 1 ~ 6 사이의 수를 입력해주세요");
					// '잘못 입력하였습니다 1 ~ 6 사이의 수를 입력해주세요' 출력
					break;
			}
		}
		
	}
	
	
}
