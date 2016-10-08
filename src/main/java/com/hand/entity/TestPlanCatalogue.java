package com.hand.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tuberose on 16-10-7.
 */
@Entity(name="testPlanCatalogue")
public class TestPlanCatalogue implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="id")
    @Expose
    private int id;

    @Column(name="catalogueName")
    @Expose
    private String catalogueName;

    @Column(name="parentsId")
    @Expose
    private int parentsId;

    @Column(name="sequence")
    @Expose
    private int sequence;

    @Column(name="testPlanId")
    @Expose
    private int testPlanId;

    public TestPlanCatalogue(String catalogueName, int parentsId, int sequence,int testPlanId) {
        this.catalogueName = catalogueName;
        this.parentsId = parentsId;
        this.sequence = sequence;
        this.testPlanId = testPlanId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName(String catalogueName) {
        this.catalogueName = catalogueName;
    }

    public int getParentsId() {
        return parentsId;
    }

    public void setParentsId(int parentsId) {
        this.parentsId = parentsId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(int testPlanId) {
        this.testPlanId = testPlanId;
    }
}
