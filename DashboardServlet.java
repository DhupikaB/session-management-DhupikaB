package com.example.demo;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    public static class Course {
        private String courseId;
        private String courseName;
        private String instructor;

        public Course(String courseId, String courseName, String instructor) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.instructor = instructor;
        }

        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public String getInstructor() { return instructor; }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSC3093", "Object Oriented Analysis", "Prof.S.Kodithuwakku"));
        courses.add(new Course("CSC3112", "Software Engineering", "Dr.R.Siyambalapitiya"));
        courses.add(new Course("CSC3103", "Server Side Web Programming", "Mr.I.Madugalla"));

        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }


        String message = (String) session.getAttribute("message");
        session.removeAttribute("message");
        request.setAttribute("message", message);

        request.setAttribute("username", username);
        request.setAttribute("courseList", courses);
        request.setAttribute("enrolledCourses", enrolledCourses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
