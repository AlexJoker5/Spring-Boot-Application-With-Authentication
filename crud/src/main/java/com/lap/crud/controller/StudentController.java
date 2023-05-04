package com.lap.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lap.crud.entity.Student;
import com.lap.crud.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/account/student/{id}")
	public String student (Model model, @PathVariable long id) {
		Student student = new Student();
		if (id != 0) {
			student = service.findByID(id);
		}
		model.addAttribute("student", student);
		return "student";
	}
	
	@GetMapping("/account/students")
	public String students (Model model) {
		List<Student> students = service.findAll();
		model.addAttribute("students", students);
		return "students";
	}
	
	@PostMapping("/account/save")
	public String save (@ModelAttribute("student") Student student) {
		service.save(student);
		return "redirect:/account/students";
	}
	
	@GetMapping("/account/delete/{id}")
	public String delete (@PathVariable long id) {
		service.delete(service.findByID(id));
		return "redirect:/account/students";
	}
	
}
