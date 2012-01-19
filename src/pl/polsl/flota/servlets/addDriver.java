/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.flota.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.flota.controller.UserController;
import pl.polsl.flota.exceptions.ElementAlredyExists;

/**
 *
 * @author All
 */
public class addDriver extends HttpServlet {

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
            out.println("<title>Servlet addDriver</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addDriver at " + request.getContextPath() + "</h1>");
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
        String responseText = " <p><ul> <li>Login: <em> "
                + request.getParameter("login")
                + " </em></li> <li>Hasło: <em> "
                + request.getParameter("password")
                + " </em></li> <li>Pełna nazwa: <em> "
                + request.getParameter("fullName")
                + " </em></li></ul> <br> <strong> Wynik: </strong> ";
        Boolean wasError = false;
        HttpSession session = request.getSession();
        UserController userController = new UserController((String)session.getAttribute("DB_URL"));
        try {
            userController.addUser(request.getParameter("login"), request.getParameter("password"), request.getParameter("fullName"));
        } catch (ElementAlredyExists ex) {
            wasError = true;
            responseText += "Taki użytkownik już istnieje. Nie można dodać po raz drugi.";
        } catch (Exception ex) {
            wasError = true;
            responseText += "Należy uzupełnić pola login i hasło.";
        }
        if (!wasError) {
            responseText += "Dodano pomyślnie.";
        }
        responseText += "</p>";
        userController.save((String)session.getAttribute("DB_URL"));

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/response.jsp");
        request.setAttribute("title", "Dodawanie użytkownika - wynik");
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
