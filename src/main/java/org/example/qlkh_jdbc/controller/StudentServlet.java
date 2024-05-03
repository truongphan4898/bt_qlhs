package org.example.qlkh_jdbc.controller;

import com.mysql.cj.util.DnsSrv;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.qlkh_jdbc.HelloServlet;
import org.example.qlkh_jdbc.model.Student;
import org.example.qlkh_jdbc.service.StudentDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet" , value = "/student")

public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    public void init(){
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action="";
        }
        try{
            switch (action){
                case "create":
                    insertStudent(req,resp);
                    break;
                case "edit":
                    updateStudent(req,resp);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }
    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException
        ,ServletException{
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        float mark = Float.parseFloat(request.getParameter("mark"));
        Student student = new Student(firstName,lastName,dob,address,mark);
        studentDAO.insertStudent(student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
    }
    private void updateStudent (HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException,ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        float mark = Float.parseFloat(request.getParameter("mark"));
        Student student = new Student(id,firstName,lastName,dob,address,mark);
        studentDAO.updateStudent(student);
        RequestDispatcher dispatcher= request.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action= "";
        }
        try{
            switch (action){
                case "create":
                    showCreateForm(req,resp);
                    break;
                case "edit":
                    showUpdateForm(req,resp);
                    break;
                case "delete":
                    deleteStudent(req,resp);
                    break;
                default:
                    showList(req,resp);
                    break;
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }

    }
     private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException{
         List < Student> studentList = studentDAO.selectAllStudent();
         request.setAttribute("studentList", studentList);
         RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
         dispatcher.forward(request,response);
     }
     private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException{
        RequestDispatcher dispatcher =request.getRequestDispatcher("student/create.jsp");
        dispatcher.forward(request,response);
     }
     private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        request.setAttribute("student",student);
        dispatcher.forward(request,response);
     }
     private void deleteStudent (HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        List<Student>studentList=studentDAO.selectAllStudent();
        request.setAttribute("studentList",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
     }

}
