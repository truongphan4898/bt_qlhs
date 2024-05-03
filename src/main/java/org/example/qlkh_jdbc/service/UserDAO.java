package org.example.qlkh_jdbc.service;

import org.example.qlkh_jdbc.model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IUserDAO{
    private String jdbcURL="jdbc:mysql://localhost:3306/qlkh";
    private String jdbcName="root";
    private String jdbcPassword="admin123456";
    private static final String INSERT_USER="insert into users(name, email, address) value (?,?,?);";
    private static final String SELECT_USER="select id, name, email, address from users where id=?;";
    private static final String SELECT_ALL_USER="select * from users;";
    private static final String DELETE_USER = "delete from users where id=?;";
    private static final String UPDATE_USER ="update users set name=?, email=?, address=? where id=?;";
    public UserDAO(){}
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcName,jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    @Override
    public void insertUser(Users user) throws SQLException {

    }

    @Override
    public Users selectUser(int id) {
        return null;
    }

    @Override
    public List<Users> selectAllUser() {
        return null;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(Users user) throws SQLException {
        return false;
    }
}
