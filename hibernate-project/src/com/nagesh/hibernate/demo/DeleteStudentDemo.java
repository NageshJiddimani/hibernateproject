package com.nagesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagesh.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();

	try {
		int StudentId=5;
		 
		//now get a new session and start transaction
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		//retrieve student based on the id: primary key
		System.out.println("\nGetting student with id: " +StudentId);
		
		Student myStudent = session.get(Student.class, +StudentId);
		
		//delete the student
		//System.out.println("Deleting student: "+myStudent);
		//session.delete(myStudent);
		
		//delete student Id=2
		System.out.println("deleting student id=2");
		
		session.createQuery("delete from Student where id =2").executeUpdate();
		
		//commit the transaction
		session.getTransaction().commit();
		
		System.out.println("Done!");

	}
	finally{
		factory.close();
	}
}
}
