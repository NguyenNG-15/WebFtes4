/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ftes.controller;

import ftes.users.UserDAO;
import ftes.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYENSE190290
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url = "create.jsp"; 
        
        try {
            // Lấy dữ liệu từ form
            String fullName = request.getParameter("fullName");
            String gender = request.getParameter("gender");
            String dobStr = request.getParameter("dob");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            boolean hasError = false;

            // --- KIỂM TRA LỖI (VALIDATION) ---
            if (fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("ERROR_FULLNAME", "Tên không được để trống!");
                hasError = true;
            }
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                request.setAttribute("ERROR_EMAIL", "Email không hợp lệ!");
                hasError = true;
            }
            if (phone == null || !phone.matches("\\d+")) {
                request.setAttribute("ERROR_PHONE", "Số điện thoại chỉ được chứa số!");
                hasError = true;
            }

            // --- NẾU KHÔNG CÓ LỖI THÌ THÊM VÀO DB ---
            if (!hasError) {
                Date dob = Date.valueOf(dobStr); // Chuyển chuỗi thành java.sql.Date
                UserDTO user = new UserDTO(0, fullName, gender, dob, email, phone);
                UserDAO dao = new UserDAO();
                
                boolean checkInsert = dao.insert(user);
                if (checkInsert) {
                    // Thêm thành công thì gọi lại MainController để chuyển về trang danh sách
                    url = "MainController?action=List";
                }
            }
            // Nếu có lỗi, file create.jsp sẽ tự động lấy lại dữ liệu bằng request.getParameter()
            
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
