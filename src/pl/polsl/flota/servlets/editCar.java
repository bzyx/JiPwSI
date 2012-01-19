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
import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementNotFound;

/**
 *
 * @author Marcin Jabrzyk
 */
public class editCar extends HttpServlet {

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
             * TODO output your page here out.println("<html>");
             * out.println("<head>"); out.println("<title>Servlet
             * editCar</title>"); out.println("</head>"); out.println("<body>");
             * out.println("<h1>Servlet editCar at " + request.getContextPath ()
             * + "</h1>"); out.println("</body>"); out.println("</html>");
             */
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
        String responseText = " <p><ul> <li>Numer rejestracyjny: <em> "
                + request.getParameter("reg_number")
                + " </em></ul> <br> <strong> Wynik: </strong> ";
        Boolean wasError = false;
        HttpSession session = request.getSession();
        CarController carController = new CarController(session.getAttribute("DB_URL").toString());
        try {
            carController.editCar(request.getParameter("reg_number"),
                    request.getParameter("carName"),
                    request.getParameter("distance"),
                    request.getParameter("consumption"));
        } catch (ElementNotFound ex) {
            wasError = true;
            responseText += "Nie udało się zapisać.";
        } catch (NumberFormatException ex) {
            wasError = true;
            responseText += "Błędny format wprowadzonych danych. ";
        }
        if (!wasError) {
            responseText += "Wprowadzono pomyślnie zmiany.";
        }
        responseText += "</p>";
        carController.save(session.getAttribute("DB_URL").toString());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/response.jsp");
        request.setAttribute("title", "Edycja samochodu - wynik");
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
