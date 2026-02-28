package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Student;

public class MySQLStudentDAO implements StudentDAO {

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_management",
                "root",
                "root");
    }

    @Override
    public void addStudent(Student student) {

        String insert = "INSERT INTO student_details "
                + "(Student_Name, Student_Email, Student_Age, Student_Mobile_Number) "
                + "VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(insert)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getMobile());

            int result = ps.executeUpdate();

            if (result > 0)
                System.out.println("Data inserted successfully...");
            else
                System.out.println("No data inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewAllStudents() {

        String sql = "SELECT * FROM student_details";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No data found");
                return;
            }

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("Student_Id"));
                System.out.println("NAME: " + rs.getString("Student_Name"));
                System.out.println("EMAIL: " + rs.getString("Student_Email"));
                System.out.println("AGE: " + rs.getInt("Student_Age"));
                System.out.println("MOBILE_NUMBER: " + rs.getString("Student_Mobile_Number"));
                System.out.println("----------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmailByMobile(String mobile, String newEmail) {

        String update = "UPDATE student_details "
                + "SET Student_Email = ? "
                + "WHERE Student_Mobile_Number = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(update)) {

            ps.setString(1, newEmail);
            ps.setString(2, mobile);

            int result = ps.executeUpdate();

            if (result > 0)
                System.out.println("Data updated successfully...");
            else
                System.out.println("No data updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentById(int id) {

        String sql = "DELETE FROM student_details WHERE Student_Id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0)
                System.out.println("Student Deleted Successfully");
            else
                System.out.println("No student found with this ID");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}