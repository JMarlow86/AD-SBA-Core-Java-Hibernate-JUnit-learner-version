package sba.sms.services;


import org.hibernate.HibernateException;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;


import java.util.List;
import java.util.*;

public class CourseService implements CourseI {
    //hibernate configuration session factory helper
    //data initializer helper (dummy data dump)

    @Override
    //persist course to database, also handle commit,rollback, and exceptions
    public void createCourse(Course course) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.persist(course);
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
    }

    @Override
    //return course if exists, also handle commit,rollback, and exceptions
    public Course getCourseById(int courseId) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            Course course = s.get(Course.class, courseId);
            if (course == null)
                throw new HibernateException("Cannot find course");
            else
                return course;
        } catch (HibernateException exception) {
            exception.printStackTrace();
        } finally {
            s.close();
        }
        return null;
    }

    @Override
    //return all courses from database, also handle commit,rollback, and exceptions
    public List<Course> getAllCourses() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Course> course = new ArrayList<>();
        try {
            tx = s.beginTransaction();
            Query q = s.createQuery("from Course", Course.class);
            course = q.getResultList();
            tx.commit();
        } catch (HibernateException exception) {
            if (tx != null) tx.rollback();
            exception.printStackTrace();
        } finally {
            s.close();
        }
            return course;

    }
}