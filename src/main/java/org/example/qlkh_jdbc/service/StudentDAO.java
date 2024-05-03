package org.example.qlkh_jdbc.service;

import org.example.qlkh_jdbc.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO{
    private String jdbcName="root";
    private String jdbcPassword="admin123456";
    private String jdbcURL="jdbc:mysql://localhost:3306/qlkh";
    private static final String INSERT_STUDENT= "insert into student (firstname, lastname, dob, address, mark) value (?,?,?,?,?);";
    private static final String SELECT_STUDENT = "select id, firstname, lastname, dob, address, mark from student where id=?;";
    private static final String SELECT_ALL_STUDENT = "select * from student;";
    private static final String DELETE_STUDENT = "delete from student where id=?;";
    private static final String UPDATE_STUDENT = "update student set firstname=?, lastname=?, dob=?, address=?, mark=? where id=?;";
    public StudentDAO(){}
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcName,jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return connection;
    }
    @Override
    public void insertStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENT);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)
        ){
            preparedStatement.setString(1,student.getFirstname());
            preparedStatement.setString(2,student.getLastname());
            preparedStatement.setString(3,student.getDob());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setFloat(5,student.getMark());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Student selectStudent(int id) {
        Student student = null;
        try( Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                float mask = resultSet.getFloat("mark");
                student = new Student(id,firstName,lastName,dob,address,mask);

            }
        }catch (SQLException e){
            printSQLException (e);
        }return student;
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student>students = new ArrayList<>();
        try( Connection connection= getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT)){
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String dob = resultSet.getString("dob");
                String address = resultSet.getString("address");
                float mask = resultSet.getFloat("mark");
                students.add(new Student(id,firstName,lastName,dob,address,mask));
            }
        }catch (SQLException e){
            printSQLException (e);
        }return students;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDelete;
        try( Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)){
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }return rowDelete;
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)){
            preparedStatement.setString(1,student.getFirstname());
            preparedStatement.setString(2,student.getLastname());
            preparedStatement.setString(3,student.getDob());
            preparedStatement.setString(4,student.getAddress());
            preparedStatement.setFloat(5,student.getMark());
            preparedStatement.setInt(6,student.getId());
            rowUpdate = preparedStatement.executeUpdate()>0;

        }
        return rowUpdate;
    }
    private void printSQLException(SQLException exception){
        for (Throwable e:exception){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState "+((SQLException) e).getSQLState());
                System.err.println("SQLCode "+((SQLException)e).getErrorCode());
                System.err.println("message "+e.getMessage());
                Throwable throwable = exception.getCause();
                while (throwable != null){
                    System.out.println("cause "+ throwable);
                    throwable=throwable.getCause();
                }
            }
        }
    }

}
