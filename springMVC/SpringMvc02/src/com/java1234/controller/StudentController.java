package com.java1234.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.model.Student;

@Controller
@RequestMapping("/student")
// 请求地址
public class StudentController {

	// 模拟一些仿真的数据
	private static List<Student> studentList = new ArrayList<Student>();

	static {
		studentList.add(new Student(1, "张三", 11));
		studentList.add(new Student(2, "李四", 12));
		studentList.add(new Student(3, "王武", 13));
	}

	/**
	 * 获取所有的学生信息
	 */
	// 请求地址
	@RequestMapping("/list")
	public ModelAndView list() {
		// 视图控制
		ModelAndView mav = new ModelAndView();
		// 封装数据
		mav.addObject("studentList", studentList);
		// 要跳转到的页面
		mav.setViewName("student/list");
		return mav;
	}

	/**
	 * @param id
	 *            点击修改，获取学生信息，跳转到修改页面
	 */
	@RequestMapping("/preSave")
	public ModelAndView preSave(
			@RequestParam(value = "id", required = false) String id) {// RequestParam接收前台传递的值，request=false表示不必须有
		ModelAndView mav = new ModelAndView();
		if (id != null) {
			mav.addObject("student", studentList.get(Integer.parseInt(id) - 1));
			mav.setViewName("student/update");
		} else {
			mav.setViewName("student/add");
		}
		return mav;
	}

	/**
	 * @param student
	 * @return 保存
	 */
	@RequestMapping("/save")
	public String save(Student student) {
		// 修改
		if (student.getId() != 0) {
			Student s = studentList.get(student.getId() - 1);
			s.setName(student.getName());
			s.setAge(student.getAge());
		} else {
			// 添加
			studentList.add(student);
		}
		// return "redirect:/student/list.do";重定向请求list.do，跳转到list.jsp
		// return "forward:/student/list.do";
		// 转发,url地址不会改变，重定向url地址会改变
		return "forward:/student/list.do";
	}

	/**
	 * @param id
	 * @return 删除
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		studentList.remove(id - 1);
		return "redirect:/student/list.do";
	}
}
