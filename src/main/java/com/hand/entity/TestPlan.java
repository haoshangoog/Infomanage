package com.hand.entity;

import java.io.Serializable;

/**
 * Created by tuberose on 16-10-7.
 */
import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity(name="testPlan")
public class TestPlan implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    @Expose
    private int id;

    @Column(name="planname")
    @Expose
    private String planname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }
}
