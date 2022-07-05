package com.billingsys.main.models;


import javax.persistence.*;

@Entity
public class Readings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double previousReading;
    private double previousBill;
    private String date;
    @ManyToOne
    @JoinColumn(name = "consumerID")
    Consumer consumer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreviousReading() {
        return previousReading;
    }

    public void setPreviousReading(double previousReading) {
        this.previousReading = previousReading;
    }

    public double getPreviousBill() {
        return previousBill;
    }

    public void setPreviousBill(double previousBill) {
        this.previousBill = previousBill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }


}
