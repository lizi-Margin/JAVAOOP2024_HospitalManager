import java.util.Scanner;




import java.util.HashMap;

class Student {
    public String studentID;
    public String name;
    public double mathScore;
    public double englishScore;
    public double javaScore;

    // Constructor
    public Student(String studentID, String name, double mathScore, double englishScore, double javaScore) {
        this.studentID = studentID;
        this.name = name;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
        this.javaScore = javaScore;
    }

    // Getters
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getMathScore() {
        return mathScore;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    public double getJavaScore() {
        return javaScore;
    }

    // Method to calculate average score
    public double calculateAverage() {
        return (mathScore + englishScore + javaScore) / 3;
    }
}

class StudentManager {
    private static HashMap<String, Student> students = new HashMap<>();

    // Method to add student
    public static void addStudent(String studentID, String name, double mathScore, double englishScore, double javaScore) {
        if (!students.containsKey(studentID)) {
            Student newStudent = new Student(studentID, name, mathScore, englishScore, javaScore);
            students.put(studentID, newStudent);
            System.out.println("Add success");
        } else {
            System.out.println("Students already exist");
        }
    }

    // Method to delete student
    public static void deleteStudent(String studentID) {
        if (students.containsKey(studentID)) {
            students.remove(studentID);
            System.out.println("Delete success");
        } else {
            System.out.println("Students do not exist");
        }
    }

    // Method to update student score
    public static void updateScore(String studentID, double mathScore, double englishScore, double javaScore) {
        if (students.containsKey(studentID)) {
            Student student = students.get(studentID);
            student.mathScore = mathScore;
            student.englishScore = englishScore;
            student.javaScore = javaScore;
            System.out.println("Update success");
        } else {
            System.out.println("Students do not exist");
        }
    }

    // Method to display student average score
    public static void displayAverageScore(String studentID) {
        if (students.containsKey(studentID)) {
            Student student = students.get(studentID);
            System.out.println("Student ID:" + student.getStudentID());
            System.out.println("Name:" + student.getName());
            System.out.printf("Average Score:%.1f\n", student.calculateAverage());
        } else {
            System.out.println("Students do not exist");
        }
    }
}

















public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            int operation = Integer.parseInt(input[0]);
            // StudentManager m = new StudentManager();
            
            switch (operation) {
                case 1:
                    String studentID1 = input[1];
                    String name1 = input[2];
                    double mathScore1 = Double.parseDouble(input[3]);
                    double englishScore1 = Double.parseDouble(input[4]);
                    double javaScore1 = Double.parseDouble(input[5]);
                    StudentManager.addStudent(studentID1, name1, mathScore1, englishScore1, javaScore1);
                    break;
                case 2:
                    String studentID2 = input[1];
                    StudentManager.deleteStudent(studentID2);
                    break;
                case 3:
                    String studentID3 = input[1];
                    double mathScore3 = Double.parseDouble(input[2]);
                    double englishScore3 = Double.parseDouble(input[3]);
                    double javaScore3 = Double.parseDouble(input[4]);
                    StudentManager.updateScore(studentID3, mathScore3, englishScore3, javaScore3);
                    break;
                case 4:
                    String studentID4 = input[1];
                    StudentManager.displayAverageScore(studentID4);
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
        
        scanner.close();
    }
}

