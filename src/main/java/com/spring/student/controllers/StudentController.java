package com.spring.student.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.student.beans.Student;

@RestController
@RequestMapping("/template")
public class StudentController {
	
	@ Autowired
	RestTemplate template;
	
	 @GetMapping("/student")
	   public String getStudentList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return template.exchange("http://localhost:9000/user/all", HttpMethod.GET, entity, String.class).getBody();
	   }
	 
	 @PostMapping("/student")
	   public String createStudent(@RequestBody Student user) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Student> entity = new HttpEntity<Student>(user,headers);
	      
	      return template.exchange(
	         "http://localhost:9000/user/signup", HttpMethod.POST, entity, String.class).getBody();
	   }
	
	 @PutMapping("/student/{id}")
	   public String updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Student> entity = new HttpEntity<Student>(student,headers);
	      
	      return template.exchange(
	         "http://localhost:9000/user/update/"+id, HttpMethod.PUT, entity, String.class).getBody();
	   }
	 
	 @DeleteMapping("/student/{id}")
	   public String deleteStudent(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Student> entity = new HttpEntity<Student>(headers);
	      
	      return template.exchange(
	         "http://localhost:9000/user/delete/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
}
