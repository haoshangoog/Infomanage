package com.hand.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @SerializedName("label")
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

    @Column(name="testPlanContextId")
    @Expose
    private int testPlanContextId = 0;

    @Column(name="deleteflag")
    @Expose
    private Integer deleteFlag = 0; // 删除标志符	  0 代表false 1 代表true-已删除

    @Transient
    @Expose
    private List<TestPlanCatalogue> children;

    @Transient
    @Expose
    private TestPlanContext testPlanContext;


    public TestPlanCatalogue(){};

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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getTestPlanContextId() {
        return testPlanContextId;
    }

    public void setTestPlanContextId(int testPlanContextId) {
        this.testPlanContextId = testPlanContextId;
    }

    public List<TestPlanCatalogue> getChildren() {
        return children;
    }

    public void setChildren(List<TestPlanCatalogue> children) {
        this.children = children;
    }

    public TestPlanContext getTestPlanContext() {
        return testPlanContext;
    }

    public void setTestPlanContext(TestPlanContext testPlanContext) {
        this.testPlanContext = testPlanContext;
    }
}
