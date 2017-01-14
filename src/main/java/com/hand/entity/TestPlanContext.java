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
@Entity(name="testPlanContext")
public class TestPlanContext implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    @Expose
    private int id;

    @Column(name="testPlanContext",columnDefinition="TEXT")
    @Expose
    private String testPlanContext;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestPlanContext() {
        return testPlanContext;
    }

    public void setTestPlanContext(String testPlanContext) {
        this.testPlanContext = testPlanContext;
    }
}
