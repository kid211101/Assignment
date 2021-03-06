/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAL.OrderDAO;
import model.Order;
import java.util.ArrayList;
import DAL.CategoriesDAO;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Category;
import model.Customer;
import DAL.CustomerDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
/**
 *
 * @author khanh doan
 */
@WebServlet(name = "admin", urlPatterns = {"/admin"})
public class admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Customer cus = (Customer)request.getAttribute("cus");
           HttpSession session = request.getSession();
           session.setAttribute("cus", cus);
        //Order
        OrderDAO db = new OrderDAO();
        ArrayList<Order> listor = new ArrayList<>();
        listor = db.getAll();
        int pageor, numperpageor = 8;
        int sizeor = listor.size();
        int numor = (sizeor%8==0?(sizeor/8):((sizeor/8))+1);
        String xpageor = request.getParameter("pageor");    
        if (xpageor == null) {
            pageor = 1;
        } else {
            pageor = Integer.parseInt(xpageor);
        }
        int startor, endor;
        startor = (pageor - 1) * numperpageor;
        endor = Math.min(pageor * numperpageor, sizeor);
        ArrayList<Order> listorder = db.getListByPage(listor, startor, endor);
        request.setAttribute("listorder", listorder);
        request.setAttribute("numor", numor);
        //Book
        BookDAO db2 = new BookDAO();
        ArrayList<Book> listb = new ArrayList<>();
        listb = db2.getAll();
        request.setAttribute("listb", listb);
        
        //Category
        CategoriesDAO db1 = new CategoriesDAO();
        ArrayList<Category> listcate = new ArrayList<>();
        listcate = db1.getAll();
        request.setAttribute("listcate", listcate);
        
        //Moderator
        CustomerDAO dbmod = new CustomerDAO();
        ArrayList<Customer> listmod = new ArrayList<>();
        listmod = dbmod.getAll("mod");
        request.setAttribute("listmod", listmod);
        
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
