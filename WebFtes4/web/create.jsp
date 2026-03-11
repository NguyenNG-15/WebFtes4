<%-- 
    Document   : create
    Created on : Mar 11, 2026, 11:48:47 PM
    Author     : NGUYENSE190290
--%>

<%@page import="ftes.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Form</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) request.getAttribute("USER_INFO");
            boolean isEditMode = (user != null); 
            
            String title = isEditMode ? "Update User Information" : "Add New User";
            
            // QUAN TRỌNG: Khớp với hằng số CREATE và UPDATE trong MainController
            String actionName = isEditMode ? "Update" : "Create"; 
            String buttonName = isEditMode ? "Update" : "Save";
            
            String userID = isEditMode ? String.valueOf(user.getUserID()) : "0";
            String fullName = request.getParameter("fullName") != null ? request.getParameter("fullName") : (isEditMode ? user.getFullName() : "");
            String dob = request.getParameter("dob") != null ? request.getParameter("dob") : (isEditMode && user.getDob() != null ? user.getDob().toString() : "");
            String email = request.getParameter("email") != null ? request.getParameter("email") : (isEditMode ? user.getEmail() : "");
            String phone = request.getParameter("phone") != null ? request.getParameter("phone") : (isEditMode ? user.getPhone() : "");
            String gender = request.getParameter("gender") != null ? request.getParameter("gender") : (isEditMode ? user.getGender() : "Male"); 
            
            String errFullName = (String) request.getAttribute("ERROR_FULLNAME");
            String errEmail = (String) request.getAttribute("ERROR_EMAIL");
            String errPhone = (String) request.getAttribute("ERROR_PHONE");
        %>

        <h2><%= title %></h2>
        
        <form action="MainController" method="POST">
            <input type="hidden" name="userID" value="<%= userID %>" />

            FullName: <input type="text" name="fullName" value="<%= fullName %>"/>
            <span style="color:red"><%= errFullName != null ? errFullName : "" %></span><br/><br/>
            
            Gender: 
            <input type="radio" name="gender" value="Male"   <%= "Male".equals(gender) ? "checked" : "" %> /> Male
            <input type="radio" name="gender" value="Female" <%= "Female".equals(gender) ? "checked" : "" %> /> Female <br/><br/>
            
            DOB: <input type="date" name="dob" value="<%= dob %>" required/><br/><br/>
            
            Email: <input type="text" name="email" value="<%= email %>"/>
            <span style="color:red"><%= errEmail != null ? errEmail : "" %></span><br/><br/>
            
            Phone: <input type="text" name="phone" value="<%= phone %>"/>
            <span style="color:red"><%= errPhone != null ? errPhone : "" %></span><br/><br/>
            
            <input type="hidden" name="action" value="<%= actionName %>" />
            
            <input type="submit" value="<%= buttonName %>" />
            <a href="MainController?action=List">Cancel</a>
        </form>
    </body>
</html>
