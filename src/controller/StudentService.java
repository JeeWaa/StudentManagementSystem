package controller;

import model.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentService {
    public boolean saveStudent(Student s) throws SQLException, ClassNotFoundException;
    public boolean updateStudent(Student s);
    public boolean deleteStudent(String id);
    public Student getStudent(String id);
    public ArrayList<Student> getAllStudent() throws SQLException, ClassNotFoundException;
}
