package net.ukr.tigor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static net.ukr.tigor.MyServletContextListener.stat;

public class QuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");

        if ((name==null)||(surname==null)||(age==null)){
            resp.sendRedirect("index.jsp");
            return;
        }

        HttpSession session = req.getSession();
        // until browser is open
        session.setMaxInactiveInterval(-1);

        Respondent respondentFromSession = (Respondent) session.getAttribute("respondent");

        // get all answers for questions
        HashMap<Question, String> result = new HashMap<>();
        for (Question question : stat.getArrQuestions()) {
            result.put(question, req.getParameter(question.getName()));
        }
        // add/update statistics for respondent
        Respondent currentResponded = stat.addRespondentAnswers(name, surname, age, result);

        if (respondentFromSession == null){
            session.setAttribute("respondent", currentResponded);
        }else if (!respondentFromSession.equals(currentResponded)){
            session.setAttribute("respondent", currentResponded);
        }
        resp.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null)) {
            session.removeAttribute("respondent");
        }

        response.sendRedirect("index.jsp");
    }
}
