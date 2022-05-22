package com.example.tpo4_bm_20136;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "SearchDataServlet", value = "/SearchDataServlet")
public class SearchDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userParameterName = "Type";

        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=TPO;encrypt=true;trustServerCertificate=true";
        Statement stmt = null;
        ResultSet result = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String databaseUserName = "sa";
        String databasePassword = "sa_password_MTB123";

        try {
            if (request.getParameter(userParameterName) != null) {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
                stmt = conn.createStatement();

                result = null;
                String pa,us;

                if (!request.getParameter(userParameterName).isEmpty())
                    result = stmt.executeQuery(String.format("select * from Cars where Type = '%s'", request.getParameter(userParameterName)));
                else
                    result = stmt.executeQuery(String.format("select * from Cars"));

                String resultValue = "";
                while (result.next()) {
                    resultValue += result.getString("Name") + ";" + result.getString("Type") + ";" + result.getInt("ProductionDate") + ";" + result.getFloat("FuelConsumption") + "\n";
                }

                request.setAttribute("sqlResult", resultValue);
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/PrintDataServlet");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
