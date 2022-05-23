package sba.sms.utils;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.services.CourseService;
import sba.sms.services.StudentService;

import java.util.List;

public class CommandLine implements StudentI {
    private CommandLine() {
        // Utility classes should not have public constructors
    }

    private static final String PASSWORD = " ";
    public static void addData() {

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        String instructorPhillip = "Phillip Witkin";
        studentService.createStudent(new Student("reema@gmail.com", "reema brown", PASSWORD));
        studentService.createStudent(new Student("annette@gmail.com", "annette allen", PASSWORD));
        studentService.createStudent(new Student("anthony@gmail.com", "anthony gallegos", PASSWORD));
        studentService.createStudent(new Student("ariadna@gmail.com", "ariadna ramirez", PASSWORD));
        studentService.createStudent(new Student("bolaji@gmail.com", "bolaji saibu", PASSWORD));

        courseService.createCourse(new Course("Java", instructorPhillip));
        courseService.createCourse(new Course("Frontend", "Kasper Kain"));
        courseService.createCourse(new Course("JPA", "Jafer Alhaboubi"));
        courseService.createCourse(new Course("Spring Framework", instructorPhillip));
        courseService.createCourse(new Course("SQL", instructorPhillip));


    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public void createStudent(Student student) {


    }

    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        return false;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {

    }

    @Override
    public List<Course> getStudentCourses(String email) {
        return null;
    }
}
