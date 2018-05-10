package com.tw;

import java.util.Objects;

/**
 * @program: student-grade-command-basic-java
 * @description: 学生
 * @author: liust
 * @create: 2018-05-08 16:51
 **/
public class Student {
    private String id;
    private String name;
    private double math;
    private double chinese;
    private double english;
    private double programming;
    private double summary = 0;

    public double getMath() {
        return math;
    }

    @Override
    public String toString() {
        double average = summary / 4;
        return
                name +
                        "|" + math +
                        "|" + chinese +
                        "|" + english +
                        "|" + programming +
                        "|" + average +
                        "|" + summary+"\n"
                ;
    }

    public void setMath(double math) {
        this.summary += math;
        this.math = math;
    }

    public double getChinese() {
        return chinese;
    }

    public void setChinese(double chinese) {
        this.summary += chinese;
        this.chinese = chinese;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.summary += english;
        this.english = english;
    }

    public double getProgramming() {
        return programming;
    }

    public void setProgramming(double programming) {
        this.summary += programming;
        this.programming = programming;
    }

    public double getSummary() {
        return summary;
    }

    public void setSummary(double summary) {
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.math, math) == 0 &&
                Double.compare(student.chinese, chinese) == 0 &&
                Double.compare(student.english, english) == 0 &&
                Double.compare(student.programming, programming) == 0 &&
                Double.compare(student.summary, summary) == 0 &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, math, chinese, english, programming, summary);
    }
}
