package com.nagesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagesh.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();

	try {
		int StudentId=4;
		 
		//now get a new session and start transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		//retrieve student based on the id: primary key
		System.out.println("\nGetting student with id: " +StudentId);
		
		Student myStudent = session.get(Student.class, +StudentId);
		
		System.out.println("Updating student..");
		myStudent.setFirstName("Mohammed");
		myStudent.setLastName("Shami");
		
		//commit the transaction
		session.getTransaction().commit();
		
		//New Code
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		//update email for all students
		System.out.println("Update email for all students");
		
		session.createQuery("update Student set email='foo@gmail.com'")
		    .executeUpdate();
		
		//commit the transaction
	    session.getTransaction().commit();
		
		System.out.println("Done!");

	}
	finally{
		factory.close();
	}
}
}
