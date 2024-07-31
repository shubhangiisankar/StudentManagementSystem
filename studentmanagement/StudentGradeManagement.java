import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student Name: " + name + ", Grade: " + grade;
    }
}

public class StudentGradeManagement {
    private List<Student> students;
    private Scanner scanner;

    public StudentGradeManagement() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("1. Enter student grades");
            System.out.println("2. Compute average, highest, and lowest scores");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int option = readInt();

            switch (option) {
                case 1:
                    enterGrades();
                    break;
                case 2:
                    computeStatistics();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void enterGrades() {
        students.clear(); // Clear previous students
        System.out.print("Enter the number of students: ");
        int numStudents = readInt();

        scanner.nextLine(); // Consume leftover newline

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            double grade;
            while (true) {
                System.out.print("Enter grade for " + name + ": ");
                grade = readDouble();
                if (grade >= 0 && grade <= 100) {
                    break;
                } else {
                    System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                }
            }

            students.add(new Student(name, grade));
        }
        System.out.println("Grades have been entered.");
    }

    private void computeStatistics() {
        if (students.isEmpty()) {
            System.out.println("No grades entered.");
            return;
        }

        double total = 0.0;
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();

        for (Student student : students) {
            double grade = student.getGrade();
            total += grade;
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = total / students.size();

        System.out.println("Average score: " + average);
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);
    }

    private int readInt() {
        while (true) {
            System.out.print("Enter an integer: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private double readDouble() {
        while (true) {
            System.out.print("Enter a number: ");
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        StudentGradeManagement studentGradeManagement = new StudentGradeManagement();
        studentGradeManagement.start();
    }
}
