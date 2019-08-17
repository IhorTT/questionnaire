package net.ukr.tigor;

import java.util.Objects;

public class Respondent {

    private String name;
    private String surname;
    private int age;

    public Respondent() {
    }

    public Respondent(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Respondent)) return false;
        Respondent that = (Respondent) o;
        return getAge() == that.getAge() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getAge());
    }

    @Override
    public String toString() {
        return "" + name + " " + surname + ", age: " + age;
    }
}
