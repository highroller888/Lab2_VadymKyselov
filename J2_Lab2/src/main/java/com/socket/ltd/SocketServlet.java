package com.socket.ltd;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class SocketServlet
 */
@WebServlet("/SocketServlet")
public class SocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Proceed to the quote requesting by using an HTML file</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form parameters
        String socketType = request.getParameter("socketType");
        String quantityStr = request.getParameter("quantity");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Validate input
        int quantity = 0;
        boolean isValid = true;
        if (quantityStr != null && quantityStr.matches("\\d+")) {
            quantity = Integer.parseInt(quantityStr);
        } else {
            isValid = false;
            out.println("<h3>Invalid input. Please enter a numeric value.</h3>");
        }

        // Calculate price quote if valid
        if (isValid) {
            double pricePerSocket = getPricePerSocket(socketType);
            double totalPrice = pricePerSocket * quantity;

            // Generate quote response
            out.println("<h2>The quote for " + name + "</h2>");
            out.println("<p>Socket Type: " + socketType + "</p>");
            out.println("<p>Quantity: " + quantity + "</p>");
            out.println("<p>Total Price: $" + totalPrice + "</p>");
            out.println("<p>The copy of the quote will be sent to: " + email + "</p>");
        }

        out.close();
    }

    private double getPricePerSocket(String socketType) {
        switch (socketType) {
            case "Type A":
                return 5;
            case "Type B":
                return 10;
            case "Type C":
                return 15;
            default:
                return 0;
        }
    }
}
