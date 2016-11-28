package com.ssi.stu.bo;


public class StuInfo {
	private Integer stuId;
	private String stuName;
	private Integer age;
	private String sex;
	private String email;
	private String mobile;
	private String address;
	
	public StuInfo(Integer stuId, String stuName, Integer age, String sex, String email, String mobile, String address,
			StudentDetail studentDetail) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.age = age;
		this.sex = sex;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.studentDetail = studentDetail;
	}

	public StuInfo() {
		super();
	}

	/** 学生详细信息 */
	private StudentDetail studentDetail;

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}
	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
