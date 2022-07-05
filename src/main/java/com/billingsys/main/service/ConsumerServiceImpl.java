package com.billingsys.main.service;

import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Readings;
import com.billingsys.main.models.Slabs;
import com.billingsys.main.repo.ConsumerRepo;
import com.billingsys.main.repo.ReadingsRepo;
import com.billingsys.main.repo.SlabsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    ConsumerRepo cr;
    @Autowired
    ReadingsRepo rr;
    @Autowired
    SlabsRepo sr;
    @Override
    public void saveConsumer(Consumer c, Readings r) {
        cr.save(c);
        r.setConsumer(c);
        rr.save(r);
    }

    @Override
    public Consumer verify(Consumer u) {

        return  cr.verifyConsumer(u.getName(),u.getPassword());
    }

    @Override
    public Consumer getConsumer(String uname) {

        return cr.getByName(uname);
    }

    @Override
    public List<Readings> getReadings(int id) {


        return rr.getByConsumer(cr.getReferenceById(id));
    }


    @Override
    public Consumer getConsumerById(int id) {

        return cr.getReferenceById(id);
    }

    @Override
    public void updateConsumer(Consumer c) {
        cr.save(c);
    }



    @Override
    public Readings lastReading(String name) {


        return rr.getLast(cr.getByName(name));
    }

    @Override
    public List<Slabs> getSlabs() {

        return sr.findAll();
    }

    @Override
    public double cal(double previousReading, double currentReading) {

        int usage= (int) (currentReading-previousReading);

        Slabs s=sr.getSlab(usage);


        double bill=0;

        if(usage<=50) {

            bill=usage*s.getCharge();

        }else if(s.getType()=='t'){

            bill=telescopic(s,usage);

        }else {
            bill=nonTelescopic(s,usage);
        }

        return bill;
    }

    @Override
    public double cal2(double previousReading, double currentReading) {

        int usage= (int) (currentReading-previousReading)/2;

        Slabs s=sr.getSlab(usage);

        double bill=0;

        if(usage<=50) {

            bill=usage*s.getCharge();

        }else if(s.getType()=='t'){

            bill=telescopic(s,usage);

        }else {
            bill=nonTelescopic(s,usage);
        }

        return bill*2;
    }



    private double nonTelescopic(Slabs s, int usage) {

        double b=usage*s.getCharge();

        return b;
    }

    private double telescopic(Slabs s,int usage) {

        List<Slabs> sl=sr.getSlabs(s.getHighRange());

        double b=0;

        for (int i=0;i<sl.size()-1;i++){
            b=b+(50*sl.get(i).getCharge());

        }
            b=b+((usage-(s.getLowRange()))*s.getCharge());

        return b;
    }

    @Override
    public void updateReadings(Readings r, String uname) {


        LocalDate d=LocalDate.now();
        String dt=d.toString();

        r.setDate(d.toString());
        r.setConsumer(cr.getByName(uname));

        rr.save(r);

    }


}
