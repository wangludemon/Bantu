package com.demon.pojo;

public class MCQs {
    private String username;
    int q_id;
    String answer;

    public MCQs(){}
    public MCQs(String username, int q_id, String answer) {
        this.username = username;
        this.q_id = q_id;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "MCQs{" +
                "username='" + username + '\'' +
                ", q_id=" + q_id +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
