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

    @Column(name="deleteflag")
    @Expose
    private Integer deleteFlag = 0; // 删除标志符	  0 代表false 1 代表true-已删除

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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
