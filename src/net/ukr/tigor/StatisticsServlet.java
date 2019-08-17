package net.ukr.tigor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static net.ukr.tigor.MyServletContextListener.stat;

public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.print("<h1>Statistics</h1>");
        pw.print(stat.getReportByQuestion());
        pw.print(stat.getReportByRespondents());

    }
}
