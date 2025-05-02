package com.example.demo;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);

        List<DashboardServlet.Course> allCourses = getAllCourses();
        DashboardServlet.Course selectedCourse = null;

        for (DashboardServlet.Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }

        if (selectedCourse != null) {
            List<DashboardServlet.Course> enrolledCourses =
                    (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");

            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            boolean alreadyEnrolled = false;
            for (DashboardServlet.Course c : enrolledCourses) {
                if (c.getCourseId().equals(courseId)) {
                    alreadyEnrolled = true;
                    break;
                }
            }

            if (!alreadyEnrolled) {
                enrolledCourses.add(selectedCourse);
                session.setAttribute("message", "✅ Successfully enrolled in " + selectedCourse.getCourseName());
            } else {
                session.setAttribute("message", "⚠️ You are already enrolled in " + selectedCourse.getCourseName());
            }

            session.setAttribute("enrolledCourses", enrolledCourses);
        }

        response.sendRedirect("DashboardServlet");
    }

    private List<DashboardServlet.Course> getAllCourses() {
        List<DashboardServlet.Course> courses = new ArrayList<>();
        courses.add(new DashboardServlet.Course("CSC3093", "Object Oriented Analysis", "Prof.S.Kodithuwakku"));
        courses.add(new DashboardServlet.Course("CSC3112", "Software Engineering", "Dr.R.Siyambalapitiya"));
        courses.add(new DashboardServlet.Course("CSC3103", "Server Side Web Programming", "Mr.I.Madugalla"));
        return courses;
    }
}
