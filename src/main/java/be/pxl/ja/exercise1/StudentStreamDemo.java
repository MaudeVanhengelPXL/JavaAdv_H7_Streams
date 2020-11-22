package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.util.List;

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> studentList = StudentDao.createStudents();

        //alle jarige studenten van vandaag uitprinten
        studentList.stream()
                .filter(s -> s.getDateOfBirth().withYear(2020).equals(LocalDate.now()))
                .forEach(s -> System.out.println(s.getName()));

        //alle gegevens tonen van student met naam Carol
        studentList.stream()
                .filter(s -> s.getName().equals("Carol"))
                .forEach(System.out::println);

        //Hoeveel studenten studeerden af in 2017
        long count = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .count();
        System.out.println(count);

        //Wat is de hoogste score ooit behaalde, wie behaalde deze?
        //TODO: Vanaf hier verder werken
    }
}
