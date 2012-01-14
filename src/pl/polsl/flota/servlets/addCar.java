/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.flota.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementAlredyExists;

/**
 *
 * @author bzyx
 */
public class addCar extends HttpServlet {

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
             * addCar</title>"); out.println("</head>"); out.println("<body>");
             * out.println("<h1>Servlet addCar at " + request.getContextPath ()
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
                + " </em></li> <li>Marka: <em> " 
                + request.getParameter("carName")
                + " </em></li></ul> <br> <strong> Wynik: </strong> ";
        Boolean wasError = false;
        HttpSession session = request.getSession();
        CarController carController = new CarController(session.getAttribute("carsFilePath").toString());
        try {
            carController.addCar(request.getParameter("reg_number"),
                    request.getParameter("carName"),
                    Integer.parseInt(request.getParameter("distance"), 10),
                    Float.parseFloat(request.getParameter("consumption")));
        } catch (ElementAlredyExists ex) {
            wasError = true;
            responseText += "Taki samochód już istnieje. Nie można dodać po raz drugi.";
        } catch (NumberFormatException ex) {
            wasError = true;
            responseText += "Błędny format wprowadzonych danych.";
        }
        if (!wasError){
            responseText += "Dodano pomyślnie.";
        }
        responseText += "</p>";
        carController.save(session.getAttribute("carsFilePath").toString());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/response.jsp");
        request.setAttribute("title", "Dodawanie samochodu - wynik");
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
