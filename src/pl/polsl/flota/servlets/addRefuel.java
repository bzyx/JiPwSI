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
 * @author All
 */
public class addRefuel extends HttpServlet {

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
            out.println("<title>Servlet addRefuel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addRefuel at " + request.getContextPath() + "</h1>");
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
        String responseText = " <p><ul> <li>Ilość paliwa: <em> "
                + request.getParameter("amoutOfFuel")
                + " </em></li> <li>Cena: <em> "
                + request.getParameter("price")
                + " </em></li> <li>Przebieg: <em> "
                + request.getParameter("distance")
                + " </em></li></ul> <br> <strong> Wynik: </strong> ";
        Boolean wasError = false;
        HttpSession session = request.getSession();
        CarController carController = new CarController(session.getAttribute("DB_URL").toString());
        try {
            carController.refuel((Integer) session.getAttribute("userId"), request.getParameter("distance"),
                    request.getParameter("amoutOfFuel"), request.getParameter("price"));
        } catch (ElementNotFound ex) {
            wasError = true;
            responseText += "Wystąpił błąd - samochód nie zaleiziony.";
        } catch (NumberFormatException ex) {
            wasError = true;
            responseText += "Wystąpił błąd - błędny format danych.";
        }
        if (!wasError) {
            responseText += "Pomyślnie zapisano tankowanie.";
        }
        responseText += "</p>";
        carController.save(session.getAttribute("DB_URL").toString());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/response.jsp");
        request.setAttribute("title", "Tankowanie pojazdu - wynik");
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
