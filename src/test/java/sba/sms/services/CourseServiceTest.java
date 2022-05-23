package sba.sms.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


        @FieldDefaults(level = AccessLevel.PRIVATE)
     class courseServiceTest {

        static CourseService courseService;

        @BeforeAll
        static void beforeAll() {
            courseService = new CourseService();
            CommandLine.addData();
        }

        @Test
        void getAllCourses() {

            List<Course> toExpect = new ArrayList<>(Arrays.asList(
                    new Course("Spuds 101", "Mr.Potato Head"),
                    new Course("Horticulture", "Brock Coley"),
                    new Course("Regeneration","Willy Worm")
            ));
            assertThat(courseService.getAllCourses()).hasSameElementsAs(toExpect);

        }
    }
