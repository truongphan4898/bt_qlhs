package org.example.qlkh_jdbc.service;

import org.example.qlkh_jdbc.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
public void insertStudent(Student student) throws SQLException;
public Student selectStudent(int id);
public List <Student> selectAllStudent();
public boolean deleteStudent(int id) throws SQLException;
public boolean updateStudent(Student student) throws SQLException;
}
