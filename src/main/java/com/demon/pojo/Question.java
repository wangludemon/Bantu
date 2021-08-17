package com.demon.pojo;

public class Question {
    int q_id;
    String q_text;
    String q_A;
    String q_B;
    String q_C;
    String q_D;
    String q_E;
    int unit;
    String subject;
    String answer;

    public Question(){}

    public Question(int q_id, String q_text, String q_A,
                    String q_B, String q_C, String q_D, String q_E,
                    int unit, String subject, String answer) {
        this.q_id = q_id;
        this.q_text = q_text;
        this.q_A = q_A;
        this.q_B = q_B;
        this.q_C = q_C;
        this.q_D = q_D;
        this.q_E = q_E;
        this.unit = unit;
        this.subject = subject;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "q_id=" + q_id +
                ", q_text='" + q_text + '\'' +
                ", q_A='" + q_A + '\'' +
                ", q_B='" + q_B + '\'' +
                ", q_C='" + q_C + '\'' +
                ", q_D='" + q_D + '\'' +
                ", q_E='" + q_E + '\'' +
                ", unit=" + unit +
                ", subject='" + subject + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    public void setQ_A(String q_A) {
        this.q_A = q_A;
    }

    public void setQ_B(String q_B) {
        this.q_B = q_B;
    }

    public void setQ_C(String q_C) {
        this.q_C = q_C;
    }

    public void setQ_D(String q_D) {
        this.q_D = q_D;
    }

    public void setQ_E(String q_E) {
        this.q_E = q_E;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQ_id() {
        return q_id;
    }

    public String getQ_text() {
        return q_text;
    }

    public String getQ_A() {
        return q_A;
    }

    public String getQ_B() {
        return q_B;
    }

    public String getQ_C() {
        return q_C;
    }

    public String getQ_D() {
        return q_D;
    }

    public String getQ_E() {
        return q_E;
    }

    public int getUnit() {
        return unit;
    }

    public String getSubject() {
        return subject;
    }

    public String getAnswer() {
        return answer;
    }
}
