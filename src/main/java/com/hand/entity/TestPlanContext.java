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

    @Column(name="catalogueId")
    @Expose
    private int catalogueId;

    ////////  以下是测试的内容 ///////////////////////

    //测试ID
    @Column(name="testID")
    @Expose
    private String testID;

    // 测试内容
    @Column(name="testContext")
    @Expose
    private String testContext;

    //  测试目的
    @Column(name="testAim")
    @Expose
    private String testAim;

    //  测试方法
    @Column(name="testMethod")
    @Expose
    private String testMethod;

    //测试拓扑
    @Column(name="testTopology")
    @Expose
    private String testTopology;

    //测试配置
    @Column(name="testConfigure")
    @Expose
    private String testConfigure;

    //测试结果
    @Column(name="testResult")
    @Expose
    private String testResult;

    //北向接口
    @Column(name="northInterface")
    @Expose
    private String northInterface;

    //测试结论
    @Column(name="testConclusion")
    @Expose
    private String testConclusion;

    //备注
    @Column(name="remark")
    @Expose
    private String remark;

}
