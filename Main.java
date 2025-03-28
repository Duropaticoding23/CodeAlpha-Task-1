import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentId;
    private ArrayList<Double> grades;

    public Student(String studentId) {
        this.studentId = studentId;
        this.grades = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double findHighestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double findLowestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }
}

class StudentGradeTracker {
    private ArrayList<Student> students;

    public StudentGradeTracker() {
        students = new ArrayList<>();
    }

    public void addStudent(String studentId) {
        students.add(new Student(studentId));
    }

    public Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentGradeTracker tracker = new StudentGradeTracker();

        System.out.println("Welcome to the Student Grade Tracker!");

        while (true) {
            System.out.print("Enter student ID (or type 'exit' to finish): ");
            String studentId = scanner.nextLine();

            if (studentId.equalsIgnoreCase("exit")) {
                break;
            }

            tracker.addStudent(studentId);
            Student currentStudent = tracker.getStudent(studentId);

            while (true) {
                System.out.print("Enter a grade for student " + studentId + " (or type 'done' to finish): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("done")) {
                    break;
                }

                try {
                    double grade = Double.parseDouble(input);
                    currentStudent.addGrade(grade);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid grade.");
                }
            }
        }

        // Display results for each student
        for (Student student : tracker.getStudents()) {
            if (!student.getGrades().isEmpty()) {
                System.out.printf("Student ID: %s%n", student.getStudentId());
                System.out.printf("Average Grade: %.2f%n", student.calculateAverage());
                System.out.printf("Highest Grade: %.2f%n", student.findHighestGrade());
                System.out.printf("Lowest Grade: %.2f%n", student.findLowestGrade());
                System.out.println();
            } else {
                System.out.printf("Student ID: %s has no grades entered.%n", student.getStudentId());
            }
        }

        scanner.close();
    }
}