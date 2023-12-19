import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a course
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    // Constructor to initialize a course
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

// Class to represent a student
class Student {
    int studentID;
    String name;
    String department;
    ArrayList<String> registeredCourses;

    // Constructor to initialize a student
    public Student(int studentID, String name, String department) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    // ArrayList to store course and student data
    static ArrayList<Course> courseDatabase = new ArrayList<>();
    static ArrayList<Student> studentDatabase = new ArrayList<>();

    public static void main(String[] args) {
        enrollStudent(); // Enroll the student first
        initializeCourses(); // Initialize sample courses
        menu(); // Display the main menu
    }

    private static void enrollStudent() {
        // Enroll the student by taking their name, ID, and department
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Course Registration System!");
        System.out.println("To start your learning journey with us, firstly Register yourself ");
        System.out.print("Enter your name champ: ");
        String name = sc.nextLine();

        System.out.print("Enter your student ID: ");
        int studentID = sc.nextInt();

        System.out.print("Enter your department: ");
        sc.nextLine(); // Consume the newline character
        String department = sc.nextLine();

        studentDatabase.add(new Student(studentID, name, department));
        System.out.println("Enrollment successful!\n");
        System.out.println("Nowww let's get into the world of learning "+name);
    }

    private static void initializeCourses() {
        // Initialize sample courses and add them to the course database
        courseDatabase.add(new Course("IT2301", "Java Programming", "Basic Java programming concepts", 30, "Mon/Wed 10:00 AM"));
        courseDatabase.add(new Course("EC6702", "Optical Communication and Networks", "Concepts of Fibre Optics and Networks", 25, "Tue/Thu 2:00 PM"));
        // many more courses can be added
    }

    private static void menu() {
        // Display the main menu and handle user choices
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. List of Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    viewRegisteredCourses();
                    break;
                case 0:
                    System.out.println("Exiting the program. Happy Learning!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void displayAvailableCourses() {
        // Display information about available courses
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase) {
            int availableSlots = course.capacity - getRegisteredStudents(course.courseCode).size();
            System.out.println("   "+course.courseCode + " - " + course.title + " (" + availableSlots + " slots available)");
            System.out.println("   Schedule: " + course.schedule);
            System.out.println("   Description: " + course.description);
            System.out.println();
        }
    }

    private static void registerForCourse() {
        // Allow the student to register for a course
        Scanner scanner = new Scanner(System.in);
        displayAvailableCourses();
        System.out.print("Enter the course code you want to register for: ");
        String courseCode = scanner.nextLine().toUpperCase();

        Course selectedCourse = findCourse(courseCode);
        if (selectedCourse != null) {
            if (canRegister(selectedCourse, getCurrentStudent())) {
                getCurrentStudent().registeredCourses.add(courseCode);
                System.out.println("Successfully registered for " + selectedCourse.title + ".");
            } else {
                System.out.println("Sorry, you cannot register for this course. It is either full or you are already registered.");
            }
        } else {
            System.out.println("Invalid course code. Please try again.");
        }
    }

    private static void dropCourse() {
        // Allow the student to drop a registered course
        Scanner scanner = new Scanner(System.in);
        viewRegisteredCourses();
        System.out.print("Enter the course code you want to drop: ");
        String courseCode = scanner.nextLine().toUpperCase();

        Course selectedCourse = findCourse(courseCode);
        if (selectedCourse != null) {
            if (getCurrentStudent().registeredCourses.contains(courseCode)) {
                getCurrentStudent().registeredCourses.remove(courseCode);
                System.out.println("Successfully dropped " + selectedCourse.title + ".");
            } else {
                System.out.println("You are not registered for this course.");
            }
        } else {
            System.out.println("Invalid course code. Please try again.");
        }
    }

    private static void viewRegisteredCourses() {
        // Display the courses that the student is registered for
        Student currentStudent = getCurrentStudent();
        System.out.println("\nRegistered Courses for " + currentStudent.name + ":");
        for (String courseCode : currentStudent.registeredCourses) {
            Course course = findCourse(courseCode);
            if (course != null) {
                System.out.println(course.courseCode + " - " + course.title);
            }
        }
    }

    private static Student getCurrentStudent() {
        // Get the current student (in this simplified system, we assume only one student is using the system)
        if (studentDatabase.isEmpty()) {
            studentDatabase.add(new Student(1, "Aravind", "ECE"));
        }
        return studentDatabase.get(0);
    }

    private static Course findCourse(String courseCode) {
        // Find a course by its course code
        for (Course course : courseDatabase) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static ArrayList<Student> getRegisteredStudents(String courseCode) {
        // Get the list of students registered for a specific course
        ArrayList<Student> registeredStudents = new ArrayList<>();
        for (Student student : studentDatabase) {
            if (student.registeredCourses.contains(courseCode)) {
                registeredStudents.add(student);
            }
        }
        return registeredStudents;
    }

    private static boolean canRegister(Course course, Student student) {
        // Check if a student can register for a course (enough capacity and not already registered)
        return (course.capacity - getRegisteredStudents(course.courseCode).size() > 0) && !student.registeredCourses.contains(course.courseCode);
    }
}


