package dao;
import model.Student;

public interface StudentDAO {
		
	    void addStudent(Student student) ;

	    void viewAllStudents() throws Exception;

	    void updateEmailByMobile(String mobile, String newEmail) throws Exception;

	    void deleteStudentById(int id) throws Exception;
}
