package com;

import java.util.Scanner;
import dao.StudentDAO;
import dao.MySQLStudentDAO;
import model.Student;
import exception.InvalidStudentDataException;

public class StudentMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO dao = new MySQLStudentDAO();  

        while (true) {

            System.out.println("\n1.Add Student");
            System.out.println("2.View All Students");
            System.out.println("3.Update Email by Mobile");
            System.out.println("4.Delete Student by ID");
            System.out.println("5.Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Mobile: ");
                        String mobile = sc.nextLine();

                        validate(name, email, age, mobile);

                        Student student = new Student(name, email, age, mobile);
                        dao.addStudent(student);
                        break;

                    case 2:
                        dao.viewAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter Mobile: ");
                        String mob = sc.nextLine();

                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();

                        dao.updateEmailByMobile(mob, newEmail);
                        break;

                    case 4:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();

                        dao.deleteStudentById(id);
                        break;

                    case 5:
                        System.exit(0);
                }

            } catch (InvalidStudentDataException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void validate(String name, String email, int age, String mobile)
            throws InvalidStudentDataException {

        if (name.isEmpty() || name.matches("[0-9]+"))
            throw new InvalidStudentDataException("Invalid Name");

        if (!email.contains("@"))
            throw new InvalidStudentDataException("Invalid Email");

        if (age <= 0)
            throw new InvalidStudentDataException("Age must be positive");

        if (mobile.length()!=10)
            throw new InvalidStudentDataException("Mobile must be 10 digits");
    }
}