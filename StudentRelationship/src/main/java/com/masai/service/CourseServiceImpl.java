package com.masai.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Course;
import com.masai.model.Student;
import com.masai.repository.CourseDao;
import com.masai.repository.StudentDao;

@Service
public class CourseServiceImpl  implements CourseService{
     @Autowired
	private CourseDao cDao;
    
	@Override
	public Course registerNewCourse(Course course) {
		// here taking list of all student and association 
//	 Set<Student> students =  course.getStudent();
//	 //associating each student with course
//	 for (Student student: students) {
//		 
//		 student.getCourses().add(course);
//		 
//	 }
	
	 return cDao.save(course);
	}	

}

