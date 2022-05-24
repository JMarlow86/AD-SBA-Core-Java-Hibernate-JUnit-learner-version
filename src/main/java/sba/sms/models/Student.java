package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data


@Table (name = "student")
@Entity
public class Student {



    @Id
    @NonNull
    @Column(length = 50, nullable = false)
    String name;


    @NonNull
    @Column(length = 50, nullable = false)
    String email;

    @NonNull
    @Column(length = 50, nullable = false)
    String password;

        @NonNull
        public Student(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;

    }



    public void addCourse(Course course){
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course){
        courses.remove(course);
        course.setStudents(null);

    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    List<Course> courses = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && email.equals(student.email) && password.equals(student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, email);
    }
}
