package com.billingsys.main.service;

import com.billingsys.main.models.Admin;
import com.billingsys.main.models.Consumer;
import com.billingsys.main.models.Slabs;
import com.billingsys.main.repo.AdminRepo;
import com.billingsys.main.repo.ConsumerRepo;
import com.billingsys.main.repo.SlabsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepo ar;

    @Autowired
    SlabsRepo sr;

    @Autowired
    ConsumerRepo cr;

    @Override
    public Admin verify(Admin a) {

        return ar.verify(a.getName(),a.getPassword());
    }

    @Override
    public void addSlabs(Slabs s) {

        sr.save(s);

    }

    @Override
    public Slabs getSlab(int id) {
        return sr.getReferenceById(id);
    }

    @Override
    public void updateSlab(Slabs s) {

        sr.save(s);

    }

    @Override
    public List<Consumer> getDetailes() {
        return cr.findAll();
    }
}
