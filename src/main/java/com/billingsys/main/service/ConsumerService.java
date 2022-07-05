package com.billingsys.main.service;

import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Readings;
import com.billingsys.main.models.Slabs;

import java.util.List;

public interface ConsumerService {
    void saveConsumer(Consumer c, Readings r);

    Consumer verify(Consumer u);

    Consumer getConsumer(String uname);

    List<Readings> getReadings(int id);

    Readings lastReading(String name);

    Consumer getConsumerById(int id);

    void updateConsumer(Consumer c);

    List<Slabs> getSlabs();

    double cal(double previousReading, double currentReading);

    double cal2(double previousReading, double currentReading);

    void updateReadings(Readings r, String uname);
}
