package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> studentList = StudentDao.createStudents();
        //Geen stream hier declareren, stream kan maar 1 keer gebruikt worden, dus telkens opnieuw maken

        //1) alle jarige studenten van vandaag uitprinten
        studentList.stream()
                .filter(student -> student.getDateOfBirth().withYear(2020).equals(LocalDate.now()))
                .forEach(s -> System.out.println(s.getName()));

        //2) alle gegevens tonen van student met naam Carol
        studentList.stream()
                .filter(s -> s.getName().equals("Carol"))
                .forEach(System.out::println);

        //3) Hoeveel studenten studeerden af in 2017
        long count = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2017)
                .count();
        System.out.println("Aantal studenten die afstudeerden in 2017: " + count);

        //4) Wat is de hoogste score ooit behaald, wie behaalde deze?
        Optional<Student> studentWithHighestScore = studentList.stream()
                .sorted()
                .findFirst();
        System.out.println("Student met de hoogste score: " + studentWithHighestScore.get());

        //5) Wie is de oudste persoon in de lijst
            //.sorted((s1, s2) -> s1.getDateOfBirth().compareTo(s2.getDateOfBirth()))
            //.sorted(Comparator.comparing(Student::getDateOfBirth))
            //.findFirst();
        Optional<Student> oldestStudent = studentList.stream().min(Comparator.comparing(Student::getDateOfBirth));
        System.out.println("Oudste student: " + oldestStudent.get());

        //6) Geef de name van alle studenten gescheiden door komma in één string
        String commaSeperatedStudents = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
        System.out.println(commaSeperatedStudents);

        //7) Wie is de jongste student van afstudeerjaar 2018
        Optional<Student> youngestStudent2018 = studentList.stream()
                .filter(s -> s.getGraduationYear() == 2018)
                .max(Comparator.comparing(Student::getDateOfBirth));
        System.out.println("De jongste student van afstudeerjaar 2018 is: " + youngestStudent2018.get());

        //8 Maak een map met per afstudeerjaar de gemiddelde score
        Map<Integer, Double> scorePerGraduationYear = studentList.stream()
                .collect(Collectors.groupingBy(
                        Student::getGraduationYear,
                        Collectors.averagingInt(Student::getScore)
                ));
        System.out.println(scorePerGraduationYear);

        //9 Sorteer eerst op studentenjaar en daarna op score hoog naar laag

        studentList.stream()
                .sorted(
                        Comparator.comparing(Student::getGraduationYear)
                        .thenComparing(Student::getScore).reversed()
                )
                .forEach(student -> System.out.println(String.format("Naam: %s, afstudeerjaar: %d, score: %d",
                        student.getName(), student.getGraduationYear(), student.getScore())));
    }
}
