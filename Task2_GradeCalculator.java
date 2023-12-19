import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // get the marks obtained in each subject as input from the user
        System.out.println("Enter the marks obtained in each subject (out of 100):");
        int totalSubjects = 0;
        int totalMarks = 0;

        while (true) {
            System.out.print("Subject " + (totalSubjects + 1) + ": ");
            int marks = sc.nextInt();

            // Check if the entered marks are within the valid range (0-100)
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks buddy!! Kindly provide a number between 0 and 100..");
                continue;
            }

            totalMarks += marks;
            totalSubjects++;

            // Ask the user if there are more subjects
            System.out.print("Are there any other subjects to calculate totalmarks? (Yes/No): ");
            String moreSubjects = sc.next().toLowerCase();
            
            if (moreSubjects.equals("no")) {
                break;
            }
        }

        // Calculate Total Marks
        System.out.println("\nTotal Marks: " + totalMarks);

        // Calculate Average Percentage
        double avgPercentage = (double) totalMarks / totalSubjects;
        System.out.println("Average Percentage: " + avgPercentage + "%");

        // Grade Calculation
        char grade = calculateGrade(avgPercentage);

        // Display Results
        System.out.println("Grade: " + grade);

        // Close the scanner
        sc.close();
    }

    // Function to calculate grade based on average percentage
    private static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'O';
        } else if (averagePercentage >= 80) {
            return 'A';
        } else if (averagePercentage >= 70) {
            return 'B';
        } else if (averagePercentage >= 60) {
            return 'C';
        } else {
            return 'D';
        }
    }
}

