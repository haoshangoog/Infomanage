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

}
