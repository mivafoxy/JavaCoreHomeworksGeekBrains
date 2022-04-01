import java.util.Objects;

public class Course {
    public String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // Переопределения ниже необходимы для работы метода Collections.frequency.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
