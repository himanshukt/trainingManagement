package com.virtusa.tmsless.dao;

import java.util.List;
import java.util.Map;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;

public interface EmployeeDaoIface {
	String postCourses(Courses course);
	String generateCourseId();
	List<Courses> listCourses(int userId);
	List<Courses> listCoursesPosted(int userId);
	Map<Courses,CourseRegistration>viewHistory(int userId);
	List<CourseRegistration> viewEmpolyee(int userId);
	int updateStatus(String status,String registrationId);
	int updateFeedback(String courseId,double rating);
	String registerCourse(CourseRegistration courseRegistration);
	String generateRegistrationId();

}
