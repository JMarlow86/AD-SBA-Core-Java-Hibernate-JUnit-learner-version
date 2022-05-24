package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data


@Table(name = "course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(length = 50, nullable = false)
    String name;

    @NonNull
    @Column(length = 50, nullable = false)
    String instructor;

    @NonNull
    @Column
    @ToString.Exclude
    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH}, fetch = FetchType.EAGER)
    List<Student> students = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && name.equals(course.name) && instructor.equals(course.instructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instructor);
    }
}



