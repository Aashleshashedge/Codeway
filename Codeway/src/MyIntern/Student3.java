package MyIntern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student3 {


	
	    private String name;
	    private int rollNumber;
	    private String grade;

	    public Student3(String name, int rollNumber, String grade) {
	        this.name = name;
	        this.rollNumber = rollNumber;
	        this.grade = grade;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getRollNumber() {
	        return rollNumber;
	    }

	    public String getGrade() {
	        return grade;
	    }

	    @Override
	    public String toString() {
	        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
	    }
	}

	class StudentManagementSystem {
	    private List<Student3> students = new ArrayList<>();

	    public void addStudent(Student3 student) {
	        students.add(student);
	    }

	    public void removeStudent(int rollNumber) {
	        students.removeIf(student -> student.getRollNumber() == rollNumber);
	    }

	    public Student3 findStudent(int rollNumber) {
	        for (Student3 student : students) {
	            if (student.getRollNumber() == rollNumber) {
	                return student;
	            }
	        }
	        return null;
	    }

	    public List<Student3> getAllStudents() {
	        return students;
	    }

	    public void saveToFile(String filename) {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
	            oos.writeObject(students);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void loadFromFile(String filename) {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	            students = (List<Student3>) ois.readObject();
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public class Task3 {
	    public static void main(String[] args) {
	        StudentManagementSystem sms = new StudentManagementSystem();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("Student Management System");
	            System.out.println("1. Add Student");
	            System.out.println("2. Remove Student");
	            System.out.println("3. Search Student");
	            System.out.println("4. Display All Students");
	            System.out.println("5. Save to File");
	            System.out.println("6. Load from File");
	            System.out.println("7. Exit");
	            System.out.print("Select an option: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter roll number: ");
	                    int rollNumber = scanner.nextInt();
	                    scanner.nextLine(); // Consume the newline character
	                    System.out.print("Enter grade: ");
	                    String grade = scanner.nextLine();

	                    Student3 student = new Student3(name, rollNumber, grade);
	                    sms.addStudent(student);
	                    break;
	                case 2:
	                    System.out.print("Enter roll number to remove: ");
	                    int rollToRemove = scanner.nextInt();
	                    scanner.nextLine(); // Consume the newline character
	                    sms.removeStudent(rollToRemove);
	                    break;
	                case 3:
	                    System.out.print("Enter roll number to search: ");
	                    int rollToSearch = scanner.nextInt();
	                    scanner.nextLine(); // Consume the newline character
	                    Student3 foundStudent = sms.findStudent(rollToSearch);
	                    if (foundStudent != null) {
	                        System.out.println("Student found: " + foundStudent);
	                    } else {
	                        System.out.println("Student not found.");
	                    }
	                    break;
	                case 4:
	                    List<Student3> allStudents = sms.getAllStudents();
	                    if (allStudents.isEmpty()) {
	                        System.out.println("No students in the system.");
	                    } else {
	                        for (Student3 s : allStudents) {
	                            System.out.println(s);
	                        }
	                    }
	                    break;
	                case 5:
	                    sms.saveToFile("students.dat");
	                    System.out.println("Data saved to file.");
	                    break;
	                case 6:
	                    sms.loadFromFile("students.dat");
	                    System.out.println("Data loaded from file.");
	                    break;
	                case 7:
	                    System.out.println("Exiting application.");
	                    scanner.close();
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }
	}

