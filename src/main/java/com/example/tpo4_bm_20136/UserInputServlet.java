package com.example.tpo4_bm_20136;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "UserInputServlet", value = "/UserInputServlet")
public class UserInputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<form name=\"UserInputServlet\" method=\"post\" action=\"UserInputServlet\">" +
                "Type: <input type=\"text\" name=\"Type\"/> <br/>"+
                "<input type=\"submit\" value=\"Search for cars\" />"+
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/SearchDataServlet");
        requestDispatcher.forward(request, response);
    }
}
