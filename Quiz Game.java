import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class QuizGame {

    static int score = 0;  // User's score
    static Scanner scanner = new Scanner(System.in); // Scanner as a class member

    // Quiz data structure: Question, Options, and Correct Answer
    static String[][] quizData = {
            {"Who won the ODI World Cup in 2023?", "A. India", "B. Australia", "C. England", "D. Namibia", "B"},
            {"What is the capital of India?", "A. Chennai", "B. Bangalore", "C. New Delhi", "D. Coimbatore", "C"},
            {"Who invented Java Programming?", "A. Guido van Rossum", "B. James Gosling", "C. Dennis Ritchie", "D. Bjarne Stroustrup", "B"},
            {"What is the chemical symbol for gold?", "A. go", "B. Au", "C. Gold", "D. Ag", "B"},
            {"What gas do humans primarily breathe in?", "A. Oxygen", "B. Carbon", "C. Hydrogen", "D. Helium", "A"}
            // MANY QUESTIONS CAN BE ADDED
    };

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz Program!!");
        System.out.println("This Quiz Program contains 5 questions, and each question has 10 seconds to answer.");
        System.out.println("Press Enter to start the Quiz");
        scanner.nextLine(); // Using nextLine to consume the Enter key
        startQuiz();
        displayResult();
    }

    private static void startQuiz() {
        // Loop through each question
        for (String[] questionData : quizData) {
            displayQuestion(questionData);
            System.out.print("Your answer: ");

            // Set a timer for 10 seconds per question
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String userAnswer = scanner.nextLine().toUpperCase();

            // Check if the answer is correct
            if (userAnswer.equals(questionData[5])) {
                System.out.println("Hurray! Correct!\n");
                score++;
            } else {
                System.out.println("Oops! Incorrect. The correct answer is " + questionData[5] + ".\n");
            }
        }
    }

    private static void displayQuestion(String[] questionData) {
        System.out.println(questionData[0]);
        for (int i = 1; i < questionData.length - 1; i++) {
            System.out.println(questionData[i]);
        }
    }

    private static void displayResult() {
        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + quizData.length);
        scanner.close(); // Close the scanner to prevent resource leak
    }
}
