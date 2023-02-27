package com.nagesh.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagesh.hibernate.demo.entity.Student;

public class QuerryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Student.class)
				                 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();

	try {
		 
		//start a transaction
		session.beginTransaction();
	 
		//query students
		List<Student> theStudents = session.createQuery("from Student").getResultList();
		
		//display the students
		displayStudents(theStudents);
		
		//query the students: lstName='Kohli'
		theStudents = session.createQuery("from Student s where s.lastName = 'Kohli'").getResultList();
		
		//display the students
		System.out.println("\n\nStudents who have last name of Kohli");
		displayStudents(theStudents);
		
		//query students: lastName='Kohli' OR firstName='Sanju'
		theStudents=
				session.createQuery("from Student s where"
						+ " s.lastName='Kohli' OR s.firstName='Sanju'").getResultList();
		
		System.out.println("\n\nStudents who have lastName of Kohli OR firstName of Sanju");
		displayStudents(theStudents);
		
		//query students where email LIKE '%funcode.com'
		theStudents = session.createQuery("from Student s where"
				+ " s.email LIKE '%funcode.com'").getResultList();
		
		System.out.println("\n\nStudents whose email ends with funcode.com");
		displayStudents(theStudents);
		
		//commit transaction
		session.getTransaction().commit();
		
		System.out.println("Done!");

	}
	finally{
		factory.close();
	}
}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
