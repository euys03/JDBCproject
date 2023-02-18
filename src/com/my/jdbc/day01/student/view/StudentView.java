package com.my.jdbc.day01.student.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.my.jdbc.day01.student.model.vo.Student;

public class StudentView {
	
	// [초기화면]
	public int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("==========================");
		System.out.println("1. 학생 전체 목록 조회");
		System.out.println("2. 학생 아이디로 조회");
		System.out.println("3. 학생 이름으로 조회");
		System.out.println("4. 회원가입");
		System.out.println("5. 학생 정보 수정");
		System.out.println("6. 회원탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int select = sc.nextInt();
		System.out.println("==========================");
		return select;
	}
	
	// [1] 전체 목록 조회
	public void showAll(List<Student> students) {
		System.out.println("======== 학생 전체 목록 조회 =======");
		for (Student sOne : students) {
			System.out.print("아이디 : " + sOne.getStudentId());
			System.out.print(", 비밀번호 : " + sOne.getStudentPwd());
			System.out.print(", 이름 : " + sOne.getStudentName());
			System.out.print(", 성별 : " + sOne.getGender());
			System.out.print(", 나이 : " + sOne.getAge());
			System.out.print(", 이메일 : " + sOne.getEmail());
			System.out.print(", 전화번호 : " + sOne.getPhone());
			System.out.print(", 주소 : " + sOne.getAddress());
			System.out.print(", 취미 : " + sOne.getHobby());
			System.out.println(", 가입날짜 : " + sOne.getEnrollDate());
		}
	}

	public void showOne(Student student) {
		System.out.print("아이디 : " + student.getStudentId());
		System.out.print(", 비밀번호 : " + student.getStudentPwd());
		System.out.print(", 이름 : " + student.getStudentName());
		System.out.print(", 성별 : " + student.getGender());
		System.out.print(", 나이 : " + student.getAge());
		System.out.print(", 이메일 : " + student.getEmail());
		System.out.print(", 전화번호 : " + student.getPhone());
		System.out.print(", 주소 : " + student.getAddress());
		System.out.print(", 취미 : " + student.getHobby());
		System.out.println(", 가입날짜 : " + student.getEnrollDate());
		// System.out.println(student.toString());
	}

	// [2] 아이디로 조회
	public String inputStudentId(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 아이디 입력 : ");
		String studentId = sc.next();
		return studentId;
	}

	// [3] 이름으로 조회
	public String inputStudentName(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.print(message + "할 이름 입력 : ");
		String studentName = sc.next();
		return studentName;
	}

	// [4] 회원가입
	public Student inputStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String studentId = sc.next();
		System.out.print("이름 : ");
		String studentName = sc.next();
		System.out.print("비밀번호 : ");
		String studentPwd = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		// nextLine() 사용 시 주의!
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미(,로 구분) : ");
		String hobby = sc.next();

		// 방법2) 위의 많은 객체들을 하나의 참조변수로 사용할 수 있도록 객체를 하나 만든다.
//		Student student = new Student();
//		student.setStudentId(studentId);
//		student.setStudentName(studentName);
//		student.setStudentPwd(studentPwd);
//		student.setGender(gender);
//		student.setAge(age);
//		student.setEmail(email);
//		student.setPhone(phone);
//		student.setAddress(address);
//		student.setHobby(hobby);

		// 방법3) 매개변수있는 생성자를 만든다.
		Student student = new Student(studentId, studentName, studentPwd, gender, age, email, phone, address, hobby,
				null);
		return student;
	}

	
	// [5] 회원 정보 수정
	// ModifyStudent에 (Student student)으로 값을 받아 '비번, 이메일, 전번, 주소, 취미'가 아닌 
	// 모든 데이터들도 들어올 수 있도록 값을 받는다.
	// "수정할" 데이터들만 입력받을 것이다.
	public Student modifyStudent(Student student) {
		Scanner sc = new Scanner(System.in);
//		// 입력받은 여러 개의 데이터를 한번에 다루기 위해 student 객체 생성
//		// 하지만 수정 시 새로운 비번,이메일 등의 데이터를 입력하여 student객체에 넣었을 때,
//		// student가 new Student()로 선언되어있어 '새로운 객체'에 수정내용들이 저장되는 것과
//		// 같아 비번,이메일,전화번호,주소,취미 를 제외한 다른 정보들은 'null'로 저장되므로
//		// 기존의 데이터에서 수정하기 위해 new Student()는 주석처리하고 modifyStudent생성자에
//		// student를 넣어줌으로서 기존 데이터에서 변경할 수 있도록 하였다.
//		Student student = new Student();
		System.out.print("수정할 비밀번호 입력 : ");
		String studentPwd = sc.next();
		System.out.print("수정할 이메일 입력 : ");
		String email = sc.next();
		System.out.print("수정할 전화번호 입력 : ");
		String phone = sc.next();
		System.out.print("수정할 주소 입력 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("수정할 취미 입력 : ");
		String hobby = sc.next();

		student.setStudentPwd(studentPwd);
		student.setEmail(email);
		student.setPhone(phone);
		student.setAddress(address);
		student.setHobby(hobby);
		
		return student;
	}
	

	public void printMessage(String msg) {
		System.out.println(msg);
	}

	// 성공메시지 출력
	public void displaySuccess(String message) {
		System.out.println("[서비스 성공] : " + message);
	}

	// 실패메시지 출력
	public void displayError(String message) {
		System.out.println("[서비스 실패] : " + message);
	}

}
