package com.hand.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by tuberose on 16-10-7.
 */
@Entity(name="FAQ")
public class FAQ implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    @Expose
    private int id;

    @Column(name="question",columnDefinition="TEXT")
    @Expose
    private String question;

    @Column(name="answer",columnDefinition="TEXT")
    @Expose
    private String answer;

    @Column(name="deleteflag")
    @Expose
    private Integer deleteFlag = 0; // 删除标志符	  0 代表false 1 代表true-已删除

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
