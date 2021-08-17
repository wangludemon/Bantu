package com.demon.pojo;

import java.util.Date;

public class ExamResult {
    String username;
    String p_id;
    double grade;
    String mcqs_id;
    Date start_time;
    Date end_time;

    public ExamResult(String username, String p_id, double grade, String mcqs_id, Date start_time, Date end_time) {
        this.username = username;
        this.p_id = p_id;
        this.grade = grade;
        this.mcqs_id = mcqs_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public ExamResult(){}

    @Override
    public String toString() {
        return "ExamResult{" +
                "username='" + username + '\'' +
                ", p_id='" + p_id + '\'' +
                ", grade=" + grade +
                ", mcqs_id='" + mcqs_id + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getMcqs_id() {
        return mcqs_id;
    }

    public void setMcqs_id(String mcqs_id) {
        this.mcqs_id = mcqs_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }
}
