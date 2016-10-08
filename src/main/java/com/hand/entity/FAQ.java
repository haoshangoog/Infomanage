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

    @Column(name="question")
    @Expose
    private String question;

    @Column(name="answer")
    @Expose
    private String answer;


}
