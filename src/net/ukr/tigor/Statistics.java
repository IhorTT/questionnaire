package net.ukr.tigor;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {

    private ArrayList<Question> arrQuestions;
    private HashMap<Respondent, HashMap<Question, ArrayList<Answer>>> answersOfRespondent = new HashMap<Respondent, HashMap<Question, ArrayList<Answer>>>();

    public Statistics() {
        arrQuestions = new ArrayList<>();
    }

    public Statistics(Boolean test) {
        arrQuestions = new ArrayList<>();
        if (test) {
            addQuestionForTest("language", "Your nativ language is:", new String[]{"ukrainian", "russian", "english"}, new String[]{"Ukrainian", "Russian", "English"});
            addQuestionForTest("browser", "Your favorite browser is:", new String[]{"opera", "ie", "chrome"}, new String[]{"Opera", "Internet Explorer", "Google Chrome"});
            addQuestionForTest("car", "Your car is:", new String[]{"bmw", "audi", "fiat", "honda"}, new String[]{"BMW", "Audi", "Fiat", "Honda"});
        }
    }

    public void addQuestionForTest(String name, String description, String[] answersName, String[] answersDescription) {
        Question q = new Question(name, description);
        for (int i = 0; i < answersName.length; i++) {
            Answer answ = new Answer(answersName[i], answersDescription[i], i + 1);
            q.addAnswer(answ);
        }
        arrQuestions.add(q);
    }

    public ArrayList<Question> getArrQuestions() {
        return arrQuestions;
    }

    private Respondent getResponden(String name, String surname, int age) {
        Respondent result = null;
        for (Respondent rsp : answersOfRespondent.keySet()) {
            if ((rsp.getName().equals(name)) && (rsp.getSurname().equals(surname)) && (rsp.getAge() == age)) {
                result = rsp;
                System.out.println("Найден " + rsp);
                break;
            }
        }
        return result;
    }

    private Answer findAnswerByName(Question question, String strnameOfAnswer) {
        Answer result = null;
        ArrayList<Answer> arrAnswers = question.getAnswers();
        for (Answer answer : arrAnswers) {
            if (answer.getName().equals(strnameOfAnswer)) {
                result = answer;
                break;
            }
        }
        return result;
    }

    public Respondent addRespondentAnswers(String name, String surname, String age, HashMap<Question, String> answers) {

        int ageInt = 0;
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Respondent rsp = getResponden(name, surname, ageInt);

        if (rsp == null) {

            rsp = new Respondent(name, surname, ageInt);
            HashMap<Question, ArrayList<Answer>> newAnswers = new HashMap<Question, ArrayList<Answer>>();
            for (Question question : answers.keySet()) {
                ArrayList<Answer> answer = new ArrayList<Answer>();
                answer.add(findAnswerByName(question, answers.get(question)));
                newAnswers.put(question, answer);
            }
            answersOfRespondent.put(rsp, newAnswers);
        } else {

            for (Question question : answers.keySet()) {
                ArrayList<Answer> arrAnswers = answersOfRespondent.get(rsp).get(question);
                arrAnswers.add(findAnswerByName(question, answers.get(question)));
            }
        }
        return rsp;
    }

    public String getReportByRespondents() {
        String result = "<p><b>By respondents (" + answersOfRespondent.size() + "):</b><br>";
        int num = 1;
        for (Respondent respondent : answersOfRespondent.keySet()) {
            result += "" + num + ": " + respondent + "<Br>";
            for (Question question : answersOfRespondent.get(respondent).keySet()) {

                result += "" + question.getDescription() + "<Br>";

                ArrayList<Answer> arrAnswers = answersOfRespondent.get(respondent).get(question);

                HashMap<Answer, Integer> statForRespondent = new HashMap<Answer, Integer>();
                for (Answer answer : arrAnswers) {

                    Integer numForResponder = statForRespondent.get(answer);
                    if (numForResponder == null) {
                        statForRespondent.put(answer, 1);
                    } else {
                        statForRespondent.put(answer, numForResponder + 1);
                    }
                }
                for (Answer answr : statForRespondent.keySet()) {
                    result += answr.getDescription() + " : " + statForRespondent.get(answr) + "; ";
                }
                result += "<Br>";
            }
            num++;
        }
        result += "</p>";
        return result;
    }

    public String getReportByQuestion() {

        String result = "<p><b>By Total questions (" + arrQuestions.size() + "):</b><br>";

        HashMap<Question, HashMap<Answer, Integer>> totalStat = getHashMapForTotalQuestion();
        int num = 1;
        for (Question question : totalStat.keySet()) {
            result += "" + num + ": " + question.getDescription() + "<Br>";
            for (Answer answer : totalStat.get(question).keySet()) {
                result += "" + answer.getDescription() + ": " + totalStat.get(question).get(answer) + "; ";
            }
            result += "<Br>";
            num++;
        }
        result += "</p>";
        return result;
    }

    private HashMap<Question, HashMap<Answer, Integer>> getHashMapForTotalQuestion() {

        HashMap<Question, HashMap<Answer, Integer>> totalStat = new HashMap<Question, HashMap<Answer, Integer>>();
        for (Question question : arrQuestions) {
            HashMap<Answer, Integer> hmapAnswers = new HashMap<Answer, Integer>();
            for (Answer ansew : question.getAnswers()) {
                hmapAnswers.put(ansew, 0);
            }
            totalStat.put(question, hmapAnswers);
        }

        for (Respondent respondent : answersOfRespondent.keySet()) {
            for (Question question : answersOfRespondent.get(respondent).keySet()) {
                ArrayList<Answer> arrAnswers = answersOfRespondent.get(respondent).get(question);
                for (Answer answer : arrAnswers) {
                    Integer numForTotalStat = totalStat.get(question).get(answer);
                    totalStat.get(question).put(answer, numForTotalStat + 1);
                }
            }
        }
        return totalStat;
    }

}
