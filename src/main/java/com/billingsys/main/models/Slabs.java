package com.billingsys.main.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Slabs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int lowRange;
    private int highRange;
    private double charge;
    private char type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLowRange() {
        return lowRange;
    }

    public void setLowRange(int lowRange) {
        this.lowRange = lowRange;
    }

    public int getHighRange() {
        return highRange;
    }

    public void setHighRange(int highRange) {
        this.highRange = highRange;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }


}
