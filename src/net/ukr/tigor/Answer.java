package net.ukr.tigor;

public class Answer {
    private String name;
    private String description;
    private int number;

    public Answer() {

    }

    public Answer(String name, String description, int number) {
        this.name = name;
        this.description = description;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setVariant(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "variant='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                '}';
    }
}
