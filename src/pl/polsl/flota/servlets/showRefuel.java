/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.flota.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.Car;
import pl.polsl.flota.model.Refuel;

/**
 *
 * @author bzyx
 */
public class showRefuel extends HttpServlet {

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet showRefuel</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet showRefuel at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        CarController carController = new CarController("/home/bzyx/dev/java/flota-servlet/JiPwSI/cars.json");
        Car requestedCar;
        PrintWriter out = response.getWriter();
        try {
        requestedCar = carController.getCarByRegistrationNumber(request.getParameter("id").toString());
            List<Refuel> listOfRefuel = requestedCar.getHistoryOfRefuel();
            for (Refuel elem : listOfRefuel){
                out.println(elem.getAmount());
            }
        } catch (ElementNotFound e) {
            out.print("Nie znaleiziono takiego samochodu");
        }
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet showRefuel</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet showRefuel at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            out.print(request.getParameterMap());
            //out.print();
             
        } finally {            
            out.close();
        }
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
        processRequest(request, response);
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
