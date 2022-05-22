package com.example.tpo4_bm_20136;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

@WebServlet(name = "PrintDataServlet", value = "/PrintDataServlet")
public class PrintDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String sqlResultName = "sqlResult";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "}\n" +
                "</style><body>");
        if(request.getAttribute(sqlResultName) == null)
            out.println("<h1>Empty</h1>");
        else {
            if (request.getAttribute(sqlResultName).toString().isEmpty())
                out.println("<h1>Empty</h1>");
            else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Type</th>");
                out.println("<th>Production Date</th>");
                out.println("<th>Fuel Consumption</th>");
                out.println("</tr>");

                BufferedReader bufReader = new BufferedReader(new StringReader(request.getAttribute(sqlResultName).toString()));
                String line=null;
                while( (line=bufReader.readLine()) != null )
                {
                    String dataItems[] = line.split(";");
                    out.println("<tr>");
                    out.println("<td>" + dataItems[0] + "</td>");
                    out.println("<td>" + dataItems[1] + "</td>");
                    out.println("<td>" + dataItems[2] + "</td>");
                    out.println("<td>" + dataItems[3] + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
