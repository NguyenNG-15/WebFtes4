/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ftes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYENSE190290
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {


   // 1. Khai báo các giá trị action nhận từ JSP
    private static final String LIST = "List";
    private static final String CREATE_PAGE = "CreatePage";
    private static final String CREATE = "Create";
    private static final String EDIT_PAGE = "EditPage";
    private static final String UPDATE = "Update";
    private static final String DELETE = "Delete";

    // 2. Khai báo tên các file JSP và Controller đích đến
    private static final String ERROR_PAGE = "error.jsp";
    private static final String LIST_CONTROLLER = "ListController";
    private static final String CREATE_PAGE_VIEW = "create.jsp"; // Chuyển thẳng tới form rỗng
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String DELETE_CONTROLLER = "DeleteController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Gán URL mặc định nếu không khớp action nào
        String url = ERROR_PAGE; 
        
        try {
            String action = request.getParameter("action");
            
            // Xử lý chuyển hướng giống y hệt ảnh bạn gửi
            if (action == null || LIST.equals(action)) {
                url = LIST_CONTROLLER;
            } else if (CREATE_PAGE.equals(action)) {
                url = CREATE_PAGE_VIEW;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (EDIT_PAGE.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            }
            
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
