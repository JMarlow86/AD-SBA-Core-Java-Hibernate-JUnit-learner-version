package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data


@Table (name = "student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(length = 50, unique = false, nullable = false)
    String name;

    @NonNull
    @Column(length = 50, unique = true, nullable = false)
    String email;

    @NonNull
    @Column(length = 50, unique = false, nullable = false)
    String password;

    public Student(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }



    public void addCourse(Course course){
        courses.add(course);
        course.setStudents((List<Student>) this);
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
    List<Course> courses = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && name.equals(student.name) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
