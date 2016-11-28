package com.ssi.stu.bo;

public class StudentDetail {
	private int studentDetailId;
	private String hobby;

	
	
	public StudentDetail(int studentDetailId, String hobby) {
		super();
		this.studentDetailId = studentDetailId;
		this.hobby = hobby;
	}

	public StudentDetail() {
		super();
	}

	public int getStudentDetailId() {
		return studentDetailId;
	}

	public void setStudentDetailId(int studentDetailId) {
		this.studentDetailId = studentDetailId;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

}
