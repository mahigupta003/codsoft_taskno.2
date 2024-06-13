package codsoft_task2;


import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Welcome and Instructions
        System.out.println("====================================");
        System.out.println(" Welcome to Student Grade Calculator     ");
        System.out.println("====================================");
        System.out.println("Instructions:");
        System.out.println("1. Enter the student's name when prompted.");
        System.out.println("2. Enter the total number of subjects.");
        System.out.println("3. Enter the marks obtained in each subject (0-100).");
        System.out.println("The program will then calculate and display the total marks, average percentage, and grade.");
        System.out.println("====================================\n");

        System.out.print("Enter the Student Name: ");
        String name = sc.nextLine();

        int totalSub = getTotalSubjects(sc);
        if (totalSub <= 0) {
            System.out.println("Number of subjects must be greater than zero.");
            sc.close();
            return;
        }

        int totalMarksObtained = getTotalMarks(sc, totalSub);
        int maxMarks = totalSub * 100;

        // Calculating average percentage
        double avgP = (double) totalMarksObtained / totalSub;

        // Calculating grade based on the average percentage
        String grade = calculateGrade(avgP);

        // Displaying result
        displayResult(name, totalMarksObtained, maxMarks, avgP, grade);

        sc.close();
    }

    private static int getTotalSubjects(Scanner sc) {
        System.out.print("Enter the total number of subjects: ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid number of subjects: ");
            sc.next(); // Consume the invalid input
        }
        return sc.nextInt();
    }

    private static int getTotalMarks(Scanner sc, int totalSub) {
        int totalMarksObtained = 0;
        for (int i = 0; i < totalSub; i++) {
            int marks = getMarksForSubject(sc, i + 1);
            if (marks == -1) {
                i--; // Retry the same subject if invalid input
                continue;
            }
            totalMarksObtained += marks;
        }
        return totalMarksObtained;
    }

    private static int getMarksForSubject(Scanner sc, int subjectNumber) {
        System.out.print("Enter marks obtained in subject " + subjectNumber + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter valid marks for subject " + subjectNumber + ": ");
            sc.next(); // Consume the invalid input
        }
        int marks = sc.nextInt();
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid Input! Marks should be between 0 and 100.");
            return -1;
        }
        return marks;
    }

    private static String calculateGrade(double avgP) {
        if (avgP >= 90) {
            return "A+";
        } else if (avgP >= 80) {
            return "A";
        } else if (avgP >= 70) {
            return "B";
        } else if (avgP >= 60) {
            return "C";
        } else if (avgP >= 50) {
            return "D";
        } else if (avgP >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    private static void displayResult(String name, int totalMarksObtained, int maxMarks, double avgP, String grade) {
        System.out.println("\nResult: \n");
        System.out.println("Student Name: " + name);
        System.out.println("Total Marks obtained: " + totalMarksObtained + " out of " + maxMarks+ ".");
        System.out.printf("Average Percentage Gained: %.2f %%\n", avgP );
        System.out.println("Grade: " + grade);
    }
}

