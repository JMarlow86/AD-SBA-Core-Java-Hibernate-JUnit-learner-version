package sba.sms.services;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

//hibernate configuration session factory helper
//data initializer helper (dummy data dump)

@Log
public class StudentService implements StudentI {

    @Override
   //return all students from database, also handle commit,rollback, and exceptions
    public List<Student> getAllStudents() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Student> student = new ArrayList<>();
        try {
            s.beginTransaction();
            Query q = (Query) s.createQuery("from Student", Student.class).list();
            student = q.getResultList();
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return student;
    }

    @Override
    //persist student to database, also handle commit,rollback, and exceptions
    public void createStudent(Student student) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = s.beginTransaction();
            s.persist(student);
            tx.commit();
        } catch (HibernateException exception) {
            if(tx!=null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }

    }


    @Override
    //return student if exists, also handle commit,rollback, and exceptions
    public Student getStudentByEmail(String email) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Student student = s.get(Student.class,email);
            if(student == null)
                throw new HibernateException("Student not found.");
            else
                return new Student();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
       return null;
    }

    @Override
    //match email and password to database, also handle exceptions
    public boolean validateStudent(String email, String password) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            Student student = (Student) s.get(Student.class,email);
            if(student != null && student.getPassword().equals(password)) {
                System.out.println("Your password is correct");
                return true;
                }
            } catch (HibernateException exception) {
                exception.printStackTrace();
            } finally {
                s.close();
            }
        return false;
    }

    @Override
    //register a course to a student (collection to prevent duplication), also handle commit,rollback, and exceptions
    public void registerStudentToCourse(String email, int courseId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            Student student = s.get(Student.class, email); // accessing student by email parameter
            if (student.getCourses().contains(courseId)) { // checking if student course list contains course using course Id parameter
                System.out.println("You are already registered to this course");
            } else {
                CourseService courseService = new CourseService(); //gives access, lets you do actions related to courses
                Course course = courseService.getCourseById(courseId); //making object using the action, instantiated by doing actions with your course serv
                List<Course> courseList = student.getCourses();//mirroring what is in the student object made on line 112
                courseList.add(course);//adds course to the list
                student.setCourses(courseList);//resets the new course list
                s.persist(student); //saving the student object to database
                tx.commit(); }
            } catch(HibernateException exception){
                if (tx != null) tx.rollback();
                exception.printStackTrace();
            } finally{
                s.close();
            }
        }

    @Override
    //get all the student courses list (use native query), also handle commit,rollback, and exceptions
    public List<Course> getStudentCourses(String email) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            TypedQuery<Course> q = s.createNamedQuery("getStudentCourses");
            q.setParameter("student", email);
            return q.getResultList();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return null;
    }
}
