package com.demon.pojo;

import java.util.Date;

public class ExamPaper {
    String p_id;
    Date create_time;
    String content;

    public ExamPaper(String p_id, Date create_time, String content) {
        this.p_id = p_id;
        this.create_time = create_time;
        this.content = content;
    }
    public ExamPaper(){}

    @Override
    public String toString() {
        return "ExamPaper{" +
                "p_id=" + p_id +
                ", create_time=" + create_time +
                ", content='" + content + '\'' +
                '}';
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
