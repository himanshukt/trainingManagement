package com.virtusa.tmsless.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtusa.tmsless.model.CourseRegistration;
import com.virtusa.tmsless.model.Courses;
import com.virtusa.tmsless.model.Users;

@Repository
public class UsersDaoImpl implements UsersDaoIface{
	static Logger logger=Logger.getLogger(UsersDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public List<String> validateUser(Users user) {
		String validateusers="select typeUser,UserName from UsersNew where userId=:userId and password=:pass";
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(validateusers);
		query.setParameter("userId", user.getUserId());
		query.setParameter("pass", user.getUserPassword());
		List<Object[]> list=query.list();
		List<String> listValidate=null;
		if(!list.isEmpty()) {
			listValidate=new ArrayList<String>();
			for(Object[] itr:list) {
				listValidate.add(itr[0].toString());
				listValidate.add(itr[1].toString());
			}
			return listValidate;
		}
		return listValidate;
	}
	@Transactional(readOnly = true)
	public Users aboutUsers(int userId) {
		return (Users)sessionFactory.getCurrentSession().get(Users.class,userId);
	}
	@Transactional
	public int addUser(Users user) {
		return (Integer)sessionFactory.getCurrentSession().save(user);
		
	}
	@Transactional
	public int generateUserId() {
		String generateuserid="select case when max(userId) is null then 1 else max(userId)+1 end eno from Usersnew";
		
		 SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(generateuserid);
		 return Integer.parseInt(query.list().get(0).toString());
	}
	@Transactional(readOnly = true)
	public List<Users> viewUsers() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Users order by userId");
		return query.list();
	}
	@Transactional(readOnly = true)
	public List<Courses> viewCourses() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Courses order by courseId");
		
		return query.list();
	}
	@Transactional
	public int scheduleTraing(Courses courses) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("update courses set startDate=:sDate,endDate=:eDate,venue=:ven where courseId=:crseId");
		query.setParameter("sDate",courses.getStartDate());
		query.setParameter("eDate", courses.getEndDate());
		query.setParameter("ven",courses.getVenue());
		query.setParameter("crseId", courses.getCourseId());
		return query.executeUpdate();
		
	}
	@Transactional(readOnly = true)
	public Courses viewSchedule(String courseId) {
		return (Courses)sessionFactory.getCurrentSession().get(Courses.class,courseId);
	}
	@Transactional(readOnly = true)
	public List<CourseRegistration> viewNominations(String courseId) {
		Query query=sessionFactory.getCurrentSession().createQuery("from CourseRegistration where courseId=:cId");
		query.setParameter("cId",courseId);
		return query.list();
	} 
}
