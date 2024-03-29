package controller;

import com.mysql.cj.xdevapi.PreparableStatement;
import database.DbConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController implements StudentService {
    @Override
    public boolean saveStudent(Student s) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Student VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,s.getId());
        preparedStatement.setObject(2,s.getName());
        preparedStatement.setObject(3,s.getEmail());
        preparedStatement.setObject(4,s.getContact());
        preparedStatement.setObject(5,s.getAddress());
        preparedStatement.setObject(6,s.getNic());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean updateStudent(Student s) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Student SET studentName=?, email=?, contact=?, address=?, nic=? WHERE studentId=?");
        preparedStatement.setObject(1,s.getName());
        preparedStatement.setObject(2,s.getEmail());
        preparedStatement.setObject(3,s.getContact());
        preparedStatement.setObject(4,s.getAddress());
        preparedStatement.setObject(5,s.getNic());
        preparedStatement.setObject(6,s.getId());
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Student WHERE studentId='"+id+"'").executeUpdate() > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Student getStudent(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student WHERE studentId=?");
        preparedStatement.setObject(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                    );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Student> getAllStudent() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Student");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
                    ));
        }
        return students;
    }
}
