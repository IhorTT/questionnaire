<%@ page import="static net.ukr.tigor.MyServletContextListener.stat" %>
<%@ page import="net.ukr.tigor.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="net.ukr.tigor.Answer" %>
<%@ page import="net.ukr.tigor.Respondent" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questionnaire</title>
</head>
<body>

<% Respondent respondent = (Respondent) session.getAttribute("respondent");
    boolean newObj = false;
    if (respondent == null) {
        newObj = true;
    }
%>
<!-- -->
<form action="/qs" method="POST">

    <p><b>Name:</b><br>
        <input type="text" size="10" name="name" value=<%= newObj ? "" : respondent.getName() %>>
    </p>

    <p><b>Surname:</b><br>
        <input type="text" size="10" name="surname" value=<%= newObj ? "" : respondent.getSurname() %>>
    </p>

    <p><b>Age:</b><br>
        <input type="text" size="1" name="age" value=<%= newObj ? "" : respondent.getAge()%>>
    </p>


    <%for (Question question : stat.getArrQuestions()) { %>
    <p><b><%=question.getDescription()%>
    </b><br>
        <% ArrayList<Answer> arrAnsw = question.getAnswers();
            for (Answer answ : arrAnsw) {%>
        <input type="radio" name=<%=question.getName() %> value= <%=answ.getName()%>> <%=answ.getDescription()%>  <Br>
        <%}%>
    </p>
    <% }%>

    <p><input type="submit" value="Send">
</form>

<form action="/stat" method="POST">
    <p><input type="submit" value="Show statistics">
</form>

</body>

</html>
