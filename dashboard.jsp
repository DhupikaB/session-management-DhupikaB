<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.example.demo.DashboardServlet.Course" %>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Welcome, <%= request.getAttribute("username") %>!</h2>
<p><a href="LogoutServlet">Logout</a></p>

<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<p style="color: green;"><%= message %></p>
<%
    }
%>

<h3>Available Courses:</h3>
<table border="1">
    <tr>
        <th>Course ID</th>
        <th>Course Name</th>
        <th>Instructor</th>
        <th>Action</th>
    </tr>
    <%
        List<Course> courseList = (List<Course>) request.getAttribute("courseList");
        if (courseList != null) {
            for (Course c : courseList) {
    %>
    <tr>
        <td><%= c.getCourseId() %></td>
        <td><%= c.getCourseName() %></td>
        <td><%= c.getInstructor() %></td>
        <td><a href="EnrollServlet?courseId=<%= c.getCourseId() %>">Enroll</a></td>
    </tr>
    <%
            }
        }
    %>
</table>

<h3>Your Enrolled Courses:</h3>
<ul>
    <%
        List<Course> enrolledCourses = (List<Course>) request.getAttribute("enrolledCourses");
        if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
            for (Course c : enrolledCourses) {
    %>
    <li><%= c.getCourseName() %> (ID: <%= c.getCourseId() %>)</li>
    <%
        }
    } else {
    %>
    <li>You have not enrolled in any course yet.</li>
    <%
        }
    %>
</ul>
</body>
</html>
