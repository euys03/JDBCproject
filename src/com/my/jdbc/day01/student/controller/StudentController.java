package com.my.jdbc.day01.student.controller;

import java.util.List;

import com.my.jdbc.day01.student.model.dao.StudentDAO;
import com.my.jdbc.day01.student.model.vo.Student;

public class StudentController {
	// DAO에서 받고, Controller에서 Run으로 보내서 실행
	
	/**
	 * [1] 학생 전체 목록 조회
	 * @param studentList
	 * @return List<student>
	 **/
	public List<Student> printAll() {
		StudentDAO sDao = new StudentDAO();
		List<Student> sList = sDao.selectAll();
		//sDao.selectAll();
		return sList;
	}
	
	/**
	 * [2] 학생 아이디로 조회
	 * @param studentId
	 * @return List<student>
	 **/
	public Student printOneById(String studentId) {
		StudentDAO sDao = new StudentDAO();
		Student student = sDao.selectOneById(studentId);
		return student;
	}
	
	/**
	 * [3] 학생 이름으로 조회
	 * @param studentName
	 * @return List<student>
	 **/
	public List<Student> printAllByName(String studentName) {
		StudentDAO sDao= new StudentDAO();
		List<Student> sList = sDao.selectAllByName(studentName);
		return sList;
	}
	
	/**
	 * [4] 회원가입
	 * @param student
	 **/
	public int registerStudent(Student student) {
		StudentDAO sDao = new StudentDAO();
		int result = sDao.insertMember(student);
		return result;
	}
	
	/**
	 * [5] 정보수정
	 * @param student
	 * @return 
	 **/
	public int modifyStudent(Student student) {
		StudentDAO sDao = new StudentDAO();
		int result = sDao.updateStudent(student);
		return result;
	}
	
	/**
	 * [6] 회원탈퇴
	 * @param studentId
	 **/
	public int removeStudent(String studentId) {
		StudentDAO sDao = new StudentDAO();
		int result = sDao.deleteMemer(studentId);;
		return result;
		// Void methods cannot return a value
	}
	
}
