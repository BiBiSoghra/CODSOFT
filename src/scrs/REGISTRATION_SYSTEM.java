package scrs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;

    Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + " (" + enrolledStudents + "/" + capacity + " enrolled)";
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Registered for course: " + course.title);
        } else {
            System.out.println("Course is full: " + course.title);
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Dropped course: " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

public class REGISTRATION_SYSTEM {
    private static HashMap<String, Course> courseDatabase = new HashMap<>();
    private static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeData();

        while (true) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Add New Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    addNewStudent(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeData() {
        // Sample Data
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Learn the basics of programming.", 3));
        courseDatabase.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to differential calculus.", 2));
    }

    private static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);
        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            Course course = courseDatabase.get(courseCode);
            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found. Please register the student first.");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);
        if (student != null) {
            System.out.print("Enter course code: ");
            String courseCode = scanner.nextLine();
            Course course = courseDatabase.get(courseCode);
            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void addNewStudent(Scanner scanner) {
        System.out.print("Enter new student ID: ");
        String studentId = scanner.nextLine();
        if (studentDatabase.containsKey(studentId)) {
            System.out.println("Student ID already exists. Please use a different ID.");
            return;
        }
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        Student newStudent = new Student(studentId, studentName);
        studentDatabase.put(studentId, newStudent);
        System.out.println("Student added successfully: " + newStudent);
    }
}
