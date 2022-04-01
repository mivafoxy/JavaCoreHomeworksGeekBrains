import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String name;
    private List<Course> courses;

    public Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = new ArrayList<>(courses);
    }


    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return Collections.unmodifiableList(courses);
    }

    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            '}';
    }
}
