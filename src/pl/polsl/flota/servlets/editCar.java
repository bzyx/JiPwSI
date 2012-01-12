/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.flota.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementNotFound;

/**
 *
 * @author bzyx
 */
public class editCar extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editCar</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editCar at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/plain; charset=ISO-8859-2");

        // strumień do którego będzie zapisywana generowana odpowiedź
        PrintWriter out = response.getWriter();
        out.println("<html> <body>");
        out.println("Edytuję samochód: ");
        out.print("Numer rejestracyjny: ");
        out.println(request.getParameter("reg_number"));

        out.println("<a href=\"../index.jsp\">Powrót</a>");
        CarController carController = new CarController("/home/bzyx/dev/java/flota-servlet/JiPwSI/cars.json");
        try {
            carController.editCar(request.getParameter("reg_number"),
                    request.getParameter("carName"),
                    request.getParameter("distance"),
                    request.getParameter("consumption"));
        } catch (ElementNotFound ex) {
            out.println("Nie udało się zapisać.");
            //ex.printStackTrace();
        } catch (NumberFormatException ex) {
            out.println("Błędny format wprowadzonych danych. ");
        }
        out.println("</body> </html>");
        carController.save("/home/bzyx/dev/java/flota-servlet/JiPwSI/cars.json");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
