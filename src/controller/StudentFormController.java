package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import views.table.StudentTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentFormController {
    public TextField txtID;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtNIC;
    public TableView<StudentTable> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colNIC;
    public TableColumn colAddress;

    public void initialize() {
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));

            setStudentTable(new StudentController().getAllStudent());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student student = new Student(
                txtID.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                txtNIC.getText()
        );

        if (new StudentController().saveStudent(student)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            initialize();
        }else {
            new Alert(Alert.AlertType.WARNING, "Not save").show();
        }
    }

    public void updateStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student student = new Student(
                txtID.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                txtNIC.getText()
        );

        if (new StudentController().updateStudent(student)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            initialize();
        }else {
            new Alert(Alert.AlertType.WARNING, "Not Update").show();
        }
    }

    public void deleteStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new StudentController().deleteStudent(txtID.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Delete").show();
            initialize();
        }else {
            new Alert(Alert.AlertType.WARNING, "Not Delete").show();
        }
    }

    public void searchID(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtID.getText();

        Student student = new StudentController().getStudent(id);

        if (student == null) {
            new Alert(Alert.AlertType.WARNING, "Empty").show();
        }else {
            dataSet(student);
        }
    }

    private void dataSet(Student student) {
        txtID.setText(student.getId());
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        txtContact.setText(student.getContact());
        txtAddress.setText(student.getAddress());
        txtNIC.setText(student.getNic());
    }

    private void setStudentTable(ArrayList<Student> students) {
        ObservableList<StudentTable> observableList = FXCollections.observableArrayList();
        students.forEach(e->{
            observableList.add(
                    new StudentTable(e.getId(),e.getName(),e.getEmail(),e.getContact(),e.getAddress(),e.getNic())
            );
        });
        tblStudent.setItems(observableList);
    }
}
