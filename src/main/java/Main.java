import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Создаем набор студентов так, чтобы можно было проверить следующие 3 функции.
        List<Student> students = makeStudents();

        // Поиск уникальных курсов.
        System.out.println("Уникальные курсы");
        System.out.println(filterUniqueCoursesFrom(students));

        // Поиск самых любознательных
        System.out.println("Любознательные");
        System.out.println(getTopThreeInquisitiveStudents(students));

        // Поиск студентов по курсу
        System.out.println("Кто ходит на курс");
        System.out.println(findStudentsByCourse(students, new Course("Матан")));
    }

    public static List<Student> makeStudents() {
        List<Student> students = Arrays.asList(
            new Student(
                "Василий",
                Arrays.asList(
                    new Course("Матан"),
                    new Course("Информатика"),
                    new Course("Философия"),
                    new Course("Числовые методы")
                )
            ),
            new Student(
                "Ирина",
                Arrays.asList(
                    new Course("Машинное обучение"),
                    new Course("Нейронные сети"),
                    new Course("Матан"),
                    new Course("Числовые методы"),
                    new Course("История")
                )
            ),
            new Student(
                "Анна",
                Arrays.asList(
                    new Course("Природоведение"),
                    new Course("Матан"),
                    new Course("Естествознание"),
                    new Course("Числовые методы"),
                    new Course("История"),
                    new Course("Обществознание")
                )
            ),
            new Student(
                "Владимир",
                Arrays.asList(
                    new Course("Экономика"),
                    new Course("Матан"),
                    new Course("Естествознание"),
                    new Course("Международное право"),
                    new Course("История"),
                    new Course("Обществознание"),
                    new Course("Английский язык")
                )
            )
        );

        return students;
    }

    // 1. Написать функцию, принимающую список Student и
    // возвращающую список уникальных курсов,
    // на которые подписаны студенты.
    public static List<Course> filterUniqueCoursesFrom(List<Student> students) {
        List<Course> courses =
            students.stream()
                .flatMap(element -> element.getAllCourses().stream()) // Берем все коллекции курсов с каждого студента
                .collect(Collectors.toList());                        // и преобразуем в список курсов.

        return courses.stream()
            .filter(course -> Collections.frequency(courses, course) < 2) // Ищем только те курсы, которые встречаются
            .collect(Collectors.toList());                                // менее 2 раз в коллекции. Т.е. ищем уникальные.
    }

    // 2. Написать функцию, принимающую на вход список Student и
    // возвращающую список из трех самых любознательных
    // (любознательность определяется количеством курсов).
    public static List<Student> getTopThreeInquisitiveStudents(List<Student> from) {
        return from.stream()
            .sorted(new Comparator<Student>() { // Существуют не только анонимные методы, но и анонимные
                @Override                                           // классы. В данном случае создан анонимный класс
                public int compare(Student o1, Student o2) {        // наследник класса Comparator, в котором определено
                    if (o1.getAllCourses().size() > o2.getAllCourses().size()) {// поведение сравнения.
                        return -1;
                    } else if (o1.getAllCourses().size() < o2.getAllCourses().size()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            })
            .limit(3) // ограничиваемся 3 топовыми студентами
            .collect(Collectors.toList());
    }

    // 3. Написать функцию, принимающую на вход список Student
    // и экземпляр Course, возвращающую список студентов,
    // которые посещают этот курс.
    public static List<Student> findStudentsByCourse(List<Student> from, Course course) {
        return from.stream()
            .filter(student -> student.getAllCourses().contains(course)) // Найти студента такого, что в его списке
            .collect(Collectors.toList());                               // курсов значится переданный в метод.
    }
}