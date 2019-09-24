package com.virtusa.tmsless.dao;



import java.util.List;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;



public interface UsersDaoIface {
	
	List<String> validateUser(Users user);
	Users aboutUsers(int userId);
	int addUser(Users user);
	int generateUserId();
	List<Users> viewUsers();
	List<Courses>viewCourses();
	int scheduleTraing(Courses courses);
	Courses viewSchedule(String courseId);
	List<CourseRegistration> viewNominations(String courseId);
	
}
