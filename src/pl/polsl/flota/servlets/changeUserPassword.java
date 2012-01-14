/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.flota.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.flota.controller.UserController;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.User;

/**
 *
 * @author All
 */
public class changeUserPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet changeUserPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeUserPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean wasError = false;
        HttpSession session = request.getSession();
        UserController userController = new UserController(session.getAttribute("usersFilePath").toString());

        User user = null;
        try {
            user = userController.getUserList().getUserById(Integer.parseInt(request.getParameter("userId")));
        } catch (ElementNotFound ex) {
            Logger.getLogger(changeUserPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        String responseText = " <p><ul> <li>Imię i nazwisko: <em> "
                + user.getFullName()
                + " </em></li> <li>Nowe hasło: <em> "
                + request.getParameter("password")
                + " </em></ul> <br> <strong> Wynik: </strong> ";
        try {
            userController.editUser(user.getUserId().toString(), request.getParameter("password"));
        } catch (ElementNotFound ex) {
            wasError = true;
            responseText += "Nie udało się zapisać.";
        }
        if (!wasError) {
            responseText += "Wprowadzono pomyślnie zmiany.";
        }
        responseText += "</p>";
        userController.save(session.getAttribute("usersFilePath").toString());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/response.jsp");
        request.setAttribute("title", "Zmiana hasła");
        request.setAttribute("inner", responseText);
        rd.forward(request, response);
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
