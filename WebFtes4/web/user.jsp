<%-- 
    Document   : user
    Created on : Mar 11, 2026, 11:48:23 PM
    Author     : NGUYENSE190290
--%>

<%@page import="ftes.users.UserDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User List</title>
        <style> table, th, td { border: 1px solid black; border-collapse: collapse; padding: 8px; text-align: left;} </style>
    </head>
    <body>
        <h2>User Management</h2>
        <a href="MainController?action=CreatePage">Add New User</a>
        <br/><br/>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>FullName</th><th>Gender</th><th>DOB</th><th>Email</th><th>Phone</th><th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<UserDTO> userList = (List<UserDTO>) request.getAttribute("USER_LIST");
                    if (userList != null && !userList.isEmpty()) {
                        for (UserDTO user : userList) {
                %>
                    <tr>
                        <td><%= user.getUserID() %></td>
                        <td><%= user.getFullName() %></td>
                        <td><%= user.getGender() %></td>
                        <td><%= user.getDob() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPhone() %></td>
                        <td>
                            <a href="MainController?action=EditPage&id=<%= user.getUserID() %>">Edit</a> | 
                            <a href="MainController?action=Delete&id=<%= user.getUserID() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa user này?');">Delete</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="7">No users found.</td></tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
