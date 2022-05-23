package sba.sms.dao;

import sba.sms.models.Course;

import java.util.List;

public interface CourseI {
    void createCourse(Course course);

    List<Course> getAllCourses();

    Course getCourseById(int courseId);



}
