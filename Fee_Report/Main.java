package Fee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String name;
    private double courseFee;

    public Course(String name, double courseFee) {
        this.name = name;
        this.courseFee = courseFee;
    }

    public String getName() {
        return name;
    }

    public double getCourseFee() {
        return courseFee;
    }

    @Override
    public String toString() {
        return name + " - $" + courseFee;
    }
}

class Student {
    private String name;
    private int rollNumber;
    private double feesPaid;
    private double totalFees;
    private ArrayList<Course> courses;

    public Student(String name, int rollNumber, double totalFees) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.totalFees = totalFees;
        this.feesPaid = 0;
        this.courses = new ArrayList<>();
    }

    public void payFees(double amount) {
        feesPaid += amount;
    }

    public double getRemainingFees() {
        return totalFees - feesPaid;
    }

    public void enrollCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        StringBuilder coursesList = new StringBuilder();
        for (Course course : courses) {
            coursesList.append("\n").append(course);
        }

        return "Name: " + name + "\nRoll Number: " + rollNumber +
                "\nFees Paid: $" + feesPaid + "\nTotal Fees: $" + totalFees +
                "\nRemaining Fees: $" + getRemainingFees() + "\nCourses:" + coursesList;
    }
}

class FeeReportSystem {
    private ArrayList<Student> students;
    private int nextRollNumber;
    private Map<String, Course> courses;

    public FeeReportSystem() {
        students = new ArrayList<>();
        nextRollNumber = 1;
        courses = new HashMap<>();
        initializeCourses();
    }

    private void initializeCourses() {
        courses.put("Math", new Course("Math", 500));
        courses.put("Science", new Course("Science", 600));
        courses.put("History", new Course("History", 400));
        // Add more courses here
    }

    public void addStudent(String name, double totalFees) {
        Student student = new Student(name, nextRollNumber, totalFees);
        students.add(student);
        nextRollNumber++;
        System.out.println("Student added successfully.");
    }

    public void recordFees(int rollNumber, double amount) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.payFees(amount);
                System.out.println("Fees recorded successfully.");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void enrollCourse(int rollNumber, String courseName) {
        Course course = courses.get(courseName);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.enrollCourse(course);
                System.out.println("Course enrollment successful.");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void generateReport() {
        for (Student student : students) {
            System.out.println(student);
            System.out.println("-----------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FeeReportSystem feeReportSystem = new FeeReportSystem();

        while (true) {
            System.out.println("1. Add Student\n2. Record Fees\n3. Enroll Course\n4. Generate Report\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter total fees: ");
                    double totalFees = scanner.nextDouble();
                    feeReportSystem.addStudent(name, totalFees);
                    break;
                case 2:
                    System.out.print("Enter student roll number: ");
                    int rollNumber = scanner.nextInt();
                    System.out.print("Enter fee amount: ");
                    double amount = scanner.nextDouble();
                    feeReportSystem.recordFees(rollNumber, amount);
                    break;
                case 3:
                    System.out.print("Enter student roll number: ");
                    int rollNum = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    feeReportSystem.enrollCourse(rollNum, courseName);
                    break;
                case 4:
                    feeReportSystem.generateReport();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
