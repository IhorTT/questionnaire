package net.ukr.tigor;

import java.util.ArrayList;

public class Question {

    private String name;
    private String description;
    private ArrayList<Answer> answers;

    public Question() {
    }

    public Question(String name, String description) {
        this.name = name;
        this.description = description;
        answers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", answers=" + answers +
                '}';
    }
}
