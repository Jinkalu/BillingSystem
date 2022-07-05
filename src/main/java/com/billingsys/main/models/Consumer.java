package com.billingsys.main.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long consumerNumber;
    private String name;
    private String address;
    private String phone;
    private String password;

    @OneToMany(mappedBy = "consumer")
    private List<Readings> r;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(long consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Readings> getR() {
        return r;
    }

    public void setR(List<Readings> r) {
        this.r = r;
    }
}
